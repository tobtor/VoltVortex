package com.example.voltvortex.DataBaseHelper.CreateTableHelpers;

public class FloorAndRoomTableHelper {

    private static final String TABLE_NAME = "FLOOR_AND_ROOM";
    private static final String COLUMN_ROOM_ID = "ROOM_ID";
    private static final String COLUMN_ROOM = "ROOM";
    private static final String COLUMN_FLOOR = "FLOOR";

    public static String getTableName_FloorAndRoom() {
        return TABLE_NAME;
    }

    public static String getColumnRoomId() {
        return COLUMN_ROOM_ID;
    }

    public static String getColumnRoom() {
        return COLUMN_ROOM;
    }

    public static String getColumnFloor() {
        return COLUMN_FLOOR;
    }

    public static String createFloorAndRoomTable(int buildingId) {
        return "CREATE TABLE IF NOT EXISTS ID" + buildingId + "_" + TABLE_NAME +
                " (" + COLUMN_ROOM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ROOM + " VARCHAR(80) NOT NULL, " +
                COLUMN_FLOOR + " VARCHAR(80) NOT NULL);";
    }
}