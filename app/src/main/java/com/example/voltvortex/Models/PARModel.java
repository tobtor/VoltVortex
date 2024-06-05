package com.example.voltvortex.Models;

public class PARModel {

    private int parID;
    private String content;
    private int isUsed;

    // Konstruktor
    public PARModel(int parID, String content, int isUsed) {
        this.parID = parID;
        this.content = content;
        this.isUsed = isUsed;
    }

    public PARModel(String content, int isUsed) {
        this.parID = 0;
        this.content = content;
        this.isUsed = isUsed;
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

    public int getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(int isUsed) {
        this.isUsed = isUsed;
    }
}
