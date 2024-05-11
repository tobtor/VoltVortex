package com.example.voltvortex.DataBaseHelper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.example.voltvortex.DataBaseHelper.CreateTableHelpers.ProjectTableHelper;
import com.example.voltvortex.DataBaseHelper.CreateTableHelpers.BuildingTableHelper;
import com.example.voltvortex.DataBaseHelper.CreateTableHelpers.PPARTabelHelper;
import com.example.voltvortex.DataBaseHelper.CreateTableHelpers.ZSComponentsTableHelper;
import com.example.voltvortex.DataBaseHelper.CreateTableHelpers.ContactPersonTableHelper;
import com.example.voltvortex.DataBaseHelper.CreateTableHelpers.ZsElectricalProtectionTableHelper;
import com.example.voltvortex.Models.ProjectModel;
import com.example.voltvortex.Models.BuildingModel;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "VoltVortex.db";
    private static final int DATABASE_VERSION = 1;

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PPARTabelHelper.createPPARTable());
        db.execSQL(ContactPersonTableHelper.createContactPersonTable());
        db.execSQL(ZSComponentsTableHelper.createZSComponentsTable());
        db.execSQL(ZsElectricalProtectionTableHelper.createZsElectricalProtectionTable());
        db.execSQL(ProjectTableHelper.createProjectTable());
        db.execSQL(BuildingTableHelper.createBuildingTable());
    }

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
            Toast.makeText(context, "Failed to Add Project", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Toast.makeText(context, "Project Added Successfully", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    public boolean deleteProject(ProjectModel projectModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + ProjectTableHelper.getTableName_PROJECT() + " WHERE " +
                ProjectTableHelper.getColumnProjectId() + " = " + projectModel.getProjectID();

        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addBuilding(BuildingModel buildingModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(BuildingTableHelper.getColumn_BUILDING_NAME(), buildingModel.getBuildingName());
        cv.put(BuildingTableHelper.getColumn_DATE_OF_MEASUREMENTS(), buildingModel.getDateOfMeasurements());
        cv.put(BuildingTableHelper.getColumn_CITY(), buildingModel.getCity());
        cv.put(BuildingTableHelper.getColumn_POSTCODE(), buildingModel.getPostcode());
        cv.put(BuildingTableHelper.getColumn_STREET(), buildingModel.getStreet());
        cv.put(BuildingTableHelper.getColumn_BUILDING_NUMBER(), buildingModel.getBuildingNumber());
        cv.put(BuildingTableHelper.getColumn_PROJECT_ID(), buildingModel.getProjectID());
        cv.put(BuildingTableHelper.getColumn_CONTACT_PERSON_ID(), buildingModel.getContactPersonID());

        long insert = db.insert(BuildingTableHelper.getTableName_BUILDING(), null, cv);
        if (insert == -1) {
            Toast.makeText(context, "Failed to Add Building", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Toast.makeText(context, "Building Added Successfully", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

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
}