package com.example.voltvortex.DataBaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import com.example.voltvortex.DataBaseHelper.CreateTableHelpers.ProjectTableHelper;
import com.example.voltvortex.DataBaseHelper.CreateTableHelpers.BuildingTableHelper;
import com.example.voltvortex.DataBaseHelper.CreateTableHelpers.PPARTabelHelper;
import com.example.voltvortex.DataBaseHelper.CreateTableHelpers.ZSComponentsTableHelper;
import com.example.voltvortex.DataBaseHelper.CreateTableHelpers.ContactPersonTableHelper;
import com.example.voltvortex.DataBaseHelper.CreateTableHelpers.ZsElectricalProtectionTableHelper;
import com.example.voltvortex.Models.ContactPersonModel;
import com.example.voltvortex.Models.ProjectModel;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Dodanie nowego projektu do bazy danych.
     * @param projectModel model projektu
     * @return true jeśli dodanie się powiodło, false w przeciwnym razie
     */
    public boolean addProject(ProjectModel projectModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ProjectTableHelper.getColumnProjectId(), projectModel.getProjectID());
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

                ProjectModel newProjectModel = new ProjectModel(projectID, projectName, projectFirm, projectDescription, contactPersonID, projectIsSingleContactPerson);
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

        cv.put(ContactPersonTableHelper.getColumn_CONTACT_PERSON_ID(), contactPersonModel.getContactPersonID());
        cv.put(ContactPersonTableHelper.getColumn_CONTACT_PERSON_NAME(), contactPersonModel.getName());
        cv.put(ContactPersonTableHelper.getColumn_FIRM(), contactPersonModel.getFirm());
        cv.put(ContactPersonTableHelper.getColumn_CONTACT_PERSON_POSITION(), contactPersonModel.getPosition());
        cv.put(ContactPersonTableHelper.getColumn_CONTACT_PERSON_PHONE(), contactPersonModel.getPhone());
        cv.put(ContactPersonTableHelper.getColumn_CONTACT_PERSON_EMAIL(), contactPersonModel.getEmail());

        long insert = db.insert(ProjectTableHelper.getTableName_PROJECT(), null, cv);
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
        SQLiteDatabase db = this.getReadableDatabase();

        if (db == null) {
            Log.e("DatabaseHelper", "Nie udało się otworzyć bazy danych do odczytu.");
            return returnList; // Zwraca pustą listę, baza danych nie jest dostępna
        }

        String queryString = "SELECT * FROM " + ContactPersonTableHelper.getTableName_CONTACT_PERSON();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int contactPersonID = cursor.getInt(0);
                String contactPersonName = cursor.getString(1);
                String contactPersonFirm = cursor.getString(2);
                String contactPersonPosition = cursor.getString(3);
                String contactPersonPhone = cursor.getString(4);
                String contactPersonEmail = cursor.getString(5);

                ContactPersonModel contactPersonModel = new ContactPersonModel(contactPersonID,
                        contactPersonName, contactPersonFirm, contactPersonPosition,
                        contactPersonPhone, contactPersonEmail);
                returnList.add(contactPersonModel);
            } while (cursor.moveToNext());

            cursor.close();
        } else {
            Log.e("DatabaseHelper", "Cursor jest null lub pusty.");
        }

        db.close();
        return returnList;
    }

}
