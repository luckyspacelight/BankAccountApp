package org.bank;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private final List<Transaction> transactions;
    private float balance;
    private final String holder;

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getHolder() {
        return holder;
    }

    public Account(String holder) {
        transactions = new ArrayList<>();
        balance = 0;
        this.holder = holder;
    }

    public void deposit(float amount) {
        balance += amount;
        var tr = new Transaction(amount, TransactionType.DEPOSIT);
        transactions.add(tr);
    }

    public  void withdraw(float amount) throws InsufficientFundsException {
        if (amount > this.balance) {
            throw new InsufficientFundsException("Fondi non sufficienti nel conto");
        } else {
            this.balance -= amount;
            var tr = new Transaction(amount, TransactionType.WITHDRAW);
            transactions.add(tr);
        }
    }

}
