package components;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Transfer")
public class Transfer extends Flow 
{
    private int issuingAccountNumber;

    public Transfer() 
    {
        super();
    }

    public Transfer(String comment, int identifier, double amount, int targetAccountNumber, boolean effect, int issuingAccountNumber) 
    {
        super(comment, identifier, amount, targetAccountNumber, effect);
        this.issuingAccountNumber = issuingAccountNumber;
    }

    public int getIssuingAccountNumber() 
    {
        return issuingAccountNumber;
    }

    public void setIssuingAccountNumber(int issuingAccountNumber) 
    {
        this.issuingAccountNumber = issuingAccountNumber;
    }

    @Override
    public String toString() 
    {
        return super.toString() + ", issuingAccountNumber=" + issuingAccountNumber;
    }
}
