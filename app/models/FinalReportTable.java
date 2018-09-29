package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class FinalReportTable
{
    //a.artifactId, u.fieldSiteNumber, u.areaLetter, u.unitNumber, CONCAT(u.startDepth, '-', u.endDepth) AS 'depth', a.LSNId, w.LSNName,
    //a.ASN1Id, g.ASN1Name, a.ASN2Id, f.ASN2Name, a.ASN3Id, z.ASN3Name, a.quantity, a.weight, a.dateAnalyzed, l.labTechnicianInitials

    @Id private int artifactId;
    private int fieldSiteNumber;
    private String areaLetter;
    private int unitNumber;
    private int startDepth;
    private int endDepth;
    private int LSNId;
    private String LSNName;
    private int ASN1Id;
    private String ASN1Name;
    private int ASN2Id;
    private String ASN2Name;
    private Integer ASN3id;
    private String ASN3Name;
    private int quantity;
    private BigDecimal weight;
    private LocalDate dateAnalyzed;
    private String labTechnicianInitials;
    private String catalogSuffix;


    public FinalReportTable(int artifactId, int fieldSiteNumber, String areaLetter, int unitNumber, int startDepth, int endDepth, int LSNId, String LSNName, int ASN1Id, String ASN1Name, int ASN2Id, String ASN2Name, Integer ASN3id, String ASN3Name, int quantity, BigDecimal weight, LocalDate dateAnalyzed, String labTechnicianInitials)
    {
        this.artifactId = artifactId;
        this.fieldSiteNumber = fieldSiteNumber;
        this.areaLetter = areaLetter;
        this.unitNumber = unitNumber;
        this.startDepth = startDepth;
        this.endDepth = endDepth;
        this.LSNId = LSNId;
        this.LSNName = LSNName;
        this.ASN1Id = ASN1Id;
        this.ASN1Name = ASN1Name;
        this.ASN2Id = ASN2Id;
        this.ASN2Name = ASN2Name;
        this.ASN3id = ASN3id;
        this.ASN3Name = ASN3Name;
        this.quantity = quantity;
        this.weight = weight;
        this.dateAnalyzed = dateAnalyzed;
        this.labTechnicianInitials = labTechnicianInitials;
        setCatalogSuffix();
    }

    public int getArtifactId()
    {
        return artifactId;
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

    public int getLSNId()
    {
        return LSNId;
    }

    public String getLSNName()
    {
        return LSNName;
    }

    public int getASN1Id()
    {
        return ASN1Id;
    }

    public String getASN1Name()
    {
        return ASN1Name;
    }

    public int getASN2Id()
    {
        return ASN2Id;
    }

    public String getASN2Name()
    {
        return ASN2Name;
    }

    public Integer getASN3id()
    {
        return ASN3id;
    }

    public String getASN3Name()
    {
        return ASN3Name;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public BigDecimal getWeight()
    {
        return weight;
    }

    public LocalDate getDateAnalyzed()
    {
        return dateAnalyzed;
    }

    public String getLabTechnicianInitials()
    {
        return labTechnicianInitials;
    }

    private void  setCatalogSuffix()
    {
        String catalogSuffix;

        if(this.ASN3id == null)
        {
            catalogSuffix = "" + this.LSNId + "-" + this.ASN1Id + "-" + this.ASN2Id;

        }
        else
        {
            catalogSuffix = "" + this.LSNId + "-" + this.ASN1Id + "-" + this.ASN2Id + "-" + this.ASN3id;
        }

        this.catalogSuffix = catalogSuffix;

    }

    public String getCatalogSuffix()
    {
        return catalogSuffix;
    }
}
