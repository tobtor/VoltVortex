package com.example.voltvortex.DataBaseHelper.CreateTableHelpers;

public class ProjectTableHelper {

    private static final String TABLE_NAME = "PROJECT";
    private static final String COLUMN_ID = "PROJECT_ID";
    private static final String COLUMN_PROJECT_NAME = "PROJECT_NAME";
    private static final String COLUMN_FIRM = "FIRM";
    private static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    private static final String COLUMN_CONTACT_PERSON_ID = "CONTACT_PERSON_ID";
    private static final String COLUMN_IS_SINGLE_CONTACT_PERSON = "IS_SINGLE_CONTACT_PERSON";
    private static final String COLUMN_DATE = "DATE";

    public static String getTableName_PROJECT() {
        return TABLE_NAME;
    }
    public static String getColumnId() {
        return COLUMN_ID;
    }
    public static String getColumnProjectName() {
        return COLUMN_PROJECT_NAME;
    }
    public static String getColumnFirm() {
        return COLUMN_FIRM;
    }
    public static String getColumnDescription() {
        return COLUMN_DESCRIPTION;
    }
    public static String getColumnContactPersonId() {
        return COLUMN_CONTACT_PERSON_ID;
    }
    public static String getColumnIsSingleContactPerson() {
        return COLUMN_IS_SINGLE_CONTACT_PERSON;
    }
    public static String getColumnDate() {
        return COLUMN_DATE;
    }

    public static String createProjectTable() {
        String query = " CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PROJECT_NAME + " VARCHAR(255) NOT NULL, " +
                COLUMN_FIRM + " VARCHAR(50) NOT NULL, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_CONTACT_PERSON_ID + " INTEGER, " +
                COLUMN_IS_SINGLE_CONTACT_PERSON + " BOOL, " +
                COLUMN_DATE + " DATE NOT NULL);";
        return query;
    }
}
