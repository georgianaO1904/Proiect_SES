package com.ehealth.application.appointeeth.data.models;

public class AppointmentObject {
    private String clinique, timeslot, service;

    public String getClinique() {
        return clinique;
    }

    public void setClinique(String clinique) {
        this.clinique = clinique;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public AppointmentObject(String Clinique, String timeslot, String service) {
        this.clinique = Clinique;
        this.timeslot = timeslot;
        this.service = service;
    }

    public AppointmentObject() { }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
