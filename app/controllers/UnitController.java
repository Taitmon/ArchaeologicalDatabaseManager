package controllers;

import models.Unit;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;


public class UnitController extends Controller
{
    private JPAApi jpaApi;
    private FormFactory formFactory;

    @Inject
    public UnitController(JPAApi jpaApi, FormFactory formFactory)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    public Result getNewUnit()
    {
        return ok(views.html.newunit.render());
    }

    @Transactional
    public Result postNewUnit()
    {
        String result = "";

        DynamicForm form = formFactory.form().bindFromRequest();
        Unit unit = new Unit();

        String fsnString = form.get("fieldsitenumber");
        int fieldSiteNumber = Integer.parseInt(fsnString);

        String areaLetter = form.get("arealetter");

        String unitNumberString = form.get("unitnumber");
        int unitNumber = Integer.parseInt(unitNumberString);

        String startDepthString = form.get("startdepth");
        int startDepth = Integer.parseInt(startDepthString);

        String endDepthString = form.get("enddepth");
        int endDepth = Integer.parseInt(endDepthString);

        boolean isDuplicate = checkDuplicate(fieldSiteNumber, areaLetter, unitNumber, startDepth);
        boolean goodDepths = checkDepths(startDepth, endDepth);

        unit.setFieldSiteNumber(fieldSiteNumber);
        unit.setAreaLetter(areaLetter);
        unit.setUnitNumber(unitNumber);
        unit.setStartDepth(startDepth);
        unit.setEndDepth(endDepth);
        //Assumed Site Info = 2 for project purposes
        unit.setSiteInfoId(2);


        boolean dataSaved;

        if (isDuplicate)
        {
            result = "Unit Already Exists";
            return ok(result);
        }
        else if (!goodDepths)
        {
            result = "Check your depths";
            return ok(result);
        }
        else
        {
            jpaApi.em().persist(unit);
            session().put("UnitId", Integer.toString(unit.getUnitId()));
            return redirect("/newartifact");
        }

    }


    private boolean checkDuplicate(int fieldSiteNumber, String areaLetter, int unitNumber, int startDepth)
    {
        boolean isDuplicate = false;

        String sql = "SELECT u FROM Unit u WHERE u.fieldSiteNumber = :fieldsitenumber";
        List<Unit> unitCheck = jpaApi.em().createQuery(sql, Unit.class).setParameter("fieldsitenumber", fieldSiteNumber).getResultList();

        if (unitCheck.size() > 0)
        {
            isDuplicate = true;
        }
        else
        {
            sql = "SELECT u FROM Unit u WHERE u.areaLetter = :arealetter AND u.unitNumber = :unitnumber AND u.startDepth = :startdepth";

            unitCheck = jpaApi.em().createQuery(sql, Unit.class).setParameter("arealetter", areaLetter)
                    .setParameter("unitnumber", unitNumber).setParameter("startdepth", startDepth).getResultList();

            if (unitCheck.size() > 0)
            {
                isDuplicate = true;
            }
        }

        return isDuplicate;
    }

    private boolean checkDepths(int startDepth, int endDepth)
    {
        boolean result = true;
        if (startDepth > endDepth || startDepth == 10)
        {

            result = false;
        }
        else if ((startDepth == 0 && endDepth != 20) || (startDepth >= 20 && endDepth != startDepth + 10))
        {
            result = false;
        }

        return result;

    }

    @Transactional
    public Result getUnitTable()
    {
        String sql = "SELECT u FROM Unit u ORDER BY u.fieldSiteNumber";
        List<Unit> units = jpaApi.em().createQuery(sql, Unit.class).getResultList();

        return ok(views.html.unittable.render(units));


    }


}
