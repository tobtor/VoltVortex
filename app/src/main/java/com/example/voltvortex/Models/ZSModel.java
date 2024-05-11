package com.example.voltvortex.Models;

public class ZSModel {

    private int zsID;
    private int measuredComponentID;
    private int electricalProtectionID;
    private String typeOfProtection;
    private float multiplierOfProtection;
    private float measuredZS;
    private float result;
    private boolean isBZ;
    private boolean isBPE;
    private boolean isBK;
    private boolean isBKLAPKI;
    private boolean isWYRW;
    private boolean is2PRZEW;
    private boolean wasMeasured;

    // Kostruktor
    public ZSModel(int zsID, int measuredComponentID, int electricalProtectionID, String typeOfProtection,
                   float multiplierOfProtection, float measuredZS, float result, boolean isBZ, boolean isBPE,
                   boolean isBK, boolean isBKLAPKI, boolean isWYRW, boolean is2PRZEW, boolean wasMeasured) {
        this.zsID = zsID;
        this.measuredComponentID = measuredComponentID;
        this.electricalProtectionID = electricalProtectionID;
        this.typeOfProtection = typeOfProtection;
        this.multiplierOfProtection = multiplierOfProtection;
        this.measuredZS = measuredZS;
        this.result = result;
        this.isBZ = isBZ;
        this.isBPE = isBPE;
        this.isBK = isBK;
        this.isBKLAPKI = isBKLAPKI;
        this.isWYRW = isWYRW;
        this.is2PRZEW = is2PRZEW;
        this.wasMeasured = wasMeasured;
    }

    // Gettery i Settery
    public int getZsID() {
        return zsID;
    }

    public void setZsID(int zsID) {
        this.zsID = zsID;
    }

    public int getMeasuredComponentID() {
        return measuredComponentID;
    }

    public void setMeasuredComponentID(int measuredComponentID) {
        this.measuredComponentID = measuredComponentID;
    }

    public int getElectricalProtectionID() {
        return electricalProtectionID;
    }

    public void setElectricalProtectionID(int electricalProtectionID) {
        this.electricalProtectionID = electricalProtectionID;
    }

    public String getTypeOfProtection() {
        return typeOfProtection;
    }

    public void setTypeOfProtection(String typeOfProtection) {
        this.typeOfProtection = typeOfProtection;
    }

    public float getMultiplierOfProtection() {
        return multiplierOfProtection;
    }

    public void setMultiplierOfProtection(float multiplierOfProtection) {
        this.multiplierOfProtection = multiplierOfProtection;
    }

    public float getMeasuredZS() {
        return measuredZS;
    }

    public void setMeasuredZS(float measuredZS) {
        this.measuredZS = measuredZS;
    }

    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }

    public boolean isBZ() {
        return isBZ;
    }

    public void setBZ(boolean BZ) {
        isBZ = BZ;
    }

    public boolean isBPE() {
        return isBPE;
    }

    public void setBPE(boolean BPE) {
        isBPE = BPE;
    }

    public boolean isBK() {
        return isBK;
    }

    public void setBK(boolean BK) {
        isBK = BK;
    }

    public boolean isBKLAPKI() {
        return isBKLAPKI;
    }

    public void setBKLAPKI(boolean BKLAPKI) {
        isBKLAPKI = BKLAPKI;
    }

    public boolean isWYRW() {
        return isWYRW;
    }

    public void setWYRW(boolean WYRW) {
        isWYRW = WYRW;
    }

    public boolean isIs2PRZEW() {
        return is2PRZEW;
    }

    public void setIs2PRZEW(boolean is2PRZEW) {
        this.is2PRZEW = is2PRZEW;
    }

    public boolean isWasMeasured() {
        return wasMeasured;
    }

    public void setWasMeasured(boolean wasMeasured) {
        this.wasMeasured = wasMeasured;
    }
}
