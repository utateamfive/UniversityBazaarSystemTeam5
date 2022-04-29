package com.teamfive.universitybazaarsystem;

public class User {
    /** User class to be stored in database*/

    private String UserName;
    private String UserEmail;
    private String UserPhone;
    private String UserID;
    private String UserPass;
    private Boolean isLoggedIn = false;
    public User(){}

    public User(String userName, String userEmail, String userPhone, String userID, String userPass) {
        UserName = userName;
        UserEmail = userEmail;
        UserPhone = userPhone;
        UserID = userID;
        UserPass = userPass;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUserPass() {
        return UserPass;
    }

    public void setUserPass(String userPass) {
        UserPass = userPass;
    }
}
