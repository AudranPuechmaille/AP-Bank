package components;

import java.time.LocalDate;

public abstract class Flow {
    private String comment;
    private int identifier;
    private double amount;
    private int targetAccountNumber;
    private boolean effect;
    private LocalDate dateOfFlow;

    public Flow(String comment, int identifier, double amount, int targetAccountNumber, boolean effect) {
        this.comment = comment;
        this.identifier = identifier;
        this.amount = amount;
        this.targetAccountNumber = targetAccountNumber;
        this.effect = effect;
        this.dateOfFlow = LocalDate.now().plusDays(2); // Opérations effectuées 2 jours après la création du flux
    }

    

    public String getComment() {
		return comment;
	}



	public void setComment(String comment) {
		this.comment = comment;
	}



	public int getIdentifier() {
		return identifier;
	}



	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}



	public double getAmount() {
		return amount;
	}



	public void setAmount(double amount) {
		this.amount = amount;
	}



	public int getTargetAccountNumber() {
		return targetAccountNumber;
	}



	public void setTargetAccountNumber(int targetAccountNumber) {
		this.targetAccountNumber = targetAccountNumber;
	}



	public boolean isEffect() {
		return effect;
	}



	public void setEffect(boolean effect) {
		this.effect = effect;
	}



	public LocalDate getDateOfFlow() {
		return dateOfFlow;
	}



	public void setDateOfFlow(LocalDate dateOfFlow) {
		this.dateOfFlow = dateOfFlow;
	}



	@Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "comment='" + comment + '\'' +
                ", identifier=" + identifier +
                ", amount=" + amount +
                ", targetAccountNumber=" + targetAccountNumber +
                ", effect=" + effect +
                ", dateOfFlow=" + dateOfFlow +
                '}';
    }
}
