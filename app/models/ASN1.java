package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ASN1
{
    @Id private int ASN1Id;
    private String ASN1Name;

    public int getASN1Id()
    {
        return ASN1Id;
    }

    public String getASN1Name()
    {
        return ASN1Name;
    }
}
