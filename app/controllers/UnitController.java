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

        unit.setFieldSiteNumber(fieldSiteNumber);
        unit.setAreaLetter(areaLetter);
        unit.setUnitNumber(unitNumber);
        unit.setStartDepth(startDepth);
        unit.setEndDepth(endDepth);
        //Assumed Site Info = 2 for project purposes
        unit.setSiteInfoId(2);

        boolean isDuplicate = checkDuplicate(unit);
        boolean dataSaved;

        if(isDuplicate)
        {
            result = "Unit Already Exists";
            dataSaved = false;
        }
        else if (startDepth > endDepth || startDepth == 10)
        {
            result = "Check depth";
            dataSaved = false;
        }
        else if ((startDepth == 0 && endDepth != 20) || (endDepth != startDepth + 10))
        {
            result = "Check depth";
            dataSaved = false;
        }
        else
        {
            dataSaved = true;
        }

        if (dataSaved)
        {
            jpaApi.em().persist(unit);
            session().put("UnitId",Integer.toString(unit.getUnitId()));
            return redirect("/newartifact");
        }
        else
        {
            return ok(result + " ------ not saved");
        }

    }


    private boolean checkDuplicate(Unit enteredUnit)
    {
        boolean isDuplicate = false;

        String sql = "SELECT u FROM Unit u";

        List<Unit> units = jpaApi.em().createQuery(sql, Unit.class).getResultList();
        int fsnCheck;
        String areaCheck;
        int unitNumberCheck;
        int startDepth;


        for(Unit unit : units)
        {
            fsnCheck = unit.getFieldSiteNumber();
            areaCheck = unit.getAreaLetter();
            unitNumberCheck = unit.getUnitNumber();
            startDepth = unit.getStartDepth();

            if(fsnCheck == enteredUnit.getFieldSiteNumber())
            {
                isDuplicate = true;
            }
            else if(areaCheck.equals(enteredUnit.getAreaLetter()) && unitNumberCheck == enteredUnit.getUnitNumber() && startDepth == enteredUnit.getStartDepth())
            {
                isDuplicate = true;
            }

        }

        return isDuplicate;
    }


}
