package com.ehealth.application.appointeeth.data.models;

public class CliniqueObject {

    private String name, location;

    public CliniqueObject() { }

    public CliniqueObject(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
