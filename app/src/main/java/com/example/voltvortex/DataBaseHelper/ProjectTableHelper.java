package com.example.voltvortex.DataBaseHelper;

public class ProjectTableHelper {

    private static final String TABLE_NAME = "PROJECT";
    private static final String COLUMN_ID = "PROJECT_ID";
    private static final String COLUMN_PROJECT_NAME = "PROJECT_NAME";
    private static final String COLUMN_FIRM = "FIRM";
    private static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    private static final String COLUMN_CONTACT_PERSON_ID = "CONTACT_PERSON_ID";
    private static final String COLUMN_IS_SINGLE_CONTACT_PERSON = "IS_SINGLE_CONTACT_PERSON";


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

    public static String createProjectTable() {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PROJECT_NAME + " TEXT, " +
                COLUMN_FIRM + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_CONTACT_PERSON_ID + " INTEGER, " +
                COLUMN_IS_SINGLE_CONTACT_PERSON + " BOOL);";
        return query;
    }

}
