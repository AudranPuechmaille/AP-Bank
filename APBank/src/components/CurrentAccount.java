package components;

public class CurrentAccount extends Account {
    public CurrentAccount(String label, Client client) {
        super(label, client);
    }

    @Override
    public void updateBalance(Flow flow) {
        double amount = flow.getAmount();
        if (flow instanceof Credit) {
            balance += amount;
        } else if (flow instanceof Debit || flow instanceof Transfer) {
            balance -= amount;
        }
    }
}
