package components;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "accounts")

public class AccountList 
{
    private List<ConcreteAccount> accounts;

    public AccountList() 
    {
        // Constructeur par défaut nécessaire pour JAXB
    }

    @XmlElement(name = "account")
    public List<ConcreteAccount> getAccounts() 
    {
        return accounts;
    }

    public void setAccounts(List<ConcreteAccount> accounts) 
    {
        this.accounts = accounts;
    }
}
