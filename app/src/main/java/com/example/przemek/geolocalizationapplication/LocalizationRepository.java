package com.example.przemek.geolocalizationapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Przemek on 26.01.2018.
 */

class LocalizationRepository {
    private final SQLiteDatabase database;

    LocalizationRepository(Context context) {
        File mDatabaseFile = context.getDatabasePath("smb_location.db").getAbsoluteFile();
        database = SQLiteDatabase.openOrCreateDatabase(mDatabaseFile, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS Locations(Id VARCHAR PRIMARY KEY, Name VARCHAR, Description VARCHAR, Radius NUMERIC, Lat NUMERIC, Lng NUMERIC);");
    }

    public void addLocation(Localization position) {
        ContentValues insertValues = new ContentValues();
        insertValues.put("Id", position.getId().toString());
        insertValues.put("Name", position.getName());
        insertValues.put("Description", position.getDesc());
        insertValues.put("Radius", position.getRadius());
        insertValues.put("Lat", position.getLatitude());
        insertValues.put("Lng", position.getLongitude());
        database.insert("Locations", null, insertValues);
    }

    public ArrayList<Localization> getAllPositions() {
        Cursor cursor = database.rawQuery("select * from Locations", null);
        ArrayList<Localization> listResult = new ArrayList<>();

        if (cursor.moveToFirst()) {

            while (cursor.isAfterLast() == false) {
                String id = cursor.getString(cursor.getColumnIndex("Id"));
                String name = cursor.getString(cursor.getColumnIndex("Name"));
                String desc = cursor.getString(cursor.getColumnIndex("Description"));
                double radius = cursor.getDouble(cursor.getColumnIndex("Radius"));
                double lat = cursor.getDouble(cursor.getColumnIndex("Lat"));
                double lng = cursor.getDouble(cursor.getColumnIndex("Lng"));
                listResult.add(new Localization(UUID.fromString(id), name, desc, radius, lat, lng));
                cursor.moveToNext();
            }
        }

        return listResult;
    }

}
