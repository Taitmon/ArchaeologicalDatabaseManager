package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Artifact
{

    @Id private int artifactId;
    private int unitId;
    private int periodId;
    private int LSNId;
    private int ASN1Id;
    private int ASN2Id;
    private int ASN3Id;
    private String additionalDescription;
    private int quantity;
    private BigDecimal weight;
    private int labTechnicianId;
    private LocalDate dateAnalyzed;


    public int getArtifactId()
    {
        return artifactId;
    }

    public void setArtifactId(int artifactId)
    {
        this.artifactId = artifactId;
    }

    public int getUnitId()
    {
        return unitId;
    }

    public void setUnitId(int unitId)
    {
        this.unitId = unitId;
    }

    public int getPeriodId()
    {
        return periodId;
    }

    public void setPeriodId(int periodId)
    {
        this.periodId = periodId;
    }

    public int getLSNId()
    {
        return LSNId;
    }

    public void setLSNId(int LSNId)
    {
        this.LSNId = LSNId;
    }

    public int getASN1Id()
    {
        return ASN1Id;
    }

    public void setASN1Id(int ASN1Id)
    {
        this.ASN1Id = ASN1Id;
    }

    public int getASN2Id()
    {
        return ASN2Id;
    }

    public void setASN2Id(int ASN2Id)
    {
        this.ASN2Id = ASN2Id;
    }

    public int getASN3Id()
    {
        return ASN3Id;
    }

    public void setASN3Id(int ASN3Id)
    {
        this.ASN3Id = ASN3Id;
    }

    public String getAdditionalDescription()
    {
        return additionalDescription;
    }

    public void setAdditionalDescription(String additionalDescription)
    {
        this.additionalDescription = additionalDescription;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public BigDecimal getWeight()
    {
        return weight;
    }

    public void setWeight(BigDecimal weight)
    {
        this.weight = weight;
    }

    public int getLabTechnicianId()
    {
        return labTechnicianId;
    }

    public void setLabTechnicianId(int labTechnicianId)
    {
        this.labTechnicianId = labTechnicianId;
    }

    public LocalDate getDateAnalyzed()
    {
        return dateAnalyzed;
    }

    public void setDateAnalyzed(LocalDate dateAnalyzed)
    {
        this.dateAnalyzed = dateAnalyzed;
    }

}
