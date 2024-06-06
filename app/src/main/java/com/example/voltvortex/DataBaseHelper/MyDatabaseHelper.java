package com.example.voltvortex.DataBaseHelper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import com.example.voltvortex.DataBaseHelper.CreateTableHelpers.*;
import com.example.voltvortex.Models.*;

import java.util.*;

/**
 * Klasa pomocnicza do zarządzania bazą danych.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "VoltVortex.db";
    private static final int DATABASE_VERSION = 1;
    private Context context;

    /**
     * Konstruktor klasy pomocniczej bazy danych.
     * @param context kontekst aplikacji
     */
    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    /**
     * Tworzenie tabel w bazie danych.
     * @param db baza danych
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContactPersonTableHelper.createContactPersonTable());
        db.execSQL(ZSComponentsTableHelper.createZSComponentsTable());
        db.execSQL(ZsElectricalProtectionTableHelper.createZsElectricalProtectionTable());
        db.execSQL(ProjectTableHelper.createProjectTable());
        db.execSQL(BuildingTableHelper.createBuildingTable());
        InsertInitialDataHelper.insertInitialData(db);
    }

    /**
     * Aktualizacja bazy danych podczas upgrade'u.
     * @param db baza danych
     * @param oldVersion stara wersja bazy danych
     * @param newVersion nowa wersja bazy danych
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ProjectTableHelper.getTableName_PROJECT());
        db.execSQL("DROP TABLE IF EXISTS " + BuildingTableHelper.getTableName_BUILDING());
        db.execSQL("DROP TABLE IF EXISTS " + ContactPersonTableHelper.getTableName_CONTACT_PERSON());
        db.execSQL("DROP TABLE IF EXISTS " + ZSComponentsTableHelper.getTableName_ZS_COMPONENT());
        db.execSQL("DROP TABLE IF EXISTS " + ZsElectricalProtectionTableHelper.getTableName_ZS_ELECTRICAL_PROTECTION());
        onCreate(db);
    }

    public void createTableForBuilding(int buildingId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String floorTable = FloorTableHelper.createFloorTable(buildingId);
        String roomTable = RoomTableHelper.createRoomTable(buildingId);
        String zsTable = ZsTableHelper.createZSTable(buildingId);
        String parTable = PARTabelHelper.createPARTable(buildingId);

        Log.d("MyDatabaseHelper", "Creating table with query: " + floorTable);
        db.execSQL(floorTable);

        Log.d("MyDatabaseHelper", "Creating table with query: " + roomTable);
        db.execSQL(roomTable);

        Log.d("MyDatabaseHelper", "Creating table with query: " + zsTable);
        db.execSQL(zsTable);

        Log.d("MyDatabaseHelper", "Creating table with query: " + parTable);
        db.execSQL(parTable);

        InsertBasicBuildingData.insertBasicBuildingData(db, buildingId);
    }

    /**
     * Dodanie nowego projektu do bazy danych.
     * @param projectModel model projektu
     * @return true jeśli dodanie się powiodło, false w przeciwnym razie
     */
    public boolean addProject(ProjectModel projectModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ProjectTableHelper.getColumnProjectName(), projectModel.getProjectName());
        cv.put(ProjectTableHelper.getColumnFirm(), projectModel.getFirm());
        cv.put(ProjectTableHelper.getColumnDescription(), projectModel.getDescription());
        cv.put(ProjectTableHelper.getColumnContactPersonId(), projectModel.getContactPersonID());
        cv.put(ProjectTableHelper.getColumnIsSingleContactPerson(), projectModel.isSingleContactPerson() ? 1 : 0);

        long insert = db.insert(ProjectTableHelper.getTableName_PROJECT(), null, cv);
        if (insert == -1) {
            Toast.makeText(context, "Nie udało się dodać projektu", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Toast.makeText(context, "Projekt dodany pomyślnie", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    /**
     * Usunięcie projektu z bazy danych.
     * @param projectModel model projektu do usunięcia
     * @return true jeśli usunięcie się powiodło, false w przeciwnym razie
     */
    public boolean deleteProject(ProjectModel projectModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + ProjectTableHelper.getTableName_PROJECT() +
            " WHERE " + ProjectTableHelper.getColumnProjectId() + " = " + projectModel.getProjectID();

        Cursor cursor = db.rawQuery(queryString, null);
        boolean result = cursor.moveToFirst();
        cursor.close();
        return result;
    }

    /**
     * Pobranie listy wszystkich projektów z bazy danych.
     * @return lista projektów
     */
    public List<ProjectModel> viewProjectList() {
        List<ProjectModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + ProjectTableHelper.getTableName_PROJECT();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int projectID = cursor.getInt(0);
                String projectName = cursor.getString(1);
                String projectFirm = cursor.getString(2);
                String projectDescription = cursor.getString(3);
                int contactPersonID = cursor.getInt(4);
                boolean projectIsSingleContactPerson = cursor.getInt(5) == 1;

                ProjectModel newProjectModel = new ProjectModel
                        (projectID, projectName, projectFirm, projectDescription,
                                contactPersonID, projectIsSingleContactPerson);
                returnList.add(newProjectModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnList;
    }

    /**
     * Dodanie nowegej osoby kontaktowej do bazy danych.
     * @param contactPersonModel model osoby kontaktowej
     * @return true jeśli dodanie się powiodło, false w przeciwnym razie
     */
    public boolean addContactPerson(ContactPersonModel contactPersonModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ContactPersonTableHelper.getColumn_CONTACT_PERSON_NAME(), contactPersonModel.getName());
        cv.put(ContactPersonTableHelper.getColumn_FIRM(), contactPersonModel.getFirm());
        cv.put(ContactPersonTableHelper.getColumn_CONTACT_PERSON_POSITION(), contactPersonModel.getPosition());
        cv.put(ContactPersonTableHelper.getColumn_CONTACT_PERSON_EMAIL(), contactPersonModel.getEmail());
        cv.put(ContactPersonTableHelper.getColumn_CONTACT_PERSON_PHONE(), contactPersonModel.getPhone());

        long insert = db.insert(ContactPersonTableHelper.getTableName_CONTACT_PERSON(), null, cv);
        if (insert == -1) {
            Toast.makeText(context, "Nie udało się dodać osoby kontaktowej!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Toast.makeText(context, "Osoba kontaktowa dodana pomyślnie.", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    /**
     * Usunięcie osoby kontaktowej z bazy danych.
     * @param contactPersonModel model osoby kontaktowej do usunięcia
     * @return true jeśli usunięcie się powiodło, false w przeciwnym razie
     */
    public boolean deleteContactPerson(ContactPersonModel contactPersonModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " +
                ContactPersonTableHelper.getTableName_CONTACT_PERSON() + " WHERE " +
                ContactPersonTableHelper.getColumn_CONTACT_PERSON_ID() + " = " +
                contactPersonModel.getContactPersonID();

        Cursor cursor = db.rawQuery(queryString, null);
        boolean result = cursor.moveToFirst();
        cursor.close();
        return result;
    }

    /**
     * Pobranie listy wszystkich osób kontaktowych z bazy danych.
     * @return lista osób kontaktowych
     */
    public List<ContactPersonModel> viewContactPersonList() {
        List<ContactPersonModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + ContactPersonTableHelper.getTableName_CONTACT_PERSON();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int contactPersonID = cursor.getInt(0);
                String contactPersonName = cursor.getString(1);
                String contactPersonFirm = cursor.getString(2);
                String contactPersonPosition = cursor.getString(3);
                String contactPersonEmail = cursor.getString(4);
                String contactPersonPhone = cursor.getString(5);

                ContactPersonModel contactPersonModel = new ContactPersonModel(contactPersonID,
                        contactPersonName, contactPersonFirm, contactPersonPosition,
                        contactPersonEmail, contactPersonPhone);
                returnList.add(contactPersonModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnList;
    }

    /**
     * Dodanie nowego budynku do bazy danych.
     * @param buildingModel model osoby kontaktowej
     * @return true jeśli dodanie się powiodło, false w przeciwnym razie
     */
    public boolean addBuilding(BuildingModel buildingModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(BuildingTableHelper.getColumn_BUILDING_NAME(), buildingModel.getBuildingName());
        cv.put(BuildingTableHelper.getColumn_DATE_OF_MEASUREMENTS(), buildingModel.getDateOfMeasurements().getTime());
        cv.put(BuildingTableHelper.getColumn_CITY(), buildingModel.getCity());
        cv.put(BuildingTableHelper.getColumn_POSTCODE(), buildingModel.getPostcode());
        cv.put(BuildingTableHelper.getColumn_STREET(), buildingModel.getStreet());
        cv.put(BuildingTableHelper.getColumn_BUILDING_NUMBER(), buildingModel.getBuildingNumber());
        cv.put(BuildingTableHelper.getColumn_PROJECT_ID(), buildingModel.getProjectID());
        cv.put(BuildingTableHelper.getColumn_CONTACT_PERSON_ID(), buildingModel.getContactPersonID());

        long insert = db.insert(BuildingTableHelper.getTableName_BUILDING(), null, cv);

        if (insert == -1) {
            Toast.makeText(context, "Nie udało się dodać budynku!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Toast.makeText(context, "Budynek dodany pomyślnie.", Toast.LENGTH_SHORT).show();

            Cursor cursor = db.rawQuery("SELECT last_insert_rowid()", null);
            if (cursor.moveToFirst()) {
                int buildingId = cursor.getInt(0);
                createTableForBuilding(buildingId);
            }
            cursor.close();
            return true;
        }
    }

    /**
     * Usunięcie budynku z bazy danych.
     * @param buildingModel model osoby kontaktowej do usunięcia
     * @return true jeśli usunięcie się powiodło, false w przeciwnym razie
     */
    public boolean deleteBuilding(BuildingModel buildingModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " +
                BuildingTableHelper.getTableName_BUILDING() + " WHERE " +
                BuildingTableHelper.getColumn_BUILDING_ID() + " = " +
                buildingModel.getBuildingID();

        Cursor cursor = db.rawQuery(queryString, null);
        boolean result = cursor.moveToFirst();
        cursor.close();
        return result;
    }

    /**
     * Pobranie listy wszystkich budynków z bazy danych.
     * @return lista budynków
     */
    public List<BuildingModel> viewBuildingList() {
        List<BuildingModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + BuildingTableHelper.getTableName_BUILDING();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int buildingID = cursor.getInt(0);
                String buildingName = cursor.getString(1);
                long dateOfMeasurementsLong = cursor.getLong(2);
                String city = cursor.getString(3);
                String postcode = cursor.getString(4);
                String street = cursor.getString(5);
                String buildingNumber = cursor.getString(6);
                int projectID = cursor.getInt(7);
                int contactPersonID = cursor.getInt(8);

                Date dateOfMeasurements = new Date(dateOfMeasurementsLong);

                BuildingModel newBuildingModel = new BuildingModel
                        (buildingID, buildingName, dateOfMeasurements, city,
                                postcode, street, buildingNumber, projectID, contactPersonID);
                returnList.add(newBuildingModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnList;
    }

    /**
     * Pobranie listy wybranych budynków z bazy danych na podstawie projectId.
     * @return lista budynków
     */
    public List<BuildingModel> viewBuildingListByProjectId(int projectId) {
        List<BuildingModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + BuildingTableHelper.getTableName_BUILDING() +
                " WHERE " + BuildingTableHelper.getColumn_PROJECT_ID() + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, new String[]{String.valueOf(projectId)});

        if (cursor.moveToFirst()) {
            do {
                int buildingID = cursor.getInt(0);
                String buildingName = cursor.getString(1);
                long dateOfMeasurementsLong = cursor.getLong(2);
                String city = cursor.getString(3);
                String postcode = cursor.getString(4);
                String street = cursor.getString(5);
                String buildingNumber = cursor.getString(6);
                int contactPersonID = cursor.getInt(7);

                Date dateOfMeasurements = new Date(dateOfMeasurementsLong);

                BuildingModel newBuildingModel = new BuildingModel
                        (buildingID, buildingName, dateOfMeasurements, city,
                                postcode, street, buildingNumber, projectId, contactPersonID);
                returnList.add(newBuildingModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnList;
    }

    /**
     * Dodanie nowej Uwagi odnośnie budynku do bazy danych.
     * @param parModel model
     * @return true jeśli dodanie się powiodło, false w przeciwnym razie
     */
    public boolean addPAR(PARModel parModel, int buildingId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(PARTabelHelper.getColumnPARContent(), parModel.getContent());
        cv.put(PARTabelHelper.getColumnPARIsUsed(), parModel.getIsUsed());

        long insert = db.insert(PARTabelHelper.getTableName_PAR(buildingId), null, cv);
        if (insert == -1) {
            Toast.makeText(context, "Nie udało się dodać projektu", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Toast.makeText(context, "Projekt dodany pomyślnie", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    /**
     * Pobranie listy wszystkich PPAR z bazy danych.
     * @return lista PPAR
     */
    public List<PARModel> viewPARList(int buildingId) {
        List<PARModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + PARTabelHelper.getTableName_PAR(buildingId);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int parID = cursor.getInt(0);
                String content = cursor.getString(1);
                int isUsed = cursor.getInt(2);

                PARModel pARModel = new PARModel(parID, content, isUsed);
                returnList.add(pARModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnList;
    }


    public boolean updatePARIsUsed(int buildingID, int parID, int newIsUsed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PARTabelHelper.getColumnPARIsUsed(), newIsUsed);

        String tableName = PARTabelHelper.getTableName_PAR(buildingID);
        String whereClause = PARTabelHelper.getColumn_PAR_ID() + " = ?";
        String[] whereArgs = {String.valueOf(parID)};

        Log.d("updatePARIsUsed", "Updating table: " + tableName);
        Log.d("updatePARIsUsed", "ContentValues: " + cv.toString());
        Log.d("updatePARIsUsed", "WhereClause: " + whereClause);
        Log.d("updatePARIsUsed", "WhereArgs: " + Arrays.toString(whereArgs));

        int update = db.update(tableName, cv, whereClause, whereArgs);
        db.close();

        Log.d("updatePARIsUsed", "Update result: " + update);

        return update != -1;
    }

    public List<FloorModel> getFloors(int buildingId) {
        List<FloorModel> floors = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String tableName = FloorTableHelper.getTableName_Floor(buildingId);
        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String floor = cursor.getString(1);
                floors.add(new FloorModel(id, floor));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return floors;
    }

    public List<RoomModel> getRoomsForFloor(int buildingId, int floorId) {
        List<RoomModel> rooms = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String tableName = RoomTableHelper.getTableName_Room(buildingId);
        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName + " WHERE " +
                RoomTableHelper.getColumnFloorId() + " = ?", new String[]{String.valueOf(floorId)});

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String room = cursor.getString(1);
                int floor = cursor.getInt(2);
                rooms.add(new RoomModel(id, room, floor));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return rooms;
    }

    public List<String> getFloorNames(int buildingId) {
        List<String> floorNames = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String tableName = FloorTableHelper.getTableName_Floor(buildingId);
        Cursor cursor = db.rawQuery("SELECT " + FloorTableHelper.getColumnFloor() + " FROM " + tableName, null);

        if (cursor.moveToFirst()) {
            do {
                String floorName = cursor.getString(0);
                floorNames.add(floorName);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return floorNames;
    }

    public int getFloorIdByName(int buildingId, String floorName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String tableName = FloorTableHelper.getTableName_Floor(buildingId);
        Cursor cursor = db.rawQuery("SELECT " + FloorTableHelper.getColumnFloorId() + " FROM " + tableName + " WHERE " + FloorTableHelper.getColumnFloor() + " = ?", new String[]{floorName});
        int floorId = -1;

        if (cursor.moveToFirst()) {
            floorId = cursor.getInt(0);
        }

        cursor.close();
        return floorId;
    }

    public void addFloor(int buildingId, FloorModel floor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FloorTableHelper.getColumnFloor(), floor.getFloor());
        db.insert(FloorTableHelper.getTableName_Floor(buildingId), null, cv);
    }

    public void addRoom(int buildingId, RoomModel room) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(RoomTableHelper.getColumnRoom(), room.getRoom());
        cv.put(RoomTableHelper.getColumnFloorId(), room.getFloorId());
        db.insert(RoomTableHelper.getTableName_Room(buildingId), null, cv);
    }

    public int getMaxFloorNumber(int buildingId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String tableName = FloorTableHelper.getTableName_Floor(buildingId);
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + tableName, null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count - 1;
    }

    public List<ZSModel> getZSPoints(int buildingId, int floorId, int roomId) {
        List<ZSModel> zsPoints = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String tableName = ZsTableHelper.getTableName_ZS(buildingId);

        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName + " WHERE " +
                ZsTableHelper.getColumnFloorId() + " = ? AND " +
                ZsTableHelper.getColumnRoomId() + " = ?", new String[]{String.valueOf(floorId), String.valueOf(roomId)});

        if (cursor.moveToFirst()) {
            do {
                int zsID = cursor.getInt(0);
                int measuredComponentID = cursor.getInt(1);
                int electricalProtectionID = cursor.getInt(2);
                int floorID = cursor.getInt(3);
                int roomID = cursor.getInt(4);
                String typeOfProtection = cursor.getString(5);
                float multiplierOfProtection = cursor.getFloat(6);
                float result = cursor.getFloat(7);
                boolean isBZ = cursor.getInt(8) == 1;
                boolean isBPE = cursor.getInt(9) == 1;
                boolean isBK = cursor.getInt(10) == 1;
                boolean isBKLAPKI = cursor.getInt(11) == 1;
                boolean isWYRW = cursor.getInt(12) == 1;
                boolean is2PRZEW = cursor.getInt(13) == 1;
                boolean wasMeasured = cursor.getInt(14) == 1;
                float measuredZS = cursor.getFloat(15);

                zsPoints.add(new ZSModel(zsID, measuredComponentID, electricalProtectionID, floorID, roomID,
                        typeOfProtection, multiplierOfProtection, measuredZS, result, isBZ, isBPE, isBK,
                        isBKLAPKI, isWYRW, is2PRZEW, wasMeasured));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return zsPoints;
    }
}