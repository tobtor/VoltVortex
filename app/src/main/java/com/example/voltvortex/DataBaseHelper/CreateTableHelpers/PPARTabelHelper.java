package com.example.voltvortex.DataBaseHelper.CreateTableHelpers;

public class PPARTabelHelper {

    private static final String TABLE_NAME = "ELECTRICAL_PERMANENT_POINTS_AND_RECOMMENDATIONS";
    private static final String COLUMN_PPAR_ID = "PPAR_ID";
    private static final String COLUMN_CONTENT = "CONTENT";

    public static String getTableName_BUILDING() {
        return TABLE_NAME;
    }
    public static String getColumn_BUILDING_ID() {
        return COLUMN_PPAR_ID;
    }
    public static String getColumn_CONTENT() {
        return COLUMN_CONTENT;
    }

    public static String createPPARTable() {
        String query = " CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_PPAR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CONTENT + " TEXT NOT NULL);";
        return query;
    }

}
