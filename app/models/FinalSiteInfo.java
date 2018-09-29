package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FinalSiteInfo
{
    //s.siteInfoId, s.projectNumber, c.countyName, t.stateId, CONCAT(t.stateNumber, c.countyId, s.siteNumber) AS stateSiteNumber
    @Id private int siteInfoId;
    private String projectNumber;
    private String countyName;
    private String stateId;
    private String stateSiteNumber;

    public FinalSiteInfo(int siteInfoId, String projectNumber, String countyName, String stateId, String stateSiteNumber)
    {
        this.siteInfoId = siteInfoId;
        this.projectNumber = projectNumber;
        this.countyName = countyName;
        this.stateId = stateId;
        this.stateSiteNumber = stateSiteNumber;
    }

    public int getSiteInfoId()
    {
        return siteInfoId;
    }

    public String getProjectNumber()
    {
        return projectNumber;
    }

    public String getCountyName()
    {
        return countyName;
    }

    public String getStateId()
    {
        return stateId;
    }

    public String getStateSiteNumber()
    {
        return stateSiteNumber;
    }
}
