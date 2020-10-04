package akhtemov.vladlen.artemisawakeapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Constant {
    public static final String POSITION = "position";
    public static final String NAME = "name";
    public static final String MALE = "male";
    public static final String AGE = "age";
    public static final String HEIGHT = "height";
    public static final String MASS = "mass";
    public static final String ACTIVITY = "activity";
    public static final String DNA = "dna";
    public static final String JOB_ROLE = "job_role";
    public static final String LEVEL_OF_IMPORTANCE = "level_of_importance";
    public static final String TIME_ZONE = "time_zone";
    public static final String NOTE = "note";

    public static final DatabaseReference ASTRONAUT_DATABASE_REFERENCE = FirebaseDatabase.getInstance().getReference("astronaut");
}
