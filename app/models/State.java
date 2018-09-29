package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class State
{
    @Id private String stateId;
    private String stateName;
    private int stateNumber;

    public String getStateId()
    {
        return stateId;
    }

    public String getStateName()
    {
        return stateName;
    }

    public int getStateNumber()
    {
        return stateNumber;
    }
}
