package com.kumar.interview.prep.dsa.ebay_codesignal;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class BankingSystem {

    Map<String, Account> accounts;
    private long lastGlobalTimestamp = -1;
    private AtomicLong globalCounter = new AtomicLong(0);

    PriorityQueue<SchedulePayment> schedulePayments;// minHeap
    Map<String, SchedulePayment> schedulePaymentsById;

    public BankingSystem() {
        this.accounts = new HashMap<>();
        this.schedulePayments = new PriorityQueue<>(Comparator.comparingLong(a -> a.timestamp));
        this.schedulePaymentsById = new HashMap<>();
    }

    public boolean createAccount(String accountId, long timestamp) {

        if (!validateAndAdvanceTime(timestamp)) {
            return false;
        }

        if (accounts.containsKey(accountId)) {
            return false;
        }
        accounts.put(accountId, new Account(accountId, timestamp));

        return true;
    }

    private boolean validateAndAdvanceTime(long timestamp) {
        if (timestamp < lastGlobalTimestamp) {
            System.out.println("Error: Transaction has expired");
            return false;
        }

        processScheduledPayments(timestamp);
        lastGlobalTimestamp = timestamp;
        return true;
    }

    public boolean deposit(String accountId, long amount, long timestamp) {

        if (!validateAndAdvanceTime(timestamp)) {
            return false;
        }

        if (accounts.containsKey(accountId) && amount > 0) {
            Account account = accounts.get(accountId);
            account.balance += amount;
            account.lastUpdatedTimestamp = timestamp;
            return true;
        }
        System.out.println("Error: Either account does not exist or amount is negative");
        return false;
    }

    public boolean transfer(String fromId, String toId, long timestamp, long amount) {

        if (!validateAndAdvanceTime(timestamp)) {
            return false;
        }

        if (accounts.containsKey(fromId) && accounts.containsKey(toId) && amount > 0) {
            if (accounts.get(fromId).balance < amount) {
                System.out.println("Error: Insufficient Balance, Transfer failed");
                return false;

            }
            Account fromAccount = accounts.get(fromId);
            Account toAccount = accounts.get(toId);

            fromAccount.balance -= amount;
            toAccount.balance += amount;

            fromAccount.spentAmount += amount;

            fromAccount.lastUpdatedTimestamp = timestamp;
            toAccount.lastUpdatedTimestamp = timestamp;
            // System.out.println("Transfer successful from " + fromId + " balance " + fromAccount.balance + " to " +
            // toId + " balance " + toAccount.balance);

            return true;
        }
        return false;
    }

    public List<String> topSpenders(long timestamp, int n) {

        if (!validateAndAdvanceTime(timestamp)) {
            return new ArrayList<>();
        }

        List<Account> list = new ArrayList<>(accounts.values());
        list.sort((a, b) -> {
            if (a.spentAmount != b.spentAmount) {
                return Long.compare(b.spentAmount, a.spentAmount);
            }
            return a.accountId.compareTo(b.accountId);
        });
        if (list.size() < n) {
            return list.stream().map(account -> account.accountId).toList();
        }
        List<String> topSpenders = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            topSpenders.add(list.get(i).accountId);
        }
        return topSpenders;
    }

    String schedulePayment(String accountId, String targetAccId, int timestamp, int amount, double cashbackPercentage) {

        if (!validateAndAdvanceTime(timestamp)) {
            return null;
        }
        String paymentId = "Pay-" + globalCounter.incrementAndGet();
        SchedulePayment schedulePayment = new SchedulePayment(paymentId, accountId, targetAccId, timestamp, amount,
                cashbackPercentage);

        schedulePayments.offer(schedulePayment);
        schedulePaymentsById.put(paymentId, schedulePayment);

        System.out.println("Payment scheduled with paymentId: " + paymentId + " at timestamp: " + timestamp);

        return paymentId;

    }

    String getPaymentStatus(String accountId, int timestamp, String paymentId) {
        if (!validateAndAdvanceTime(timestamp)) {
            return "INVALID_TIMESTAMP";
        }

        SchedulePayment schedulePayment = schedulePaymentsById.get(paymentId);
        if (schedulePayment == null || !schedulePayment.fromAccountId.equals(accountId)) {
            System.out.println("Error: Payment not found for payment " + paymentId + " and account " + accountId);
            return "NOT_FOUND";
        }

        return schedulePayment.paymentStatus.name();
    }

    void processScheduledPayments(long currentTimestamp) {

        while (!schedulePayments.isEmpty() && currentTimestamp >= schedulePayments.peek().timestamp) {

            SchedulePayment schedulePayment = schedulePayments.poll();

            if (PaymentStatus.SCHEDULED != schedulePayment.paymentStatus) {
                continue;
            }

            Account fromAccount = accounts.get(schedulePayment.fromAccountId);
            Account toAccount = accounts.get(schedulePayment.toAccountId);
            long amount = schedulePayment.amount;
            if (fromAccount != null && toAccount != null && fromAccount.balance >= amount) {

                long cashback = (long) (amount * schedulePayment.cashbackPercentage / 100);
                fromAccount.balance -= amount;
                toAccount.balance += amount;

                fromAccount.spentAmount += amount;
                fromAccount.balance += cashback;

                fromAccount.lastUpdatedTimestamp = currentTimestamp;
                toAccount.lastUpdatedTimestamp = currentTimestamp;

                schedulePayment.paymentStatus = PaymentStatus.COMPLETED;

            } else {
                schedulePayment.paymentStatus = PaymentStatus.FAILED;
            }

        }

    }

    boolean mergeAccounts(String sourceAccount, String targetAccount, long timestamp) {
        if (!validateAndAdvanceTime(timestamp)) {
            return false;
        }

        Account acc1 = accounts.get(sourceAccount);
        Account acc2 = accounts.get(targetAccount);
        if (acc1 == null && acc2 == null || sourceAccount.equals(targetAccount)) {
            System.out.println("Error: Account " + sourceAccount + " or " + targetAccount + " not found or same");
            return false;
        }
        acc1.balance += acc2.balance;
        acc1.spentAmount += acc2.spentAmount;

        for (SchedulePayment schedulePayment : schedulePaymentsById.values()) {
            if (schedulePayment.fromAccountId.equals(sourceAccount)) {
                schedulePayment.fromAccountId = sourceAccount;
            }
            if (schedulePayment.toAccountId.equals(targetAccount)) {
                schedulePayment.toAccountId = targetAccount;
            }
        }

        accounts.remove(targetAccount);
        acc1.lastUpdatedTimestamp = timestamp;

        return true;
    }

}
