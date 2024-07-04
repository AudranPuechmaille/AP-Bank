package components;

public class Credit extends Flow {
    public Credit(String comment, int identifier, double amount, int targetAccountNumber, boolean effect) {
        super(comment, identifier, amount, targetAccountNumber, effect);
    }
}
