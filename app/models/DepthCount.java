package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DepthCount
{
    @Id private int startDepth;
    private int endDepth;
    private long numberOfArtifacts;
    private String depth;

    public DepthCount(int startDepth, int endDepth, long numberOfArtifacts)
    {
        this.startDepth = startDepth;
        this.endDepth = endDepth;
        this.numberOfArtifacts = numberOfArtifacts;
        this.depth = setDepth();
    }

    public int getStartDepth()
    {
        return startDepth;
    }

    public int getEndDepth()
    {
        return endDepth;
    }

    private String setDepth()
    {
        String depth = Integer.toString(this.startDepth) + "-" + Integer.toString(this.endDepth);
        return depth;
    }

    public String getDepth()
    {
        return depth;
    }

    public long getNumberOfArtifacts()
    {
        return numberOfArtifacts;
    }
}
