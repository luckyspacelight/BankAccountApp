package org.bank;

public class Transaction {

    private final float amount;
    private final TransactionType type;

    public Transaction(float amount, TransactionType type) {
        this.amount = amount;
        this.type = type;
    }

    public float getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }
}
