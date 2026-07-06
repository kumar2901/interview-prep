package com.kumar.interview.prep.design_pattern.saga.order.service.inmemory;

import com.kumar.interview.prep.design_pattern.saga.order.service.DeliveryService;
import com.kumar.interview.prep.design_pattern.saga.order.service.InventoryService;
import com.kumar.interview.prep.design_pattern.saga.order.service.OrderDomainException;
import com.kumar.interview.prep.design_pattern.saga.order.service.OrderService;
import com.kumar.interview.prep.design_pattern.saga.order.service.PaymentService;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * In-memory service implementations for local demos and integration tests. Each operation is idempotent where
 * compensation may be retried.
 */
public final class InMemoryOrderInfrastructure {

    private InMemoryOrderInfrastructure() {
    }

    public static OrderServices createDefault() {
        return create(Map.of("SKU-42", 5), false);
    }

    public static OrderServices create(Map<String, Integer> initialInventory, boolean failInventoryReservation) {
        return new OrderServices(new InMemoryOrderService(), new InMemoryPaymentService(),
                new InMemoryInventoryService(initialInventory, failInventoryReservation),
                new InMemoryDeliveryService());
    }

    public record OrderServices(OrderService orders, PaymentService payments, InventoryService inventory,
            DeliveryService deliveries) {
    }

    private static final class InMemoryOrderService implements OrderService {

        private final AtomicInteger sequence = new AtomicInteger(1_000);
        private final Set<String> activeOrders = ConcurrentHashMap.newKeySet();
        private final Set<String> cancelledOrders = ConcurrentHashMap.newKeySet();

        @Override
        public String createOrder(PlaceOrderRequest request) {
            String orderId = "ORD-" + sequence.incrementAndGet();
            activeOrders.add(orderId);
            log("OrderService", "Created order " + orderId + " for saga " + request.sagaId());
            return orderId;
        }

        @Override
        public void cancelOrder(String orderId) {
            if (orderId == null || cancelledOrders.contains(orderId)) {
                return;
            }
            if (activeOrders.remove(orderId)) {
                cancelledOrders.add(orderId);
                log("OrderService", "Cancelled order " + orderId);
            }
        }
    }

    private static final class InMemoryPaymentService implements PaymentService {

        private final AtomicInteger sequence = new AtomicInteger(5_000);
        private final Set<String> capturedPayments = ConcurrentHashMap.newKeySet();
        private final Set<String> refundedPayments = ConcurrentHashMap.newKeySet();

        @Override
        public String capturePayment(PaymentRequest request) {
            String paymentId = "PAY-" + sequence.incrementAndGet();
            capturedPayments.add(paymentId);
            log("PaymentService", "Captured payment " + paymentId + " for order " + request.orderId());
            return paymentId;
        }

        @Override
        public void refundPayment(String paymentId) {
            if (paymentId == null || refundedPayments.contains(paymentId)) {
                return;
            }
            if (capturedPayments.remove(paymentId)) {
                refundedPayments.add(paymentId);
                log("PaymentService", "Refunded payment " + paymentId);
            }
        }
    }

    private static final class InMemoryInventoryService implements InventoryService {

        private final AtomicInteger sequence = new AtomicInteger(7_000);
        private final Map<String, Integer> inventory;
        private final Map<String, String> reservations = new ConcurrentHashMap<>();
        private final Set<String> releasedReservations = ConcurrentHashMap.newKeySet();
        private final boolean failReservation;

        private InMemoryInventoryService(Map<String, Integer> initialInventory, boolean failReservation) {
            this.inventory = new ConcurrentHashMap<>(initialInventory);
            this.failReservation = failReservation;
        }

        @Override
        public String reserveInventory(ReservationRequest request) {
            if (failReservation) {
                throw new OrderDomainException("Insufficient inventory for product " + request.productId());
            }

            int available = inventory.getOrDefault(request.productId(), 0);
            if (available < request.quantity()) {
                throw new OrderDomainException("Insufficient inventory for product " + request.productId());
            }

            inventory.put(request.productId(), available - request.quantity());
            String reservationId = "RES-" + sequence.incrementAndGet();
            reservations.put(reservationId, request.productId() + ":" + request.quantity());
            log("InventoryService", "Reserved " + request.quantity() + " x " + request.productId() + " as "
                    + reservationId + " (remaining=" + inventory.get(request.productId()) + ")");
            return reservationId;
        }

        @Override
        public void releaseReservation(String reservationId) {
            if (reservationId == null || releasedReservations.contains(reservationId)) {
                return;
            }

            String reservation = reservations.remove(reservationId);
            if (reservation == null) {
                return;
            }

            String[] parts = reservation.split(":");
            inventory.merge(parts[0], Integer.parseInt(parts[1]), Integer::sum);
            releasedReservations.add(reservationId);
            log("InventoryService",
                    "Released reservation " + reservationId + " (remaining=" + inventory.get(parts[0]) + ")");
        }

        @Override
        public int availableQuantity(String productId) {
            return inventory.getOrDefault(productId, 0);
        }
    }

    private static final class InMemoryDeliveryService implements DeliveryService {

        private final AtomicInteger sequence = new AtomicInteger(9_000);
        private final Set<String> scheduledDeliveries = ConcurrentHashMap.newKeySet();
        private final Set<String> cancelledDeliveries = ConcurrentHashMap.newKeySet();

        @Override
        public String scheduleDelivery(DeliveryRequest request) {
            String deliveryId = "DLV-" + sequence.incrementAndGet();
            scheduledDeliveries.add(deliveryId);
            log("DeliveryService", "Scheduled delivery " + deliveryId + " for order " + request.orderId());
            return deliveryId;
        }

        @Override
        public void cancelDelivery(String deliveryId) {
            if (deliveryId == null || cancelledDeliveries.contains(deliveryId)) {
                return;
            }
            if (scheduledDeliveries.remove(deliveryId)) {
                cancelledDeliveries.add(deliveryId);
                log("DeliveryService", "Cancelled delivery " + deliveryId);
            }
        }
    }

    private static void log(String service, String message) {
        System.out.println("     [" + service + "] " + message);
    }
}
