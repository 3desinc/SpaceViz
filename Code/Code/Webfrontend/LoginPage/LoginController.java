package controllers;

import play.mvc.Controller;
import play.mvc.Result;


public class LoginController extends Controller {

    public Result forgotUser() {

        return ok(views.html.loginPage.forgotUser.render());
    }

    public Result forgotPass(){

        return ok(views.html.loginPage.forgotPass.render());
    }

}
