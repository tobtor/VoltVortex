package com.example.voltvortex.DataBaseHelper.CreateTableHelpers;

public class ZsIrregularElectricalProtectionTableHelper {

    private static final String TABLE_NAME =
            "ELECTRICAL_ZS_IRREGULAR_ELECTRICAL_PROTECTION";
    private static final String COLUMN_ZS_IRREGULAR_ELECTRICAL_PROTECTION_ID =
            "COLUMN_ZS_IRREGULAR_ELECTRICAL_PROTECTION_ID";
    private static final String COLUMN_ZS_IRREGULAR_ELECTRICAL_PROTECTION =
            "ZS_IRREGULAR_ELECTRICAL_PROTECTION";
    private static final String COLUMN_ZS_IRREGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TN_RECIVER =
            "ZS_IRREGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TN_RECIVER";
    private static final String COLUMN_ZS_IRREGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TN_SWITCHGEAR =
            "ZS_IRREGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TN_SWITCHGEAR";
    private static final String COLUMN_ZS_IRREGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TT_RECIVER =
            "ZS_IRREGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TT_RECIVER";
    private static final String COLUMN_ZS_IRREGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TT_SWITCHGEAR =
            "ZS_IRREGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TT_SWITCHGEAR";

    public static String getTableName_BUILDING() {
        return TABLE_NAME;
    }
    public static String getColumnZsIrregularElectricalProtectionId() {
        return COLUMN_ZS_IRREGULAR_ELECTRICAL_PROTECTION_ID;
    }
    public static String getColumnZsIrregularElectricalProtection() {
        return COLUMN_ZS_IRREGULAR_ELECTRICAL_PROTECTION;
    }
    public static String getColumnZsIrregularElectricalProtectionMultiplierTNReciver() {
        return COLUMN_ZS_IRREGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TN_RECIVER;
    }
    public static String getColumnZsIrregularElectricalProtectionMultiplierTNSwitchGear() {
        return COLUMN_ZS_IRREGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TN_SWITCHGEAR;
    }
    public static String getColumnZsIrregularElectricalProtectionMultiplierTTReciver() {
        return COLUMN_ZS_IRREGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TT_RECIVER;
    }
    public static String getColumnZsIrregularElectricalProtectionMultiplierTTSwitchGear() {
        return COLUMN_ZS_IRREGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TT_SWITCHGEAR;
    }

    public static String createZsIrregularElectricalProtectionTable() {
        String query = " CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ZS_IRREGULAR_ELECTRICAL_PROTECTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ZS_IRREGULAR_ELECTRICAL_PROTECTION + " VARCHAR(15) NOT NULL, " +
                COLUMN_ZS_IRREGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TN_RECIVER + " FLOAT NOT NULL, " +
                COLUMN_ZS_IRREGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TN_SWITCHGEAR + " FLOAT NOT NULL, " +
                COLUMN_ZS_IRREGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TT_RECIVER + " FLOAT NOT NULL, " +
                COLUMN_ZS_IRREGULAR_ELECTRICAL_PROTECTION_MULTIPLIER_TT_SWITCHGEAR + " FLOAT NOT NULL);";
        return query;
    }
}
