package com.example.voltvortex.DataBaseHelper.CreateTableHelpers;

public class ZsRegularElectricalProtectionTableHelper {

    private static final String TABLE_NAME =
            "ELECTRICAL_ZS_REGULAR_ELECTRICAL_PROTECTION";
    private static final String COLUMN_ZS_REGULAR_ELECTRICAL_PROTECTION_ID =
            "COLUMN_ZS_REGULAR_ELECTRICAL_PROTECTION_ID";
    private static final String COLUMN_ZS_REGULAR_ELECTRICAL_PROTECTION =
            "ZS_REGULAR_ELECTRICAL_PROTECTION";
    private static final String COLUMN_ZS_REGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TN_RECIVER =
            "ZS_REGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TN_RECIVER";
    private static final String COLUMN_ZS_REGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TN_SWITCHGEAR =
            "ZS_REGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TN_SWITCHGEAR";
    private static final String COLUMN_ZS_REGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TT_RECIVER =
            "ZS_REGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TT_RECIVER";
    private static final String COLUMN_ZS_REGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TT_SWITCHGEAR =
            "ZS_REGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TT_SWITCHGEAR";
    private static final String COLUMN_ZS_REGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_DC =
            "ZS_REGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_DC";

    public static String getTableName_BUILDING() {
        return TABLE_NAME;
    }
    public static String getColumnZsRegularElectricalProtectionId() {
        return COLUMN_ZS_REGULAR_ELECTRICAL_PROTECTION_ID;
    }
    public static String getColumnZsRegularElectricalProtection() {
        return COLUMN_ZS_REGULAR_ELECTRICAL_PROTECTION;
    }
    public static String getColumnZsRegularElectricalProtectionMultiplierTNReciver() {
        return COLUMN_ZS_REGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TN_RECIVER;
    }
    public static String getColumnZsRegularElectricalProtectionMultiplierTNSwitchGear() {
        return COLUMN_ZS_REGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TN_SWITCHGEAR;
    }
    public static String getColumnZsRegularElectricalProtectionMultiplierTTReciver() {
        return COLUMN_ZS_REGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TT_RECIVER;
    }
    public static String getColumnZsRegularElectricalProtectionMultiplierTTSwitchGear() {
        return COLUMN_ZS_REGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TT_SWITCHGEAR;
    }
    public static String getColumnZsRegularElectricalProtectionMultiplierDC() {
        return COLUMN_ZS_REGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_DC;
    }

    public static String createZsRegularElectricalProtectionTable() {
        String query = " CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ZS_REGULAR_ELECTRICAL_PROTECTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ZS_REGULAR_ELECTRICAL_PROTECTION + " VARCHAR(15) NOT NULL, " +
                COLUMN_ZS_REGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TN_RECIVER + " FLOAT NOT NULL, " +
                COLUMN_ZS_REGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TN_SWITCHGEAR + " FLOAT NOT NULL, " +
                COLUMN_ZS_REGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TT_RECIVER + " FLOAT NOT NULL, " +
                COLUMN_ZS_REGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TT_SWITCHGEAR + " FLOAT NOT NULL, " +
                COLUMN_ZS_REGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_DC + " FLOAT);";
        return query;
    }
}
