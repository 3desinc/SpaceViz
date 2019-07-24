package controllers;

import Utility.Hash;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import Models.User;

import javax.inject.Inject;

public class UserpageController extends Controller {

    @Inject
    FormFactory FF;

    public Result adminPage() {

        return ok(views.html.AppPages.AdminPage.render());
    }

    public Result userPage(Http.Request request){

        Form<User> form = FF.form(User.class);
        String secured = (request.session().data().get("Secured"));

        if(secured.equals("True")){
            return ok(views.html.AppPages.UserPage.render());
        }
        else{
            User.errorMsgBox("You are not logged in!!!", "ERROR");
            return ok(views.html.index.render(form));
        }
    }

    public Result settingsPage(Http.Request request){

        String secured = (request.session().data().get("Secured"));
        Form<User> form = FF.form(User.class);

        if(secured.equals("True")){
            return ok(views.html.AppPages.SettingsPage.render(form));
        }
        else{
            User.errorMsgBox("You are not logged in!!!", "ERROR");
            return ok(views.html.index.render(form));
        }
    }

    public Result changeSettings(Http.Request request) {

        String email = (request.session().data().get("Email"));

        User user = User.find.byId(email);
        Form<User> form = FF.form(User.class).bindFromRequest();
        String newHashedPassword;

        String newPassword = form.rawData().get("New Password: ");
        //String newFirstName = form.rawData().get("New First Name: ");
        //String newLastName = form.rawData().get("New Last Name: ");

        try {
            newHashedPassword = Hash.create(newPassword);
        } catch (Exception e) {
            return redirect(routes.UserpageController.settingsPage());
        }

        user.setPassword(newHashedPassword);
        user.update();

        return redirect(routes.UserpageController.userPage());
    }

    public Result canadaArmPage(){

        return ok(views.html.AppPages.RobotArm.render());

    }

    public Result logoutUser(){

        Form<User> form = FF.form(User.class);
        return ok(views.html.index.render(form)).withNewSession();
    }
/*
    public static boolean securedCheck(Http.Request request){

        String secured = (request.session().data().get("Secured"));

        if(secured.equals("True")){
            return true;
        }
        return false;
    }

 */
}

