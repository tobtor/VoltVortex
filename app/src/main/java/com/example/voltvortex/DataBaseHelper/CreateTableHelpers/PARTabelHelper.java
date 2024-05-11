package com.example.voltvortex.DataBaseHelper.CreateTableHelpers;

public class PARTabelHelper {

    private static final String TABLE_NAME = "ELECTRICAL_PERMANENT_POINTS_AND_RECOMMENDATIONS";
    private static final String COLUMN_PAR_ID = "PAR_ID";
    private static final String COLUMN_PAR_CONTENT = "PAR_CONTENT";

    public static String getTableName_PAR() {
        return TABLE_NAME;
    }
    public static String getColumn_BUILDING_ID() {
        return COLUMN_PAR_ID;
    }
    public static String getColumnPARContent() {
        return COLUMN_PAR_CONTENT;
    }

    public static String createPPARTable() {
        String query = " CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_PAR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PAR_CONTENT + " TEXT NOT NULL);";
        return query;
    }
}
