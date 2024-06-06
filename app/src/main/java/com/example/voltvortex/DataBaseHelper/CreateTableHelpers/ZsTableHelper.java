package com.example.voltvortex.DataBaseHelper.CreateTableHelpers;

import static com.example.voltvortex.DataBaseHelper.CreateTableHelpers.ZSComponentsTableHelper.getTableName_ZS_COMPONENT;
import static com.example.voltvortex.DataBaseHelper.CreateTableHelpers.ZsElectricalProtectionTableHelper.getTableName_ZS_ELECTRICAL_PROTECTION;
import static com.example.voltvortex.DataBaseHelper.CreateTableHelpers.ZSComponentsTableHelper.getColumnZsComponentsId;
import static com.example.voltvortex.DataBaseHelper.CreateTableHelpers.ZsElectricalProtectionTableHelper.getColumnZsElectricalProtectionId;
import static com.example.voltvortex.DataBaseHelper.CreateTableHelpers.FloorTableHelper.getTableName_Floor;
import static com.example.voltvortex.DataBaseHelper.CreateTableHelpers.FloorTableHelper.getColumnFloorId;
import static com.example.voltvortex.DataBaseHelper.CreateTableHelpers.RoomTableHelper.getTableName_Room;
import static com.example.voltvortex.DataBaseHelper.CreateTableHelpers.RoomTableHelper.getColumnRoomId;

public class ZsTableHelper {

    private static final String TABLE_NAME = "ZS";
    private static final String COLUMN_ZS_ID = "ZS_ID";
    private static final String COLUMN_ZS_MESURED_COMPONENT_ID = "ZS_MESURMENT_COMPONENT_ID";
    private static final String COLUMN_ZS_ELECTRICAL_PROTECTION_ID = "ZS_ELECTRICAL_PROTECTION_ID";
    private static final String COLUMN_FLOOR_ID = "FLOOR_ID";
    private static final String COLUMN_ROOM_ID = "ROOM_ID";
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

    public static String getTableName_ZS(int buildingId) {
        return "ID" + buildingId + "_" + TABLE_NAME;
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
    public static String getColumnFloorId() {
        return COLUMN_FLOOR_ID;
    }
    public static String getColumnRoomId() {
        return COLUMN_ROOM_ID;
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

    public static String createZSTable(int buildingId) {
        return "CREATE TABLE IF NOT EXISTS ID" + buildingId + "_" + TABLE_NAME +
                " (" + COLUMN_ZS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ZS_MESURED_COMPONENT_ID + " INTEGER, " +
                COLUMN_ZS_ELECTRICAL_PROTECTION_ID + " INTEGER, " +
                COLUMN_FLOOR_ID + " INTEGER, " +
                COLUMN_ROOM_ID + " INTEGER, " +
                COLUMN_MULTIPLIER_OF_ELECTRICAL_PROTECTION + " FLOAT, " +
                COLUMN_RESULT + " FLOAT NOT NULL, " +
                COLUMN_IS_BZ + " INTEGER NOT NULL, " +
                COLUMN_IS_BPE + " INTEGER NOT NULL, " +
                COLUMN_IS_BK + " INTEGER NOT NULL, " +
                COLUMN_IS_BKLAPKI + " INTEGER NOT NULL, " +
                COLUMN_IS_WYRW + " INTEGER NOT NULL, " +
                COLUMN_IS_2PRZEW + " INTEGER NOT NULL, " +
                COLUMN_WAS_MESURED + " INTEGER NOT NULL, " +
                COLUMN_ZS_MESURED + " FLOAT NOT NULL, " +
                "FOREIGN KEY (" + COLUMN_ZS_MESURED_COMPONENT_ID + ") REFERENCES " + getTableName_ZS_COMPONENT() +
                " (" + getColumnZsComponentsId() + "), " +
                "FOREIGN KEY (" + COLUMN_ZS_ELECTRICAL_PROTECTION_ID + ") REFERENCES " + getTableName_ZS_ELECTRICAL_PROTECTION() +
                " (" + getColumnZsElectricalProtectionId() + "), " +
                "FOREIGN KEY (" + COLUMN_FLOOR_ID + ") REFERENCES " + getTableName_Floor(buildingId) +
                " (" + getColumnFloorId() + "), " +
                "FOREIGN KEY (" + COLUMN_ROOM_ID + ") REFERENCES " + getTableName_Room(buildingId) +
                " (" + getColumnRoomId() + "));";
    }
}