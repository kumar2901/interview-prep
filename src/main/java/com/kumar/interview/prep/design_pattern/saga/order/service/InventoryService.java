package com.kumar.interview.prep.design_pattern.saga.order.service;

public interface InventoryService {

    String reserveInventory(ReservationRequest request);

    void releaseReservation(String reservationId);

    int availableQuantity(String productId);

    record ReservationRequest(String sagaId, String productId, int quantity) {
    }
}
