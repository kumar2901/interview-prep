package com.kumar.interview.prep.dsa.ebay_codesignal;

import java.util.List;

public class BankingSystemApplication {

    static void main() {
        BankingSystem app = new BankingSystem();

        app.createAccount("A1", 1);
        app.createAccount("A2", 2);
        app.createAccount("A3", 3);

        app.deposit("A1", 1000, 4);
        app.deposit("A2", 1000, 5);
        app.deposit("A3", 1000, 6);

        System.out.println("A1 balance: " + app.accounts.get("A1").balance);
        System.out.println("A2 balance: " + app.accounts.get("A2").balance);
        System.out.println("A3 balance: " + app.accounts.get("A3").balance);

        System.out.println("\n---- Level 2: Top Spenders ----");

        app.transfer("A1", "A3", 7, 200);
        app.transfer("A2", "A3", 7, 200);
        app.transfer("A3", "A1", 7, 200);
        app.transfer("A2", "A1", 7, 200);

        List<String> top2 = app.topSpenders(8, 2);
        System.out.println("Top 2 spenders: " + top2);
        System.out.println("Remaining Balance: ");
        app.accounts.values().forEach(account -> System.out
                .println(account.accountId + " : " + account.balance + " spent : " + account.spentAmount));

        System.out.println("\n---- Level 3: Scheduled Payments ----");

        app.schedulePayment("A2", "A3", 10, 400, 10); // 10% cashback
        app.schedulePayment("A1", "A3", 12, 300, 5); // 5% cashback

        System.out.println("Processing payments at timestamp 11...");
        app.processScheduledPayments(11);

        System.out.println("A1 balance: " + app.accounts.get("A1").balance);
        System.out.println("A2 balance: " + app.accounts.get("A2").balance);
        System.out.println("A3 balance: " + app.accounts.get("A3").balance);

        System.out.println("Processing payments at timestamp 15...");
        app.processScheduledPayments(15);

        System.out.println("A1 balance: " + app.accounts.get("A1").balance);
        System.out.println("A2 balance: " + app.accounts.get("A2").balance);
        System.out.println("A3 balance: " + app.accounts.get("A3").balance);

        System.out.println("\n---- Payment Status ----");
        System.out.println("Payment P1 status: " + app.getPaymentStatus("A2", 16, "Pay-1"));
        System.out.println("Payment P2 status: " + app.getPaymentStatus("A1", 16, "Pay-2"));

        System.out.println("\n---- Level 4: Merge Accounts ----");
        app.mergeAccounts("A1", "A3", 16);

        System.out.println("After merging A3 into A1:");
        System.out.println("A1 balance: " + app.accounts.get("A1").balance);
        System.out.println("Accounts present: " + app.accounts.keySet());

    }
}
