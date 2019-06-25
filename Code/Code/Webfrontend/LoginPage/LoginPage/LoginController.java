package controllers;

import Models.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

public class LoginController extends Controller {

    @Inject
    FormFactory formFactory;


    public Result authenticateUser(){

        Form<User> form = formFactory.form(User.class).bindFromRequest();

        String email = form.rawData().get("email");
        String password = form.rawData().get("password");

        //Checks if there is any text in the input fields
        if(email == null || password == null){
            User.errorMsgBox("Email or password is invalid" + email + " " + password, "Error");
            return redirect(routes.HomeController.index());

        }

        User user = User.find.byId(email);

        //Checks if the password is correct or if there is such user in the database
        if(user == null || !password.equals(user.getPassword())){
            User.errorMsgBox("User does not exist or password is incorrect", "Error");
            return redirect(routes.HomeController.index());
        }

        if(user.isAdmin()){
            User.errorMsgBox("Redirecting to admin home page", "Logging in...");
            return redirect(routes.UserpageController.adminPage());
        }

        User.errorMsgBox("Redirecting to your home page", "Logging in...");
        return ok(views.html.AppPages.UserPage.render());
    }

    public Result forgotUser() {

        return ok(views.html.loginPage.forgotUser.render());
    }

    public Result forgotPass(){

        return ok(views.html.loginPage.forgotPass.render());
    }

}
