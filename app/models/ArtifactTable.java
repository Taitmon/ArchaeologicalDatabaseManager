package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class ArtifactTable
{
    @Id private int artifactId;
    private String LSNName;
    private String ASN1Name;
    private String ASN2Name;
    private String ASN3Name;
    private int quantity;
    private BigDecimal weight;
    private LocalDate dateAnalyzed;
    private String imageKey;

    public ArtifactTable(int artifactId, String LSNName, String ASN1Name, String ASN2Name, String ASN3Name, int quantity, BigDecimal weight, LocalDate dateAnalyzed, String imageKey)
    {
        this.artifactId = artifactId;
        this.LSNName = LSNName;
        this.ASN1Name = ASN1Name;
        this.ASN2Name = ASN2Name;
        this.ASN3Name = ASN3Name;
        this.quantity = quantity;
        this.weight = weight;
        this.dateAnalyzed = dateAnalyzed;
        this.imageKey = imageKey;
    }

    public int getArtifactId()
    {
        return artifactId;
    }

    public String getImageKey()
    {
        return imageKey;
    }

    public String getLSNName()
    {
        return LSNName;
    }

    public String getASN1Name()
    {
        return ASN1Name;
    }

    public String getASN2Name()
    {
        return ASN2Name;
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



}
