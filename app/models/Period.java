package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Period
{
    @Id private int periodId;
    private String periodType;

    public int getPeriodId()
    {
        return periodId;
    }

    public String getPeriodType()
    {
        return periodType;
    }
}
