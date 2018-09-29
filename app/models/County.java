package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class County
{
    @Id private String countyId;
    private String countyName;
    private String stateId;

    public String getCountyId()
    {
        return countyId;
    }

    public String getCountyName()
    {
        return countyName;
    }

    public String getStateId()
    {
        return stateId;
    }
}
