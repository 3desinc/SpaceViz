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
    @Constraints.Required
    private String firstName;

    @Constraints.Required
    private String lastName;

    @Constraints.Email
    @Constraints.Required
    private String email;*/

    @Constraints.Required
    private String password;

    @Constraints.Required
    private int userId;

    @Constraints.Required
    private boolean admin;

    @Constraints.Required
    private boolean student;

    @Constraints.Required
    private boolean superAdmin;

    @Constraints.Required
    private boolean sysAdmin;




    public User() {
    }

    //Constructor
    public User(String firstName, String lastName, String email, String password, int userId,
                boolean admin, boolean student, boolean superAdmin, boolean sysAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userId = userId;
        this.admin = admin;
        this.student = student;
        this.superAdmin = superAdmin;
        this.sysAdmin = sysAdmin;
    }

    //Methods

    public static Finder<String, User> find = new Finder<>(User.class);


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

    public String getEmail(){

        return email;
    }

    public int getUserId(){

        return userId;
    }
    
    public String getPassword(){

        return password;
    }

    public boolean isAdmin(){

        return admin;
    }

    public static void errorMsgBox(String msgString, String titlebar){

        JOptionPane.showMessageDialog(null, msgString, "Infobox: " + titlebar, JOptionPane.INFORMATION_MESSAGE);
    }
}
