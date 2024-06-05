package com.example.voltvortex.Models;

public class RoomModel {

    private int roomId;
    private String room;
    private int floorId;

    public RoomModel(int roomId, String room, int floorId) {
        this.roomId = roomId;
        this.room = room;
        this.floorId = floorId;
    }

    public RoomModel(String room, int floorId) {
        this.roomId = 0;
        this.room = room;
        this.floorId = floorId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }
}