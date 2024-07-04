package components;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    protected static int nextAccountNumber = 1;

    protected String label;
    protected double balance;
    protected int accountNumber;
    protected Client client;

    public Account(String label, Client client) {
        this.label = label;
        this.client = client;
        this.accountNumber = nextAccountNumber++;
        this.balance = 0.0;
    }

    // Méthodes abstraites
    public abstract void updateBalance(Flow flow);

    // Getters and setters
    public double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", label='" + label + '\'' +
                ", client=" + client.toString() +
                '}';
    }
}
