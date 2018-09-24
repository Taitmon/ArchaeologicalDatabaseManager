package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ASN2
{
    @Id private int ASN2Id;
    private String ASN2Name;

    public int getASN2Id()
    {
        return ASN2Id;
    }

    public String getASN2Name()
    {
        return ASN2Name;
    }
}
