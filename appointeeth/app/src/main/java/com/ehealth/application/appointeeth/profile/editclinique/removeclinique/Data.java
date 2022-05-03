package com.ehealth.application.appointeeth.profile.editclinique.removeclinique;

import com.ehealth.application.appointeeth.data.models.CliniqueObject;

import java.util.ArrayList;

//todo: replace this with real data
public class Data {

    public static ArrayList<CliniqueObject> getData() {

        ArrayList<CliniqueObject> data = new ArrayList<>();

        data.add(new CliniqueObject("Regina Maria 1", "Strada Sperantei nr 1F"));
        data.add(new CliniqueObject("Regina Maria 2", "Strada Sperantei nr 1F"));
        data.add(new CliniqueObject("Regina Maria 3", "Strada Sperantei nr 1F"));
        data.add(new CliniqueObject("Regina Maria 4", "Strada Sperantei nr 1F"));
        data.add(new CliniqueObject("Regina Maria 5", "Strada Sperantei nr 1F"));
        data.add(new CliniqueObject("Regina Maria 6", "Strada Sperantei nr 1F"));
        data.add(new CliniqueObject("Regina Maria 7", "Strada Sperantei nr 1F"));

        return data;
    }

}