package controllers;


import models.ArtifactTable;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class TableController extends Controller
{

    private JPAApi jpaApi;
    private FormFactory formFactory;

    @Inject
    public TableController(JPAApi jpaApi, FormFactory formFactory)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    @Transactional(readOnly = true)
    public Result getArtifactTable()
    {
        String sql = "SELECT NEW models.ArtifactTable(a.artifactId, u.fieldSiteNumber, u.areaLetter, u.unitNumber, u.startDepth, u.endDepth, a.periodId, a.LSNId, a.ASN1Id, a.ASN2Id, a.ASN3Id) " +
                "FROM Artifact a JOIN Unit u ON u.unitId = a.unitId";

        List<ArtifactTable> artifactTables = jpaApi.em().createQuery(sql, ArtifactTable.class).getResultList();


        return ok(views.html.artifacttable.render(artifactTables));
    }

}
