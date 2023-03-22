package org.bank;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private final List<Transaction> transactions;
    private float balance;
    private String holder;

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

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public Account(String holder) {
        transactions = new ArrayList<>();
        balance = 0;
        this.holder = holder;
    }

    public void deposit(float amount) {
        balance += amount;
        //TODO: Implement Transaction!
    }

    public  void withdraw(float amount) {

    }

}
