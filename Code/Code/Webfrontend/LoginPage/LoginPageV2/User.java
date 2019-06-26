package Models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.*;


@Entity
@Table(name = "users")
public class User extends Model {

    //Attributes
    @Id
  /*@Constraints.Required
    public String firstName;

    @Constraints.Required
    public String lastName;
*/
    @Constraints.Email
    @Constraints.Required
    public String email;

    @Constraints.Required
    public String password;

/*
    @Constraints.Required
    public boolean admin;

    @Constraints.Required
    public boolean student;

    @Constraints.Required
    public boolean superAdmin;

    @Constraints.Required
    public boolean sysAdmin;
*/



    public User() {
    }

    //Constructor
/*    public User(String firstName, String lastName, String email, String password,
                boolean admin, boolean student, boolean superAdmin, boolean sysAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.admin = admin;
        this.student = student;
        this.superAdmin = superAdmin;
        this.sysAdmin = sysAdmin;
    }
*/
    //Methods

    public static Finder<String, User> find = new Finder<>(User.class);
/*

    public String getFirstName(String userId){
        User user = User.find.byId(userId);
        if(user == null){
            return null;
        }
        return user.firstName;
    }


    public String getLastName(){

        return lastName;
    }
*/
    public String getEmail(String userId){
        User user = User.find.byId(userId);
        if(user == null){
            return null;
        }
        return user.email;
    }


    public String getPassword(){

        return password;
    }
/*
    public boolean isAdmin(){

        return admin;
    }
*/
    public static void errorMsgBox(String msgString, String titlebar){

        JOptionPane.showMessageDialog(null, msgString, "Infobox: " + titlebar, JOptionPane.INFORMATION_MESSAGE);
    }
}
