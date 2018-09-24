package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LabTechnician
{
    @Id private int labTechnicianId;
    private String labTechnicianName;
    private String labTechnicianInitials;

    public int getLabTechnicianId()
    {
        return labTechnicianId;
    }

    public String getLabTechnicianName()
    {
        return labTechnicianName;
    }

    public String getLabTechnicianInitials()
    {
        return labTechnicianInitials;
    }
}
