package com.example.voltvortex.DataBaseHelper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.example.voltvortex.ProjectModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.voltvortex.DataBaseHelper.CreateTableHelpers.ProjectTableHelper.*;
import static com.example.voltvortex.DataBaseHelper.CreateTableHelpers.BuildingTableHelper.*;
import static com.example.voltvortex.DataBaseHelper.CreateTableHelpers.PPARTabelHelper.*;
import static com.example.voltvortex.DataBaseHelper.CreateTableHelpers.ZSComponentsTableHelper.*;
import static com.example.voltvortex.DataBaseHelper.CreateTableHelpers.ZsIrregularElectricalProtectionTableHelper.*;
import static com.example.voltvortex.DataBaseHelper.CreateTableHelpers.ZsRegularElectricalProtectionTableHelper.*;
import static com.example.voltvortex.DataBaseHelper.CreateTableHelpers.FloorAndRoomTableHelper.*;

public class MyDatabaseHelper extends SQLiteOpenHelper{

    private Context context;
    private static final String DATABASE_NAME = "VoltVortex.db";
    private static final int DATABASE_VERSION = 1;

    /*private static final String TABLE_NAME = "projekt";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PROJECT_NAME = "project_name";
    private static final String COLUMN_IS_MANY_CITIES = "is_many_cities";
    private static final String COLUMN_IS_MANY_CONTACT_PERSONS = "is_many_contact_persons";*/

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNecessaryTable =
                createProjectTable() +
                        createBuildingTable() +
                        createPPARTable() +
                        createZSComponentsTable() +
                        createZsIrregularElectricalProtectionTable() +
                        createZsRegularElectricalProtectionTable() +
                        createFloorAndRoomTable();
        db.execSQL(createNecessaryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + getTableName_PROJECT());
        onCreate(db);
    }


    public boolean addProject(ProjectModel projectModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(getColumnProjectName(), projectModel.getProjectName());
        cv.put(getColumnIsSingleContactPerson(), projectModel.isSingleContactPerson());

        long insert = db.insert(getTableName_PROJECT(), null, cv);
        if (insert == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    public boolean deleteProject(ProjectModel projectModel){

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + getTableName_PROJECT() + " WHERE " + getColumnId() + " = " + projectModel.getProjectID();

        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            return true;
        } else {
            return false;
        }

    }

    public List<ProjectModel> viewProjectList(){

        List<ProjectModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + getTableName_PROJECT();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do {

                int projectID = cursor.getInt(0);
                String projectName = cursor.getString(1);
                String projectFirm = cursor.getString(2);
                String projectDescription = cursor.getString(3);
                int contactPersonID = cursor.getInt(4);
                boolean projectIsSingleContactPerson = cursor.getInt(5) == 1;

                ProjectModel newProjectModel = new ProjectModel
                        (projectID, projectName, projectFirm, projectDescription, contactPersonID, projectIsSingleContactPerson);

                returnList.add(newProjectModel);

            } while (cursor.moveToNext());

        } else {
            //failure. do not add anything to the list
        }

        cursor.close();
        db.close();
        return returnList;
    }

}
