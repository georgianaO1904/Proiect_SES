package com.ehealth.application.appointeeth.data.models;


import java.util.HashMap;

public class UserObject {

    private String userId;
    private String userEmail;
    private String userType;
    private String userCuim;
    public HashMap<String, CliniqueObject> cliniques = new HashMap<String, CliniqueObject>();

    public UserObject() { }

    public UserObject(String userId, String userEmail, String userType, String userCuim) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userType = userType;
        this.userCuim = userCuim;
    }

    public UserObject(String userId, String userEmail, String userType) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserCuim() {
        return userCuim;
    }

    public void setCliniques(HashMap<String, CliniqueObject> cliniques) {
        this.cliniques = cliniques;
    }

    public HashMap<String, CliniqueObject> getCliniques() {
        return cliniques;
    }

    public void setUserCuim(String userCuim) {
        this.userCuim = userCuim;
    }
}
