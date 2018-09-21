package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Unit
{
    @Id private int unitId;
    private int fieldSiteNumber;
    private String area;
    private int unitNumber;
    private int startDepth;
    private int endDepth;
    private int siteInfoId;

    public int getUnitId()
    {
        return unitId;
    }

    public void setUnitId(int unitId)
    {
        this.unitId = unitId;
    }

    public int getFieldSiteNumber()
    {
        return fieldSiteNumber;
    }

    public void setFieldSiteNumber(int fieldSiteNumber)
    {
        this.fieldSiteNumber = fieldSiteNumber;
    }

    public String getArea()
    {
        return area;
    }

    public void setArea(String area)
    {
        this.area = area;
    }

    public int getUnitNumber()
    {
        return unitNumber;
    }

    public void setUnitNumber(int unitNumber)
    {
        this.unitNumber = unitNumber;
    }

    public int getStartDepth()
    {
        return startDepth;
    }

    public void setStartDepth(int startDepth)
    {
        this.startDepth = startDepth;
    }

    public int getEndDepth()
    {
        return endDepth;
    }

    public void setEndDepth(int endDepth)
    {
        this.endDepth = endDepth;
    }

    public int getSiteInfoId()
    {
        return siteInfoId;
    }

    public void setSiteInfoId(int siteInfoId)
    {
        this.siteInfoId = siteInfoId;
    }
}
