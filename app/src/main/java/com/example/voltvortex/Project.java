package com.example.voltvortex;

public class Project {

    private String projectName;
    private boolean isManyCities;
    private boolean isManyContactPerson;

    public Project(String projectName, boolean isSingleCity, boolean isManyContactPerson) {
        this.projectName = projectName;
        this.isManyCities = isManyCities;
        this.isManyContactPerson = isManyContactPerson;
    }

    public boolean isManyCities() {
        return isManyCities;
    }
}
