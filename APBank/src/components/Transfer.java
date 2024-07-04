package components;

public class Transfer extends Flow {
    private int issuingAccountNumber;

    public Transfer(String comment, int identifier, double amount, int targetAccountNumber, boolean effect, int issuingAccountNumber) {
        super(comment, identifier, amount, targetAccountNumber, effect);
        this.issuingAccountNumber = issuingAccountNumber;
    }
}
