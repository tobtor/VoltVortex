package com.example.voltvortex.DataBaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import com.example.voltvortex.DataBaseHelper.CreateTableHelpers.ProjectTableHelper;
import com.example.voltvortex.DataBaseHelper.CreateTableHelpers.BuildingTableHelper;
import com.example.voltvortex.DataBaseHelper.CreateTableHelpers.PPARTabelHelper;
import com.example.voltvortex.DataBaseHelper.CreateTableHelpers.ZSComponentsTableHelper;
import com.example.voltvortex.DataBaseHelper.CreateTableHelpers.ContactPersonTableHelper;
import com.example.voltvortex.DataBaseHelper.CreateTableHelpers.ZsElectricalProtectionTableHelper;
import com.example.voltvortex.Models.ContactPersonModel;
import com.example.voltvortex.Models.ProjectModel;
import com.example.voltvortex.Models.BuildingModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.voltvortex.DataBaseHelper.CreateTableHelpers.FloorAndRoomTableHelper.createFloorAndRoomTable;
import static com.example.voltvortex.DataBaseHelper.CreateTableHelpers.PARTabelHelper.createPPARTable;
import static com.example.voltvortex.DataBaseHelper.CreateTableHelpers.ZsTableHelper.createZSTable;

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
        db.execSQL(PPARTabelHelper.createPPARTable());
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
        db.execSQL("DROP TABLE IF EXISTS " + PPARTabelHelper.getTableName_PPAR());
        onCreate(db);
    }

    public void createTableForBuilding(int buildingId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(createFloorAndRoomTable(buildingId));
        db.execSQL(createZSTable(buildingId));
        db.execSQL(createPPARTable(buildingId));
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
            Toast.makeText(context, "Nie udało się dodać osoby kontaktowej!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Toast.makeText(context, "Budynek dodany pomyślnie.", Toast.LENGTH_SHORT).show();

            // Pobierz ID dodanego budynku
            Cursor cursor = db.rawQuery("SELECT last_insert_rowid()", null);
            if (cursor.moveToFirst()) {
                int buildingId = cursor.getInt(0);

                // Tworzenie nowych tabel
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
}