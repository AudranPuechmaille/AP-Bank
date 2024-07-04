package components;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class ConcreteAccount extends Account 
{

    // Constructeur par défaut nécessaire pour JAXB
    public ConcreteAccount() 
    {
        super("", null); 
    }

    public ConcreteAccount(String label, Client client) 
    {
        super(label, client);
    }

    // Constructeur avec les paramètres nécessaires
    public ConcreteAccount(int accountNumber, String label, double balance, Client client) 
    {
        super(label, client);
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    
    @Override
    public void updateBalance(Flow flow) 
    {
        // Implémentation de la méthode abstraite
    }

    // Ajouter des annotations JAXB aux champs
    @XmlElement
    public String getLabel() 
    {
        return label;
    }

    public void setLabel(String label) 
    {
        this.label = label;
    }

    @XmlElement
    public double getBalance() 
    {
        return balance;
    }

    public void setBalance(double balance) 
    {
        this.balance = balance;
    }

    @XmlElement
    public int getAccountNumber() 
    {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) 
    {
        this.accountNumber = accountNumber;
    }

    @XmlElement
    public Client getClient() 
    {
        return client;
    }

    public void setClient(Client client) 
    {
        this.client = client;
    }
}
