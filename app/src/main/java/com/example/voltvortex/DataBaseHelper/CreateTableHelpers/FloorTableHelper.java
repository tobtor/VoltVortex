package com.example.voltvortex.DataBaseHelper.CreateTableHelpers;

public class FloorTableHelper {

    private static final String TABLE_NAME = "FLOOR";
    private static final String COLUMN_FLOOR_ID = "FLOOR_ID";
    private static final String COLUMN_FLOOR = "FLOOR";

    public static String getTableName_Floor(int buildingId) {
        return "ID" + buildingId + "_" + TABLE_NAME;
    }

    public static String getColumnFloorId() {
        return COLUMN_FLOOR_ID;
    }

    public static String getColumnFloor() {
        return COLUMN_FLOOR;
    }

    public static String createFloorTable(int buildingId) {
        return "CREATE TABLE IF NOT EXISTS ID" + buildingId + "_" + TABLE_NAME +
                " (" + COLUMN_FLOOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FLOOR + " VARCHAR(80) NOT NULL);";
    }
}
