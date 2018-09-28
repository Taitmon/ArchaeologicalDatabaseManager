package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ASN3Count
{
    @Id private Integer ASN3Id;
    private String ASN3Name;
    private long numberOfArtifacts;
    private String rgb;

    public ASN3Count(Integer ASN3Id, String ASN3Name, long numberOfArtifacts)
    {
        this.ASN3Id = ASN3Id;
        this.ASN3Name = ASN3Name;
        this.numberOfArtifacts = numberOfArtifacts;
        setRgb();
    }

    public Integer getASN3Id()
    {
        return ASN3Id;
    }

    public String getASN3Name()
    {
        return ASN3Name;
    }

    public long getNumberOfArtifacts()
    {
        return numberOfArtifacts;
    }

    private void setRgb()
    {
        if(this.ASN3Id == 0)
        {
            this.rgb = "rgb(134,161,179)";
        }
        else
        {
            this.rgb = "rgb(153,0,0)";
        }
    }

    public String getRgb()
    {
        return rgb;
    }


}
