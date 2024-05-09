package com.example.voltvortex;

import org.jetbrains.annotations.NotNull;

public class ProjectModel {

    private int id;
    private String projectName;
    private boolean isSingleContactPerson;

    public ProjectModel(int id, String projectName, boolean isManyCities, boolean isManyContactPerson) {
        this.id = id;
        this.projectName = projectName;
        this.isSingleContactPerson = isManyContactPerson;
    }

    @NotNull
    @Override
    public String toString() {
        return "ProjectModel{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", isManyContactPerson=" + isSingleContactPerson +
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


    public boolean isSingleContactPerson() {
        return isSingleContactPerson;
    }

    public void setSingleContactPerson(boolean singleContactPerson) {
        isSingleContactPerson = singleContactPerson;
    }


}
