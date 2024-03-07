package com.example.voltvortex;

import org.jetbrains.annotations.NotNull;

public class ProjectModel {

    private int id;
    private String projectName;
    private boolean isManyCities;
    private boolean isManyContactPerson;

    public ProjectModel(int id, String projectName, boolean isManyCities, boolean isManyContactPerson) {
        this.id = id;
        this.projectName = projectName;
        this.isManyCities = isManyCities;
        this.isManyContactPerson = isManyContactPerson;
    }

    @NotNull
    @Override
    public String toString() {
        return "ProjectModel{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", isManyCities=" + isManyCities +
                ", isManyContactPerson=" + isManyContactPerson +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public boolean isManyCities() {
        return isManyCities;
    }

    public void setManyCities(boolean manyCities) {
        isManyCities = manyCities;
    }

    public boolean isManyContactPerson() {
        return isManyContactPerson;
    }

    public void setManyContactPerson(boolean manyContactPerson) {
        isManyContactPerson = manyContactPerson;
    }
}
