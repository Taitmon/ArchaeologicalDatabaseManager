package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SiteInfo
{
    @Id private int siteInfoId;
    private int countyId;
    private String projectNumber;
    private String accessionNumber;

    public int getSiteInfoId()
    {
        return siteInfoId;
    }

    public int getCountyId()
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
}
