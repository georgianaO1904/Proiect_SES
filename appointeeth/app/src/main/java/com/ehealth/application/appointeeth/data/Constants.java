package com.ehealth.application.appointeeth.data;

import java.util.ArrayList;
import java.util.Arrays;

public class Constants {
    public static final String PACIENT_USER_TYPE = "pacient";
    public static final String DOCTOR_USER_TYPE = "doctor";

    public static final ArrayList<String> TIME_SLOTS_ARRAY = new ArrayList<String>(
            Arrays.asList(
                    "8:00 - 8:30", "8:30 - 9:00",
                    "9:00 - 9:30", "9:30 - 10:00",
                    "10:00 - 10:30", "10:30 - 11:00",
                    "11:00 - 11:30", "11:30 - 12:00",
                    "12:00 - 12:30", "12:30 - 13:00",
                    "13:00 - 13:30", "13:30 - 14:00",
                    "14:00 - 14:30", "14:30 - 15:00",
                    "15:00 - 15:30", "15:30 - 16:00",
                    "16:00 - 16:30", "16:30 - 17:00",
                    "17:00 - 17:30", "17:30 - 18:00",
                    "18:00 - 18:30", "18:30 - 19:00",
                    "19:00 - 19:30", "19:30 - 20:00"
    ));

}
