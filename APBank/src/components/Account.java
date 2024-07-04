package components;

public abstract class Account 
{
    protected static int nextAccountNumber = 1;

    protected String label;
    protected double balance;
    protected int accountNumber;
    protected Client client;

    public Account(String label, Client client) 
    {
        this.label = label;
        this.client = client;
        this.accountNumber = nextAccountNumber++;
        this.balance = 0.0;
    }

    // Méthode abstraite
    public abstract void updateBalance(Flow flow);
    
    @Override
    public String toString() 
    {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", label='" + label + '\'' +
                ", client=" + client.toString() +
                '}';
    }

	public static int getNextAccountNumber() 
	{
		return nextAccountNumber;
	}

	public static void setNextAccountNumber(int nextAccountNumber) 
	{
		Account.nextAccountNumber = nextAccountNumber;
	}

	public String getLabel() 
	{
		return label;
	}

	public void setLabel(String label) 
	{
		this.label = label;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) 
	{
		this.balance = balance;
	}

	public int getAccountNumber() 
	{
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) 
	{
		this.accountNumber = accountNumber;
	}

	public Client getClient() 
	{
		return client;
	}

	public void setClient(Client client) 
	{
		this.client = client;
	}
}
