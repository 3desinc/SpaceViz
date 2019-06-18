//Event listener
var loginOK = document.getElementById('loginForm');
loginOK.addEventListener('submit', validLogin);


//Validation for the User credentials (Login)
function validLogin() {
    var userName = document.getElementById('userEmail').value;
    var passWord = document.getElementById('password').value;
    var redirect = null;

    if (userName === "admin@admin.com" && passWord === "admin"){ //TODO ADMIN Login in
        alert("Admin Login Successful");
        redirect = "adminhomepage";
    }
    else if (userName === "username@user.com" && passWord === "password"){ //TODO User Login in
        alert("User Login successful");
        redirect = "userhomepage";
    }
    else{
        alert("Login Unsuccessful!");
        return false;
        //document.getElementById('loginForm').action = "/index";
    }
    document.getElementById('loginForm').action = redirect; //sets the url to the next page
}

