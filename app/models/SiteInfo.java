package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SiteInfo
{
    @Id private int siteInfoId;
    private int siteNumber;
    private String countyId;
    private String projectNumber;
    private String accessionNumber;

    public int getSiteInfoId()
    {
        return siteInfoId;
    }

    public String getCountyId()
    {
        return countyId;
    }

    public String getProjectNumber()
    {
        return projectNumber;
    }

    public String getAccessionNumber()
    {
        return accessionNumber;
    }

    public int getSiteNumber()
    {
        return siteNumber;
    }
}
