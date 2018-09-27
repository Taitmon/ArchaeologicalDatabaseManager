package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CatalogNumber
{
    @Id private int unitId;
    private String accessionNumber;
    private int fieldSiteNumber;
    private int LSNId;
    private int ASN1Id;
    private int ASN2Id;
    private Integer ASN3Id;

    public CatalogNumber(int unitId, String accessionNumber, int fieldSiteNumber, int LSNId, int ASN1Id, int ASN2Id, Integer ASN3Id)
    {
        this.unitId = unitId;
        this.accessionNumber = accessionNumber;
        this.fieldSiteNumber = fieldSiteNumber;
        this.LSNId = LSNId;
        this.ASN1Id = ASN1Id;
        this.ASN2Id = ASN2Id;
        this.ASN3Id = ASN3Id;
    }

    public int getUnitId()
    {
        return unitId;
    }

    public int getLSNId()
    {
        return LSNId;
    }

    public String getAccessionNumber()
    {
        return accessionNumber;
    }

    public int getFieldSiteNumber()
    {
        return fieldSiteNumber;
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
