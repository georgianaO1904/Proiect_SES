package com.ehealth.application.appointeeth.data.models;

import android.app.Service;

import java.util.ArrayList;
import java.util.HashMap;

public class UserObject {

    private String userId;
    public String userName;
    private String userEmail;
    private String userType;
    private String userCuim;
    private String userPhone;
    private HashMap<String, CliniqueObject> cliniques = new HashMap<String, CliniqueObject>();
    private HashMap<String, ArrayList<String>> program = new HashMap<String, ArrayList<String>>();
    private HashMap<String, ServiceObject> services = new HashMap<String, ServiceObject>();

    public UserObject() { }

    public UserObject(String userId, String userName, String userEmail, String userType, String userPhone, String userCuim) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userType = userType;
        this.userCuim = userCuim;
        this.userPhone = userPhone;
    }

    public UserObject(String userId, String userName, String userEmail, String userPhone, String userType) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userType = userType;
        this.userPhone = userPhone;

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

    public String getUserName() { return userName; }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPhone(String userPhone){this.userPhone = userPhone; }

    public String getUserPhone(String userPhone) {return userPhone; }

    public String getUserCuim() {
        return userCuim;
    }

    public void setCliniques(HashMap<String, CliniqueObject> cliniques) {
        this.cliniques = cliniques;
    }

    public HashMap<String, CliniqueObject> getCliniques() {
        return cliniques;
    }

    public HashMap<String, ServiceObject> getServices() {
        return services;
    }

    public void setServices(HashMap<String, ServiceObject> services) {
        this.services = services;
    }

    public void setUserCuim(String userCuim) {
        this.userCuim = userCuim;
    }

    public HashMap<String, ArrayList<String>> getProgram() {
        return program;
    }

    public void setProgram(HashMap<String, ArrayList<String>> program) {
        this.program = program;
    }

    public String getUserPhone() {
        return userPhone;
    }
}
