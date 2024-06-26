package com.example.voltvortex.DataBaseHelper.CreateTableHelpers;

public class BuildingTableHelper {

    private static final String TABLE_NAME = "BUILDING";
    private static final String COLUMN_BUILDING_ID = "BUILDING_ID";
    private static final String COLUMN_BUILDING_NAME = "BUILDING_NAME";
    private static final String COLUMN_DATE_OF_MEASUREMENTS = "DATE_OF_MEASUREMENTS";
    private static final String COLUMN_CITY = "CITY";
    private static final String COLUMN_POSTCODE = "POSTCODE";
    private static final String COLUMN_STREET = "STREET";
    private static final String COLUMN_BUILDING_NUMBER = "BUILDING_NUMBER";
    private static final String COLUMN_PROJECT_ID = "PROJECT_ID";
    private static final String COLUMN_CONTACT_PERSON_ID = "CONTACT_PERSON_ID";

    public static String getTableName_BUILDING() {
        return TABLE_NAME;
    }

    public static String getColumn_BUILDING_ID() {
        return COLUMN_BUILDING_ID;
    }

    public static String getColumn_BUILDING_NAME() {
        return COLUMN_BUILDING_NAME;
    }

    public static String getColumn_DATE_OF_MEASUREMENTS() {
        return COLUMN_DATE_OF_MEASUREMENTS;
    }

    public static String getColumn_CITY() {
        return COLUMN_CITY;
    }

    public static String getColumn_POSTCODE() {
        return COLUMN_POSTCODE;
    }

    public static String getColumn_STREET() {
        return COLUMN_STREET;
    }

    public static String getColumn_BUILDING_NUMBER() {
        return COLUMN_BUILDING_NUMBER;
    }

    public static String getColumn_PROJECT_ID() {
        return COLUMN_PROJECT_ID;
    }

    public static String getColumn_CONTACT_PERSON_ID() {
        return COLUMN_CONTACT_PERSON_ID;
    }

    public static String createBuildingTable() {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_BUILDING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BUILDING_NAME + " VARCHAR(127) NOT NULL, " +
                COLUMN_DATE_OF_MEASUREMENTS + " DATE NOT NULL, " +
                COLUMN_CITY + " VARCHAR(30) NOT NULL, " +
                COLUMN_POSTCODE + " VARCHAR(5) NOT NULL, " +
                COLUMN_STREET + " VARCHAR(60) NOT NULL, " +
                COLUMN_BUILDING_NUMBER + " VARCHAR(10) NOT NULL, " +
                COLUMN_PROJECT_ID + " INTEGER, " +
                COLUMN_CONTACT_PERSON_ID + " INTEGER, " +
                "FOREIGN KEY (" + COLUMN_PROJECT_ID + ") REFERENCES " + ProjectTableHelper.getTableName_PROJECT() +
                "(" + ProjectTableHelper.getColumnProjectId() + "), " +
                "FOREIGN KEY (" + COLUMN_CONTACT_PERSON_ID + ") REFERENCES " + ContactPersonTableHelper.getTableName_CONTACT_PERSON() +
                "(" + ContactPersonTableHelper.getColumn_CONTACT_PERSON_ID() + "));";
        return query;
    }
}