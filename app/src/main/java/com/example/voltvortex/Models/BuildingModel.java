package com.example.voltvortex.Models;

public class BuildingModel {
    private int buildingID;
    private String buildingName;
    private String dateOfMeasurements;
    private String city;
    private String postcode;
    private String street;
    private String buildingNumber;
    private int projectID;
    private int contactPersonID;

    // Konstruktor
    public BuildingModel(int buildingID, String buildingName, String dateOfMeasurements, String city,
                         String postcode, String street, String buildingNumber, int projectID, int contactPersonID) {
        this.buildingID = buildingID;
        this.buildingName = buildingName;
        this.dateOfMeasurements = dateOfMeasurements;
        this.city = city;
        this.postcode = postcode;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.projectID = projectID;
        this.contactPersonID = contactPersonID;
    }

    // Gettery i Settery
    public int getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(int buildingID) {
        this.buildingID = buildingID;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getDateOfMeasurements() {
        return dateOfMeasurements;
    }

    public void setDateOfMeasurements(String dateOfMeasurements) {
        this.dateOfMeasurements = dateOfMeasurements;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getContactPersonID() {
        return contactPersonID;
    }

    public void setContactPersonID(int contactPersonID) {
        this.contactPersonID = contactPersonID;
    }
}
