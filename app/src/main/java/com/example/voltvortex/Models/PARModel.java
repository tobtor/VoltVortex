package com.example.voltvortex.Models;

public class PARModel {

    private int parID;
    private String content;

    // Konstruktor
    public PARModel(int parID, String content) {
        this.parID = parID;
        this.content = content;
    }

    // Gettery i Settery
    public int getParID() {
        return parID;
    }

    public void setParID(int parID) {
        this.parID = parID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
