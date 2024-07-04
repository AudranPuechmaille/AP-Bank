package components;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Client 
{
    private static int nextClientNumber = 1;

    @XmlElement
    private String firstName;

    @XmlElement
    private String lastName;

    @XmlElement
    private int clientNumber;

    public Client() 
    {
        // Constructeur par défaut nécessaire pour JAXB
    }

    public Client(String firstName, String lastName) 
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.clientNumber = nextClientNumber++;
    }

    public Client(String firstName, String lastName, int clientNumber) 
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.clientNumber = clientNumber;
    }

    // Getters and setters
    public String getFirstName() 
    {
        return firstName;
    }

    public void setFirstName(String firstName) 
    {
        this.firstName = firstName;
    }

    public String getLastName() 
    {
        return lastName;
    }

    public void setLastName(String lastName) 
    {
        this.lastName = lastName;
    }

    public int getClientNumber() 
    {
        return clientNumber;
    }

    public void setClientNumber(int clientNumber) 
    {
        this.clientNumber = clientNumber;
    }

    @Override
    public String toString() 
    {
        return "Client{" +
                "clientNumber=" + clientNumber +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
