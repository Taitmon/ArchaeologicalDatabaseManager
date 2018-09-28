package controllers;

import com.google.common.io.Files;
import models.*;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ArtifactController extends Controller
{
    private JPAApi jpaApi;
    private FormFactory formFactory;

    @Inject
    public ArtifactController(JPAApi jpaApi, FormFactory formFactory)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    @Transactional(readOnly = true)
    public Result getNewArtifact()
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        String sql = "SELECT l FROM LSN l";
        List<LSN> lsnList = jpaApi.em().createQuery(sql, LSN.class).getResultList();

        sql = "SELECT a FROM ASN1 a";
        List<ASN1> asn1List = jpaApi.em().createQuery(sql, ASN1.class).getResultList();

        sql = "SELECT a FROM ASN2 a";
        List<ASN2> asn2List = jpaApi.em().createQuery(sql, ASN2.class).getResultList();

        sql = "SELECT a FROM ASN3 a";
        List<ASN3> asn3List = jpaApi.em().createQuery(sql, ASN3.class).getResultList();

        sql = "SELECT l FROM LabTechnician l";
        List<LabTechnician> labTechnicians = jpaApi.em().createQuery(sql, LabTechnician.class).getResultList();

        if(form.get("UnitId") != null)
        {
            session().put("UnitId", form.get("UnitId"));
        }

        String unitIdString = session().get("UnitId");
        int unitId = Integer.parseInt(unitIdString);

        sql = "SELECT u FROM Unit u WHERE u.unitId = :unitId";
        Unit currentUnit = jpaApi.em().createQuery(sql, Unit.class).setParameter("unitId", unitId).getSingleResult();

        return ok(views.html.newartifact.render(lsnList, asn1List, asn2List, asn3List, labTechnicians, currentUnit));
    }

    @Transactional
    public Result postNewArtifact()
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        String unitIdString = session().get("UnitId");
        int unitId = Integer.parseInt(unitIdString);

        String lsnString = form.get("LSN");
        int lsnId = Integer.parseInt(lsnString);

        String asn1String = form.get("ASN1");
        int asn1Id = Integer.parseInt(asn1String);

        String asn2String = form.get("ASN2");
        int asn2Id = Integer.parseInt(asn2String);

        String asn3String = form.get("ASN3");
        Integer asn3Id;
        if(asn3String.equals(""))
        {
            asn3Id = null;
        }
        else
        {
            asn3Id = Integer.parseInt(asn3String);
        }

        String additionalDescription = form.get("description");
        if(additionalDescription.isEmpty())
        {
            additionalDescription = null;
        }

        String quantityString = form.get("quantity");
        int quantity = Integer.parseInt(quantityString);

        String weightString = form.get("weight");
        BigDecimal weight = new BigDecimal(weightString);

        String labTechString = form.get("labtechnician");
        int labTechnicianId = Integer.parseInt(labTechString);

        String dateString = form.get("dateanalyzed");
        LocalDate dateAnalyzed = LocalDate.parse(dateString);

        String imageKey = form.get("imagekey");
        if(imageKey.isEmpty())
        {
            imageKey = null;
        }

        String notes = form.get("notes");
        if(notes.isEmpty())
        {
            notes = null;
        }

        Http.MultipartFormData<File> formData = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> filePart = formData.getFile("image");
        File file = filePart.getFile();
        byte[] picture;



        Artifact newArtifact = new Artifact();

        newArtifact.setUnitId(unitId);
        //period set to type 2 for project purposes
        newArtifact.setPeriodId(2);
        newArtifact.setLSNId(lsnId);
        newArtifact.setASN1Id(asn1Id);
        newArtifact.setASN2Id(asn2Id);
        newArtifact.setASN3Id(asn3Id);
        newArtifact.setAdditionalDescription(additionalDescription);
        newArtifact.setQuantity(quantity);
        newArtifact.setWeight(weight);
        newArtifact.setLabTechnicianId(labTechnicianId);
        newArtifact.setDateAnalyzed(dateAnalyzed);
        newArtifact.setNotes(notes);
        newArtifact.setImageKey(imageKey);

        try
        {
            picture = Files.toByteArray(file);
            if(picture.length > 0)
            {
                newArtifact.setImage(picture);
            }
            else
            {
                newArtifact.setImage(null);
            }

        }
        catch (Exception e)
        {
            picture = null;
        }

        jpaApi.em().persist(newArtifact);


        return redirect("/newartifact");
    }

    @Transactional(readOnly = true)
    public Result getExistingArtifacts(int unitId)
    {
        String sql = "SELECT NEW models.ArtifactTable(a.artifactId, l.LSNName, e.ASN1Name, f.ASN2Name, g.ASN3Name, a.quantity, a.weight, a.dateAnalyzed, a.imageKey) " +
                " FROM Artifact a " +
                " JOIN LSN l ON l.LSNId = a.LSNId " +
                " JOIN ASN1 e ON e.ASN1Id = a.ASN1Id " +
                " JOIN ASN2 f ON f.ASN2Id = a.ASN2Id " +
                " LEFT OUTER JOIN ASN3 g ON g.ASN3Id = a.ASN3Id " +
                " WHERE a.unitId = :unitId";

        List<ArtifactTable> artifacts = jpaApi.em().createQuery(sql, ArtifactTable.class).setParameter("unitId", unitId).getResultList();

        sql = "SELECT u FROM Unit u WHERE u.unitId = :unitId";
        Unit currentUnit = jpaApi.em().createQuery(sql, Unit.class).setParameter("unitId", unitId).getSingleResult();

        return ok(views.html.existingartifacts.render(artifacts, currentUnit));
    }


    @Transactional(readOnly = true)
    public Result getArtifactImage(int artifactId)
    {
        String sql = "SELECT a FROM Artifact a WHERE a.artifactId = :artifactId";
        Artifact artifact = jpaApi.em().createQuery(sql, Artifact.class).setParameter("artifactId", artifactId).getSingleResult();

        return ok(artifact.getImage()).as("image/jpg");
    }

    @Transactional(readOnly = true)
    public Result getCatalogNumbers(int unitId)
    {
        String sql = "SELECT NEW models.CatalogNumber(u.unitId, s.accessionNumber, u.fieldSiteNumber, a.LSNId, a.ASN1Id, a.ASN2Id, a.ASN3Id) FROM Unit u " +
                "JOIN Artifact a ON a.unitId = u.unitId " +
                "JOIN SiteInfo s ON s.siteInfoId = u.siteInfoId WHERE u.unitId = :unitId";
        List<CatalogNumber> catalogNumbers = jpaApi.em().createQuery(sql, CatalogNumber.class).setParameter("unitId", unitId).getResultList();

        sql = "SELECT u FROM Unit u WHERE u.unitId = :unitId";
        Unit currentUnit = jpaApi.em().createQuery(sql, Unit.class).setParameter("unitId", unitId).getSingleResult();

        String unitIdString = Integer.toString(unitId);
        session().put("UnitId", unitIdString);
        return ok(views.html.catalognumbers.render(catalogNumbers, currentUnit));
    }

    @Transactional(readOnly = true)
    public Result getEditArtifact(int artifactId)
    {
        String sql = "SELECT a FROM Artifact a WHERE a.artifactId = :artifactId";
        Artifact artifact = jpaApi.em().createQuery(sql, Artifact.class).setParameter("artifactId", artifactId).getSingleResult();

        String unitIdString = session().get("UnitId");
        int unitId = Integer.parseInt(unitIdString);

        sql = "SELECT u FROM Unit u WHERE u.unitId = :unitId";
        Unit currentUnit = jpaApi.em().createQuery(sql, Unit.class).setParameter("unitId", unitId).getSingleResult();

        sql = "SELECT l FROM LSN l";
        List<LSN> lsnList = jpaApi.em().createQuery(sql, LSN.class).getResultList();

        sql = "SELECT a FROM ASN1 a";
        List<ASN1> asn1List = jpaApi.em().createQuery(sql, ASN1.class).getResultList();

        sql = "SELECT a FROM ASN2 a";
        List<ASN2> asn2List = jpaApi.em().createQuery(sql, ASN2.class).getResultList();

        sql = "SELECT a FROM ASN3 a";
        List<ASN3> asn3List = jpaApi.em().createQuery(sql, ASN3.class).getResultList();

        sql = "SELECT l FROM LabTechnician l";
        List<LabTechnician> labTechnicians = jpaApi.em().createQuery(sql, LabTechnician.class).getResultList();

        return ok(views.html.editartifact.render(artifact, currentUnit,lsnList, asn1List, asn2List, asn3List, labTechnicians));
    }

    @Transactional
    public Result postEditArtifact(int artifactId)
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        String unitIdString = session().get("UnitId");
        int unitId = Integer.parseInt(unitIdString);

        String lsnString = form.get("LSN");
        int lsnId = Integer.parseInt(lsnString);

        String asn1String = form.get("ASN1");
        int asn1Id = Integer.parseInt(asn1String);

        String asn2String = form.get("ASN2");
        int asn2Id = Integer.parseInt(asn2String);

        String asn3String = form.get("ASN3");
        Integer asn3Id;
        if(asn3String.equals(""))
        {
            asn3Id = null;
        }
        else
        {
            asn3Id = Integer.parseInt(asn3String);
        }

        String additionalDescription = form.get("description");
        if(additionalDescription.isEmpty())
        {
            additionalDescription = null;
        }

        String quantityString = form.get("quantity");
        int quantity = Integer.parseInt(quantityString);

        String weightString = form.get("weight");
        BigDecimal weight = new BigDecimal(weightString);

        String labTechString = form.get("labtechnician");
        int labTechnicianId = Integer.parseInt(labTechString);

        String dateString = form.get("dateanalyzed");
        LocalDate dateAnalyzed = LocalDate.parse(dateString);

        String imageKey = form.get("imagekey");
        if(imageKey.isEmpty())
        {
            imageKey = null;
        }

        String notes = form.get("notes");
        if(notes.isEmpty())
        {
            notes = null;
        }


        Http.MultipartFormData<File> formData = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> filePart = formData.getFile("image");
        File file = filePart.getFile();

        byte[] picture;



        String sql = "SELECT a FROM Artifact a WHERE a.artifactId = :artifactId";

        Artifact artifact = jpaApi.em().createQuery(sql, Artifact.class).setParameter("artifactId", artifactId).getSingleResult();

        artifact.setUnitId(unitId);
        //period set to type 2 for project purposes
        artifact.setPeriodId(2);
        artifact.setLSNId(lsnId);
        artifact.setASN1Id(asn1Id);
        artifact.setASN2Id(asn2Id);
        artifact.setASN3Id(asn3Id);
        artifact.setAdditionalDescription(additionalDescription);
        artifact.setQuantity(quantity);
        artifact.setWeight(weight);
        artifact.setLabTechnicianId(labTechnicianId);
        artifact.setDateAnalyzed(dateAnalyzed);
        artifact.setNotes(notes);
        artifact.setImageKey(imageKey);
        try
        {
            picture = Files.toByteArray(file);
            if(picture.length > 0)
            {
                artifact.setImage(picture);
            }
            else
            {
                artifact.setImage(null);
            }

        }
        catch (Exception e)
        {
            picture = null;
        }

        return redirect("/existingartifacts/" + unitId);
    }
}
