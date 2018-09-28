package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FlakeTypeCount
{
    @Id private int ASN1Id;
    private String ASN1Name;
    private long numberOfArtifacts;

    public FlakeTypeCount(int ASN1Id, String ASN1Name, long numberOfArtifacts)
    {
        this.ASN1Id = ASN1Id;
        this.ASN1Name = ASN1Name;
        this.numberOfArtifacts = numberOfArtifacts;
    }

    public int getASN1Id()
    {
        return ASN1Id;
    }

    public String getASN1Name()
    {
        return ASN1Name;
    }

    public long getNumberOfArtifacts()
    {
        return numberOfArtifacts;
    }
}
