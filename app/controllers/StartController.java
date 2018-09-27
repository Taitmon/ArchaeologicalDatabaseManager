package controllers;

import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

public class StartController extends Controller
{
    private JPAApi jpaApi;
    private FormFactory formFactory;

    @Inject
    public StartController(JPAApi jpaApi, FormFactory formFactory)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }
    public Result getHomePage()
    {
        session().clear();
        return ok(views.html.home.render());
    }

}
