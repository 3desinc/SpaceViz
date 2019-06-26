package controllers;

import Models.User;
import io.ebean.Ebean;
import io.ebean.Finder;
import io.ebean.annotation.NotNull;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.AppPages.UserPage;

import javax.inject.Inject;
import java.net.UnknownServiceException;

public class LoginController extends Controller {

    @Inject
    FormFactory formFactory;


    public Result authenticateUser(){
        Form<User> form = formFactory.form(User.class).bindFromRequest(); //TODO Figure out bindFromRequestData

        User.errorMsgBox("This works", "Success");
        String email = form.rawData().get("email");
        String password = form.rawData().get("password");


        //Checks if there is any text in the input fields
        if(email == null || password == null){
            User.errorMsgBox("Email or password is invalid" + email + " " + password, "Error");
            return redirect(routes.HomeController.index());

        }

            User user = User.find.byId(email);

            User.errorMsgBox("Email: " + user.email + "Password : " + user.password, "Report");


        //Checks if the password is correct or if there is such user in the database
        if(user == null || !password.equals(user.getPassword())){
            User.errorMsgBox("User does not exist or password is incorrect", "Error");
            return redirect(routes.HomeController.index());
        }

      /*  if(user.isAdmin()){
            User.errorMsgBox("Redirecting to admin home page", "Logging in...");
            return redirect(routes.UserpageController.adminPage());
        }
*/
        User.errorMsgBox("Redirecting to your home page", "Logging in...");
        return ok(UserPage.render());

    }

    public Result forgotUser() {

        return ok(views.html.loginPage.forgotUser.render());
    }

    public Result forgotPass(){

        return ok(views.html.loginPage.forgotPass.render());
    }

}
