package com.example.voltvortex.DataBaseHelper.CreateTableHelpers;

import static com.example.voltvortex.DataBaseHelper.CreateTableHelpers.ZSComponentsTableHelper.*;
import static com.example.voltvortex.DataBaseHelper.CreateTableHelpers.ZsElectricalProtectionTableHelper.*;

public class ZsTableHelper {

    private static final String TABLE_NAME = "ZS";
    private static final String COLUMN_ZS_ID = "ZS_ID";
    private static final String COLUMN_ZS_MESURED_COMPONENT_ID = "ZS_MESURMENT_COMPONENT_ID";
    private static final String COLUMN_ZS_ELECTRICAL_PROTECTION_ID = "ZS_ELECTRICAL_PROTECTION_ID";
    private static final String COLUMN_TYPE_OF_ELECTRICAL_PROTECTION = "TYPE_OF_ELECTRICAL_PROTECTION";
    private static final String COLUMN_MULTIPLIER_OF_ELECTRICAL_PROTECTION = "MULTIPLIER_OF_ELECTRICAL_PROTECTION";
    private static final String COLUMN_IS_BZ = "IS_BZ";
    private static final String COLUMN_IS_BPE = "IS_BPE";
    private static final String COLUMN_IS_BK = "IS_BK";
    private static final String COLUMN_IS_BKLAPKI = "IS_BKLAPKI";
    private static final String COLUMN_IS_WYRW = "IS_WYRW";
    private static final String COLUMN_IS_2PRZEW = "IS_2PRZEW";
    private static final String COLUMN_WAS_MESURED = "WAS_MESURED";
    private static final String COLUMN_ZS_MESURED = "ZS_MESURED";
    private static final String COLUMN_RESULT = "RESULT";

    public static String getTableName_ZS() {
        return TABLE_NAME;
    }
    public static String getColumnZsId() {
        return COLUMN_ZS_ID;
    }
    public static String getColumnReferenceZsMesuredComponentId() {
        return COLUMN_ZS_MESURED_COMPONENT_ID;
    }
    public static String getColumnReferenceZsElectricalProtectionId() {
        return COLUMN_ZS_ELECTRICAL_PROTECTION_ID;
    }
    public static String getColumnTypeOfElectricalProtection() {
        return COLUMN_TYPE_OF_ELECTRICAL_PROTECTION;
    }
    public static String getColumnMultiplierOfElectricalProtection() {
        return COLUMN_MULTIPLIER_OF_ELECTRICAL_PROTECTION;
    }
    public static String getColumnIsBz() {
        return COLUMN_IS_BZ;
    }
    public static String getColumnIsBpe() {
        return COLUMN_IS_BPE;
    }
    public static String getColumnIsBK() {
        return COLUMN_IS_BK;
    }
    public static String getColumnIsBKLAPKI() {
        return COLUMN_IS_BKLAPKI;
    }
    public static String getColumnIsWyrw() {
        return COLUMN_IS_WYRW;
    }
    public static String getColumnIs2przew() {
        return COLUMN_IS_2PRZEW;
    }
    public static String getColumnWasMeasured() {
        return COLUMN_WAS_MESURED;
    }
    public static String getColumnZsMeasured() {
        return COLUMN_ZS_MESURED;
    }
    public static String getColumnResult() {
        return COLUMN_RESULT;
    }

    public static String createZSTable() {
        String query = " CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ZS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ZS_MESURED_COMPONENT_ID + " INTEGER, " +
                COLUMN_ZS_ELECTRICAL_PROTECTION_ID + " INTEGER, " +
                COLUMN_TYPE_OF_ELECTRICAL_PROTECTION + " VARCHAR(15), " +
                COLUMN_MULTIPLIER_OF_ELECTRICAL_PROTECTION + " FLOAT, " +
                COLUMN_RESULT + " FLOAT NOT NULL, " +
                COLUMN_IS_BZ + " INTEGER NOT NULL, " +
                COLUMN_IS_BPE + " INTEGER NOT NULL, " +
                COLUMN_IS_BK + " INTEGER NOT NULL, " +
                COLUMN_IS_BKLAPKI + " INTEGER NOT NULL, " +
                COLUMN_IS_WYRW + " INTEGER NOT NULL, " +
                COLUMN_IS_2PRZEW + " INTEGER NOT NULL, " +
                COLUMN_WAS_MESURED + " INTEGER NOT NULL, " +
                COLUMN_ZS_MESURED + " FLOAT NOT NULL " +
                "FOREIGN KEY (" + COLUMN_ZS_MESURED_COMPONENT_ID + ") REFERENCES " + getTableName_ZS_COMPONENT() +
                " (" + getColumnZsComponentsId() + "), " +
                "FOREIGN KEY (" + COLUMN_ZS_ELECTRICAL_PROTECTION_ID + ") REFERENCES " + getTableName_ZS_ELECTRICAL_PROTECTION() +
                "(" + getColumnZsElectricalProtectionId() + "));";
        return query;
    }
}
