package com.example.voltvortex.Models;

public class ProjectModel {

    private int projectID;
    private String projectName;
    private String firm;
    private String description;
    private int contactPersonID;
    private boolean isSingleContactPerson;

    // Konstruktor
    public ProjectModel(String projectName, String firm,
                        String description, int contactPersonID, boolean isManyContactPerson) {
        this.projectID = 100000;
        this.projectName = projectName;
        this.firm = firm;
        this.description = description;
        this.contactPersonID = contactPersonID;
        this.isSingleContactPerson = isManyContactPerson;
    }

    public ProjectModel(int id, String projectName, String firm,
                        String description, int contactPersonID, boolean isManyContactPerson) {
        this.projectID = id;
        this.projectName = projectName;
        this.firm = firm;
        this.description = description;
        this.contactPersonID = contactPersonID;
        this.isSingleContactPerson = isManyContactPerson;
    }

    public ProjectModel(String projectName, String firm,
                        String description, boolean isManyContactPerson) {
        this.projectID = 100000;
        this.projectName = projectName;
        this.firm = firm;
        this.description = description;
        this.isSingleContactPerson = isManyContactPerson;
    }

    public ProjectModel(int id, String projectName, String firm,
                        String description, boolean isManyContactPerson) {
        this.projectID = id;
        this.projectName = projectName;
        this.firm = firm;
        this.description = description;
        this.isSingleContactPerson = isManyContactPerson;
    }

    // Gettery i Settery
    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getContactPersonID() {
        return contactPersonID;
    }

    public void setContactPersonID(int contactPersonID) {
        this.contactPersonID = contactPersonID;
    }

    public boolean isSingleContactPerson() {
        return isSingleContactPerson;
    }

    public void setSingleContactPerson(boolean singleContactPerson) {
        isSingleContactPerson = singleContactPerson;
    }
}
