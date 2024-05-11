package com.example.voltvortex.DataBaseHelper.CreateTableHelpers;

public class ZsElectricalProtectionTableHelper {

    private static final String TABLE_NAME =
            "ELECTRICAL_ZS_REGULAR_ELECTRICAL_PROTECTION";
    private static final String COLUMN_ZS_ELECTRICAL_PROTECTION_ID =
            "COLUMN_ZS_ELECTRICAL_PROTECTION_ID";
    private static final String COLUMN_ZS_ELECTRICAL_PROTECTION =
            "ZS_ELECTRICAL_PROTECTION";
    private static final String COLUMN_ZS_ELECTRICAL_PROTECTION_MULTIPLIER_TN_RECIVER =
            "ZS_ELECTRICAL_PROTECTION_MULTIPLIER_TN_RECIVER";
    private static final String COLUMN_ZS_ELECTRICAL_PROTECTION_MULTIPLIER_TN_SWITCHGEAR =
            "ZS_ELECTRICAL_PROTECTION_MULTIPLIER_TN_SWITCHGEAR";
    private static final String COLUMN_ZS_ELECTRICAL_PROTECTION_MULTIPLIER_TT_RECIVER =
            "ZS_ELECTRICAL_PROTECTION_MULTIPLIER_TT_RECIVER";
    private static final String COLUMN_ZS_ELECTRICAL_PROTECTION_MULTIPLIER_TT_SWITCHGEAR =
            "ZS_ELECTRICAL_PROTECTION_MULTIPLIER_TT_SWITCHGEAR";
    private static final String COLUMN_ZS_ELECTRICAL_PROTECTION_MULTIPLIER_DC =
            "ZS_ELECTRICAL_PROTECTION_MULTIPLIER_DC";

    public static String getTableName_ZS_ELECTRICAL_PROTECTION() {
        return TABLE_NAME;
    }
    public static String getColumnZsElectricalProtectionId() {
        return COLUMN_ZS_ELECTRICAL_PROTECTION_ID;
    }
    public static String getColumnZsElectricalProtection() {
        return COLUMN_ZS_ELECTRICAL_PROTECTION;
    }
    public static String getColumnZsElectricalProtectionMultiplierTNReciver() {
        return COLUMN_ZS_ELECTRICAL_PROTECTION_MULTIPLIER_TN_RECIVER;
    }
    public static String getColumnZsElectricalProtectionMultiplierTNSwitchGear() {
        return COLUMN_ZS_ELECTRICAL_PROTECTION_MULTIPLIER_TN_SWITCHGEAR;
    }
    public static String getColumnZsElectricalProtectionMultiplierTTReciver() {
        return COLUMN_ZS_ELECTRICAL_PROTECTION_MULTIPLIER_TT_RECIVER;
    }
    public static String getColumnZsElectricalProtectionMultiplierTTSwitchGear() {
        return COLUMN_ZS_ELECTRICAL_PROTECTION_MULTIPLIER_TT_SWITCHGEAR;
    }
    public static String getColumnZsElectricalProtectionMultiplierDC() {
        return COLUMN_ZS_ELECTRICAL_PROTECTION_MULTIPLIER_DC;
    }

    public static String createZsElectricalProtectionTable() {
        String query = " CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ZS_ELECTRICAL_PROTECTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ZS_ELECTRICAL_PROTECTION + " VARCHAR(15) NOT NULL, " +
                COLUMN_ZS_ELECTRICAL_PROTECTION_MULTIPLIER_TN_RECIVER + " FLOAT NOT NULL, " +
                COLUMN_ZS_ELECTRICAL_PROTECTION_MULTIPLIER_TN_SWITCHGEAR + " FLOAT NOT NULL, " +
                COLUMN_ZS_ELECTRICAL_PROTECTION_MULTIPLIER_TT_RECIVER + " FLOAT NOT NULL, " +
                COLUMN_ZS_ELECTRICAL_PROTECTION_MULTIPLIER_TT_SWITCHGEAR + " FLOAT NOT NULL, " +
                COLUMN_ZS_ELECTRICAL_PROTECTION_MULTIPLIER_DC + " FLOAT);";
        return query;
    }
}
