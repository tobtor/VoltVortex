package com.example.voltvortex.Models;

public class FloorModel {

    private int floorId;
    private String floor;

    public FloorModel(int floorId, String floor) {
        this.floorId = floorId;
        this.floor = floor;
    }

    public FloorModel(String floor) {
        this.floorId = 0; // Default value for new records
        this.floor = floor;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }
}
