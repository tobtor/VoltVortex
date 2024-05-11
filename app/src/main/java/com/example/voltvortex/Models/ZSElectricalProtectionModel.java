package com.example.voltvortex.Models;

public class ZSElectricalProtectionModel {

    private int protectionID;
    private String protectionType;
    private float multiplierTNReceiver;
    private float multiplierTNSwitchgear;
    private float multiplierTTReceiver;
    private float multiplierTTSwitchgear;
    private float multiplierDC;

    // Konstruktor
    public ZSElectricalProtectionModel(int protectionID, String protectionType, float multiplierTNReceiver, float multiplierTNSwitchgear, float multiplierTTReceiver, float multiplierTTSwitchgear, float multiplierDC) {
        this.protectionID = protectionID;
        this.protectionType = protectionType;
        this.multiplierTNReceiver = multiplierTNReceiver;
        this.multiplierTNSwitchgear = multiplierTNSwitchgear;
        this.multiplierTTReceiver = multiplierTTReceiver;
        this.multiplierTTSwitchgear = multiplierTTSwitchgear;
        this.multiplierDC = multiplierDC;
    }

    // Gettery i Settery
    public int getProtectionID() {
        return protectionID;
    }

    public void setProtectionID(int protectionID) {
        this.protectionID = protectionID;
    }

    public String getProtectionType() {
        return protectionType;
    }

    public void setProtectionType(String protectionType) {
        this.protectionType = protectionType;
    }

    public float getMultiplierTNReceiver() {
        return multiplierTNReceiver;
    }

    public void setMultiplierTNReceiver(float multiplierTNReceiver) {
        this.multiplierTNReceiver = multiplierTNReceiver;
    }

    public float getMultiplierTNSwitchgear() {
        return multiplierTNSwitchgear;
    }

    public void setMultiplierTNSwitchgear(float multiplierTNSwitchgear) {
        this.multiplierTNSwitchgear = multiplierTNSwitchgear;
    }

    public float getMultiplierTTReceiver() {
        return multiplierTTReceiver;
    }

    public void setMultiplierTTReceiver(float multiplierTTReceiver) {
        this.multiplierTTReceiver = multiplierTTReceiver;
    }

    public float getMultiplierTTSwitchgear() {
        return multiplierTTSwitchgear;
    }

    public void setMultiplierTTSwitchgear(float multiplierTTSwitchgear) {
        this.multiplierTTSwitchgear = multiplierTTSwitchgear;
    }

    public float getMultiplierDC() {
        return multiplierDC;
    }

    public void setMultiplierDC(float multiplierDC) {
        this.multiplierDC = multiplierDC;
    }
}
