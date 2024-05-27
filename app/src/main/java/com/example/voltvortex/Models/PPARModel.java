package com.example.voltvortex.Models;

public class PPARModel {

    private int pparID;
    private String content;

    // Konstruktor
    public PPARModel(int pparID, String content) {
        this.pparID = pparID;
        this.content = content;
    }

    public PPARModel(String content) {
        this.pparID = 500000;
        this.content = content;
    }

    // Gettery i Settery
    public int getPparID() {
        return pparID;
    }

    public void setPparID(int pparID) {
        this.pparID = pparID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
