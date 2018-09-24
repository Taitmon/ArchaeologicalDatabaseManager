package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ASN3
{
    @Id private int ASN3Id;
    private String ASN3Name;

    public int getASN3Id()
    {
        return ASN3Id;
    }

    public String getASN3Name()
    {
        return ASN3Name;
    }
}
