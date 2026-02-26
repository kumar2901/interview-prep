package com.kumar.interview.prep.dsa.ebay_codesignal;

public class Account {

    String accountId;
    long balance;
    long lastUpdatedTimestamp;
    long spentAmount;

    public Account(String accountId, long timestamp) {
        this.accountId = accountId;
        this.lastUpdatedTimestamp = timestamp;
        this.balance = 0;
        this.spentAmount = 0;
    }

}
