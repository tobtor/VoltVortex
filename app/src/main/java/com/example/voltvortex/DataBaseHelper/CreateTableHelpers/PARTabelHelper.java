package com.example.voltvortex.DataBaseHelper.CreateTableHelpers;

public class PARTabelHelper {

    private static final String TABLE_NAME = "ELECTRICAL_PERMANENT_POINTS_AND_RECOMMENDATIONS";
    private static final String COLUMN_PAR_ID = "PAR_ID";
    private static final String COLUMN_PAR_CONTENT = "PAR_CONTENT";
    private static final String COLUMN_PAR_IS_USED = "PAR_IS_USED";

    public static String getTableName_PAR(int buildingId) {
        return "ID" + buildingId + "_" +TABLE_NAME;
    }
    public static String getColumn_PAR_ID() {
        return COLUMN_PAR_ID;
    }
    public static String getColumnPARContent() {
        return COLUMN_PAR_CONTENT;
    }
    public static String getColumnPARIsUsed() {
        return COLUMN_PAR_IS_USED;
    }

    public static String createPARTable(int buildingId) {
        return " CREATE TABLE ID" + buildingId + "_" + TABLE_NAME +
                " (" + COLUMN_PAR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PAR_CONTENT + " TEXT NOT NULL, " +
                COLUMN_PAR_IS_USED + " INTEGER DEFAULT 1);";
    }
}