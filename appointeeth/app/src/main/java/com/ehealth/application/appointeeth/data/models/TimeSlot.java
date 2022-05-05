package com.ehealth.application.appointeeth.data.models;

public class TimeSlot {
    private String timeSlot;
    private Boolean isSelected;

    public TimeSlot(String timeSlot, Boolean isSelected) {
        this.timeSlot = timeSlot;
        this.isSelected = isSelected;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
