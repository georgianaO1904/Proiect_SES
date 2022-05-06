package com.ehealth.application.appointeeth.data.models;

import java.util.ArrayList;

public class ProgramObject {

    ArrayList<String> doctor_program;

    public ProgramObject(ArrayList<String> program) {
        this.doctor_program = program;
    }

    public void setDoctor_program(ArrayList<String> doctor_program) {
        this.doctor_program = doctor_program;
    }

    public ArrayList<String> getDoctor_program() {
        return doctor_program;
    }
}
