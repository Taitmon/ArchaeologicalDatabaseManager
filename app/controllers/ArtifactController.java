package controllers;

import models.*;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
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






        return ok(views.html.newartifact.render(lsnList, asn1List, asn2List, asn3List, labTechnicians));
    }

    @Transactional
    public Result postNewArtifact()
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String lsnString = form.get("LSN");
        String unitNumber = session().get("UnitId");



        return ok("test");
    }

}
