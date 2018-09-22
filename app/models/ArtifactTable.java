package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ArtifactTable
{
    @Id private int artifactId;
    private int fieldSiteNumber;
    private String areaLetter;
    private int unitNumber;
    private int startDepth;
    private int endDepth;
    private int periodId;
    private int LSNId;
    private int ASN1Id;
    private int ASN2Id;
    private Integer ASN3Id;

    public ArtifactTable(int artifactId, int fieldSiteNumber, String areaLetter, int unitNumber, int startDepth, int endDepth, int periodId, int LSNId, int ASN1Id, int ASN2Id, Integer ASN3Id)
    {
        this.artifactId = artifactId;
        this.fieldSiteNumber = fieldSiteNumber;
        this.areaLetter = areaLetter;
        this.unitNumber = unitNumber;
        this.startDepth = startDepth;
        this.endDepth = endDepth;
        this.periodId = periodId;
        this.LSNId = LSNId;
        this.ASN1Id = ASN1Id;
        this.ASN2Id = ASN2Id;
        this.ASN3Id = ASN3Id;
    }

    public int getFieldSiteNumber()
    {
        return fieldSiteNumber;
    }

    public String getAreaLetter()
    {
        return areaLetter;
    }

    public int getUnitNumber()
    {
        return unitNumber;
    }

    public int getStartDepth()
    {
        return startDepth;
    }

    public int getEndDepth()
    {
        return endDepth;
    }

    public int getPeriodId()
    {
        return periodId;
    }

    public int getLSNId()
    {
        return LSNId;
    }

    public int getASN1Id()
    {
        return ASN1Id;
    }

    public int getASN2Id()
    {
        return ASN2Id;
    }

    public Integer getASN3Id()
    {
        return ASN3Id;
    }
}
