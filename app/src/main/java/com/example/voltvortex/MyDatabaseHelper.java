package com.example.voltvortex;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class MyDatabaseHelper extends SQLiteOpenHelper{

    private Context context;
    private static final String DATABASE_NAME = "VoltVortex.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "projekt";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PROJECT_NAME = "project_name";
    private static final String COLUMN_IS_MANY_CITIES = "is_many_cities";
    private static final String COLUMN_IS_MANY_CONTACT_PERSONS = "is_many_contact_persons";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PROJECT_NAME + " TEXT, " +
                COLUMN_IS_MANY_CITIES + " BOOL, " +
                COLUMN_IS_MANY_CONTACT_PERSONS + " BOOL);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean addProject(ProjectModel projectModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PROJECT_NAME, projectModel.getProjectName());
        cv.put(COLUMN_IS_MANY_CITIES, projectModel.isManyCities());
        cv.put(COLUMN_IS_MANY_CONTACT_PERSONS, projectModel.isManyContactPerson());

        long insert = db.insert(TABLE_NAME, null, cv);
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
        String queryString = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + projectModel.getId();

        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            return true;
        } else {
            return false;
        }

    }

    public List<ProjectModel> viewProjectList(){

        List<ProjectModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do {

                int projectID = cursor.getInt(0);
                String projectName = cursor.getString(1);
                boolean projectIsManyCities = cursor.getInt(2) == 1;
                boolean projectIsManyContactPersons = cursor.getInt(3) == 1;

                ProjectModel newProjectModel = new ProjectModel
                        (projectID, projectName, projectIsManyCities, projectIsManyContactPersons);

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
