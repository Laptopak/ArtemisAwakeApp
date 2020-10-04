package akhtemov.vladlen.artemisawakeapp;

public class Astronaut {

    public String id;
    public String name;
    public String isMale;
    public String age;
    public String height;
    public String mass;
    public String activity;
    public String dna;
    public String jobRole;
    public String levelOfImportance;
    public String timeZone;
    public String note;

    // Не удалять пустой конструктор! Нужен для работы Firebase
    public Astronaut() {

    }

    public Astronaut(String id, String name, String isMale, String age, String height, String mass, String activity, String dna, String jobRole, String levelOfImportance, String timeZone, String note) {
        this.id = id;
        this.name = name;
        this.isMale = isMale;
        this.age = age;
        this.height = height;
        this.mass = mass;
        this.activity = activity;
        this.dna = dna;
        this.jobRole = jobRole;
        this.levelOfImportance = levelOfImportance;
        this.timeZone = timeZone;
        this.note = note;
    }
}
