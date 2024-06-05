package com.example.voltvortex.DataBaseHelper.CreateTableHelpers;

public class RoomTableHelper {

    private static final String TABLE_NAME = "ROOM";
    private static final String COLUMN_ROOM_ID = "ROOM_ID";
    private static final String COLUMN_ROOM = "ROOM";
    private static final String COLUMN_FLOOR_ID = "FLOOR_ID"; // New column for foreign key

    public static String getTableName_Room() {
        return TABLE_NAME;
    }

    public static String getColumnRoomId() {
        return COLUMN_ROOM_ID;
    }

    public static String getColumnRoom() {
        return COLUMN_ROOM;
    }

    public static String getColumnFloorId() {
        return COLUMN_FLOOR_ID;
    }

    public static String createRoomTable(int buildingId) {
        return "CREATE TABLE IF NOT EXISTS ID" + buildingId + "_" + TABLE_NAME +
                " (" + COLUMN_ROOM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ROOM + " VARCHAR(80) NOT NULL, " +
                COLUMN_FLOOR_ID + " INTEGER, " +
                "FOREIGN KEY(" + COLUMN_FLOOR_ID + ") REFERENCES ID" + buildingId +
                "_FLOOR(" + FloorTableHelper.getColumnFloorId() + "));";
    }
}
