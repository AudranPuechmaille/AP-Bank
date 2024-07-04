package components;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Debit")
public class Debit extends Flow 
{
    public Debit() 
    {
        super();
    }

    public Debit(String comment, int identifier, double amount, int targetAccountNumber, boolean effect) 
    {
        super(comment, identifier, amount, targetAccountNumber, effect);
    }
}
