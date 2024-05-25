package com.example.voltvortex.Models;

public class ContactPersonModel {

    private int contactPersonID;
    private String name;
    private String firm;
    private String position;
    private String phone;
    private String email;

    // Konstruktor
    public ContactPersonModel(int contactPersonID, String name, String firm,
                              String position, String phone, String email) {
        this.contactPersonID = contactPersonID;
        this.name = name;
        this.firm = firm;
        this.position = position;
        this.phone = phone;
        this.email = email;
    }

    public ContactPersonModel(String name, String firm,
                              String position, String phone, String email) {
        this.contactPersonID = 200000;
        this.name = name;
        this.firm = firm;
        this.position = position;
        this.phone = phone;
        this.email = email;
    }

    // Gettery i Settery
    public int getContactPersonID() {
        return contactPersonID;
    }

    public void setContactPersonID(int contactPersonID) {
        this.contactPersonID = contactPersonID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
