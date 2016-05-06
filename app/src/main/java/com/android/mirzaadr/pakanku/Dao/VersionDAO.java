package com.android.mirzaadr.pakanku.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.mirzaadr.pakanku.Model.Version;

/**
 * Created by Eka Pandu Winata on 4/2/2016.
 */
public class VersionDAO  {

    public static final String TAG = "VersionDAO";

    private Context mContext;

    // Database fields
    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private String[] mAllColumns = {
            DBHelper.VERSION,
            DBHelper.TANGGAL,

    };

    public VersionDAO(Context context) {
        mDbHelper = new DBHelper(context);
        this.mContext = context;
        // open the database
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on openning database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public void deleteVersion(Version version) {
        String versi = version.getVersiBahan();
        System.out.println("the deleted version has the id: " + versi);
        mDatabase.delete(DBHelper.TABLE_VERSION, DBHelper.VERSION
                + " = " + versi, null);
    }

    public void addVersionJson(Version version) {
        try{
            ContentValues values = new ContentValues();

            values.put(DBHelper.VERSION, version.getVersiBahan());
            values.put(DBHelper.TANGGAL, version.getTanggal());


                mDatabase.insert(DBHelper.TABLE_VERSION, null, values);

                mDatabase.update(DBHelper.TABLE_VERSION, values, DBHelper.VERSION + " = " + version.getVersiBahan(), null);


            //mDatabase.close();
        }catch (Exception e){
            Log.e("problem",e+"");
        }
    }

     public Version createVersion(String version, String tanggal) {
        ContentValues values = new ContentValues();
        //values.put(DBHelper.BAHAN_ID, idbahan);
        values.put(DBHelper.VERSION, version);
        values.put(DBHelper.TANGGAL, tanggal);

        String insertId = String.valueOf(mDatabase
                .insert(DBHelper.TABLE_VERSION, null, values));
        Cursor cursor = mDatabase.query(DBHelper.TABLE_VERSION, mAllColumns,
                DBHelper.VERSION + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Version newVersion = cursorToVersion(cursor);
        cursor.close();
        return newVersion;
    }

    public Version getVersionByVersion(String version) {
        Cursor cursorxx = mDatabase.query(DBHelper.TABLE_VERSION, mAllColumns, DBHelper.VERSION + " = ? ",
                new String[] { version }, null, null, null, null);
        if (cursorxx != null)
            cursorxx.moveToFirst();

        Version newVersion = cursorToVersion(cursorxx);
        // return contact
        return newVersion;

    }

    public Version getTopVersion()
    {
        String countQuery = "SELECT  * FROM " + DBHelper.TABLE_VERSION + " LIMIT 1";
        Cursor cursor = mDatabase.rawQuery(countQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        Version versi = cursorToVersion(cursor);
        cursor.close();
        return versi;
    }

    private Version cursorToVersion(Cursor cursor) {
        Version version = new Version();

        version.setVersiBahan(cursor.getString(0));
        version.setTanggal(cursor.getString(1));

        return version;
    }

}
