package com.example.voltvortex.DataBaseHelper.CreateTableHelpers;

public class ContactPersonTableHelper {

    private static final String TABLE_NAME = "CONTACT_PERSON";
    private static final String COLUMN_CONTACT_PERSON_ID = "CONTACT_PERSON_ID";
    private static final String COLUMN_CONTACT_PERSON_NAME = "CONTACT_PERSON_NAME";
    private static final String COLUMN_CONTACT_PERSON_SURNAME = "COLUMN_CONTACT_PERSON_SURNAME";
    private static final String COLUMN_FIRM = "DESCRIPTION";
    private static final String COLUMN_CONTACT_PERSON_POSITION = "CONTACT_PERSON_POSITION";
    private static final String COLUMN_CONTACT_PERSON_PHONE = "CONTACT_PERSON_PHONE";
    private static final String COLUMN_CONTACT_PERSON_EMAIL = "CONTACT_PERSON_EMAIL";

    public static String getTableName_CONTACT_PERSON() {
        return TABLE_NAME;
    }
    public static String getColumn_CONTACT_PERSON_ID() {
        return COLUMN_CONTACT_PERSON_ID;
    }
    public static String getColumn_CONTACT_PERSON_NAME() {
        return COLUMN_CONTACT_PERSON_NAME;
    }
    public static String getColumn_CONTACT_PERSON_SURNAME() {
        return COLUMN_CONTACT_PERSON_SURNAME;
    }
    public static String getColumn_FIRM() {
        return COLUMN_FIRM;
    }
    public static String getColumn_CONTACT_PERSON_POSITION() {
        return COLUMN_CONTACT_PERSON_POSITION;
    }
    public static String getColumn_CONTACT_PERSON_PHONE() {
        return COLUMN_CONTACT_PERSON_PHONE;
    }
    public static String getColumn_CONTACT_PERSON_EMAIL() {
        return COLUMN_CONTACT_PERSON_EMAIL;
    }

    public static String createContactPersonTable() {
        String query = " CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_CONTACT_PERSON_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CONTACT_PERSON_NAME + " VARCHAR(15), " +
                COLUMN_CONTACT_PERSON_SURNAME + " VARCHAR(55), " +
                COLUMN_FIRM + " VARCHAR(50), " +
                COLUMN_CONTACT_PERSON_POSITION + " VARCHAR(255), " +
                COLUMN_CONTACT_PERSON_PHONE + " VARCHAR(12) NOT NULL, " +
                COLUMN_CONTACT_PERSON_EMAIL + " VARCHAR(255));";
        return query;
    }
}