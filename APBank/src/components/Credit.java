package components;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Credit")
public class Credit extends Flow 
{
    public Credit() 
    {
        super();
    }

    public Credit(String comment, int identifier, double amount, int targetAccountNumber, boolean effect) 
    {
        super(comment, identifier, amount, targetAccountNumber, effect);
    }
}
