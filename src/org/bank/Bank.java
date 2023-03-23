package org.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {

    private final List<Account> accounts;

    public Account getAccount(String accountId) {
        for (var account : accounts) {
            if (account.getHolder().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

    public Bank() {
        accounts = new ArrayList<>();
    }

    public static void main(String[] args) {
        var bank = new Bank();
        bank.run();
    }

    private void run() {

        Scanner sc = new Scanner(System.in);
        int scelta;
        
        do {
            System.out.println("\nBenvenuti nella Banca ABC");
            System.out.println("Scegliere l'opzione desiderata:\n");
            System.out.println("1 - Aggiunta conto corrente");
            System.out.println("2 - Stampa conti correnti");
            System.out.println("3 - Operazione su conto");
            System.out.println("4 - Stampa storico operazioni conto");
            System.out.println("0 - Esci da \"Bank Account App\"");

            scelta = sc.nextInt();
            switch (scelta) {
                case 1 -> addAccount();
                case 2 -> printAccounts();
                case 3 -> doAccountOperation();
                case 4 -> printAccountTransactionHistory();
                case 0 -> System.out.print("Uscita in corso...");
                default -> System.err.println("ERRORE: Input non valido");
            }
        } while(scelta != 0);
    }

    private void printAccountTransactionHistory() {
        System.out.println("Stampa storico operazioni conto...");
        var sc = new Scanner(System.in);
        System.out.println("Inserisci codice conto: ");
        var holder = sc.nextLine();
        var account = getAccount(holder);
        if (account == null) {
            System.err.println("ERRORE: conto inesistente");
            return;
        }
        for (var transaction : account.getTransactions()) {
            String opType = "";
            switch (transaction.getType()) {
                case DEPOSIT -> opType = "Deposito";
                case WITHDRAW -> opType = "Prelievo";
            }
            System.out.printf("%s: $%.2f%n", opType, transaction.getAmount());
        }
        System.out.println("-----------------");
        System.out.printf("Saldo: $%.2f%n", account.getBalance());
    }

    private void doAccountOperation() {
        System.out.println("Operazione su conto...");
        var sc = new Scanner(System.in);
        System.out.println("Inserisci codice conto: ");
        var holder = sc.nextLine();
        var account = getAccount(holder);
        if (account == null) {
            System.err.println("ERRORE: conto inesistente");
            return;
        }

        int op; // Operazione
        do {
            System.out.println("Operazioni da eseguire \n(1. deposito, 2. prelievo, 0. uscita): ");
            op = sc.nextInt();

            switch(op) {
                case 1 -> {
                    System.out.println("Importo da depositare:");
                    var amount = sc.nextFloat();
                    account.deposit(amount);
                }
                case 2 -> {
                    System.out.println("Importo da prelevare:");
                    var amount = sc.nextFloat();
                    try {
                        account.withdraw(amount);
                    } catch (InsufficientFundsException e) {
                        System.err.println("Saldo non sufficiente");
                    }
                }
                case 0 -> System.out.println("Ritorno al menu principale...");
                default -> System.err.println("ERRORE: Puoi scegliere solo 0, 1 o 2");
            }
        } while (op != 0);
    }

    public void addAccount() {
        System.out.println("Aggiungi c/c");
        var sc = new Scanner(System.in);
        System.out.println("Inserisci codice titolare:");
        var holder = sc.nextLine();
        System.out.println("Inserisci bilancio iniziale:");
        var balance = sc.nextInt();
        var account = new Account(holder);
        account.setBalance(balance);
        accounts.add(account);
    }

    private void printAccounts() {
        System.out.println("Stampa conti correnti");
        for (Account account : accounts) {
            System.out.printf("%s: $%.2f%n", account.getHolder(), account.getBalance());
        }
    }
}