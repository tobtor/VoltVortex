package com.example.voltvortex;

public class Project {

    private String projectName;
    private boolean isSingleCity;
    private boolean isSingleContactPerson;

    public Project(String projectName, boolean isSingleCity, boolean isSingleContactPerson) {
        this.projectName = projectName;
        this.isSingleCity = isSingleCity;
        this.isSingleContactPerson = isSingleContactPerson;
    }

    public boolean isSingleCity() {
        return isSingleCity;
    }
}
