package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LSN
{
    @Id private int LSNId;
    private String LSNName;

    public int getLSNId()
    {
        return LSNId;
    }

    public String getLSNName()
    {
        return LSNName;
    }
}
