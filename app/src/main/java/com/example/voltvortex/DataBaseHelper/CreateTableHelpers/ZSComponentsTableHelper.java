package com.example.voltvortex.DataBaseHelper.CreateTableHelpers;

public class ZSComponentsTableHelper {

    private static final String TABLE_NAME = "ELECTRICAL_ZS_COMPONENTS";
    private static final String COLUMN_ZS_COMPONENTS_ID = "ZS_COMPONENTS_ID";
    private static final String COLUMN_ZS_COMPONENTS = "ZS_COMPONENT";
    private static final String COLUMN_CLASS_OF_COMPONENT = "CLASS_OF_COMPONENT";

    public static String getTableName_ZS_COMPONENT() {
        return TABLE_NAME;
    }
    public static String getColumnZsComponentsId() {
        return COLUMN_ZS_COMPONENTS_ID;
    }
    public static String getColumnZsComponent() {
        return COLUMN_ZS_COMPONENTS;
    }
    public static String getColumnClassOfComponent() {
        return COLUMN_CLASS_OF_COMPONENT;
    }

    public static String createZSComponentsTable() {
        String query = " CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ZS_COMPONENTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ZS_COMPONENTS + " VARCHAR(25) NOT NULL, " +
                COLUMN_CLASS_OF_COMPONENT + " VARCHAR(25) NOT NULL);";
        return query;
    }
}