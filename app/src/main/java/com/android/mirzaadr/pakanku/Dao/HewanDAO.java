package com.android.mirzaadr.pakanku.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.mirzaadr.pakanku.Model.Hewan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eka Pandu Winata on 4/4/2016.
 */
public class HewanDAO {

    public static final String TAG = "HewanDAO";

    private Context mContext;

    // Database fields
    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private String[] mAllColumns = { DBHelper.HEWAN_ID,
            DBHelper.HEWAN,
            DBHelper.TUJUAN,
            DBHelper.HIJAU,
            DBHelper.KONSENTRAT,
            DBHelper.BK_HEWAN,
            DBHelper.PK_HEWAN,
            DBHelper.HARGA_JUAL
    };

    public HewanDAO(Context context) {
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

    public Hewan createHewan(String hewan,String tujuan,
                             double hijau, double konsentrat, double bk_hewan, double pk_hewan, int harga_jual) {
        ContentValues values = new ContentValues();
        //values.put(DBHelper.BAHAN_ID, idbahan);
        values.put(DBHelper.HEWAN, hewan);
        values.put(DBHelper.TUJUAN, tujuan);
        values.put(DBHelper.HIJAU, hijau);
        values.put(DBHelper.KONSENTRAT, konsentrat);
        values.put(DBHelper.BK_HEWAN, bk_hewan);
        values.put(DBHelper.PK_HEWAN, pk_hewan);
        values.put(DBHelper.HARGA_JUAL, harga_jual);

        int insertId = (int) mDatabase
                .insert(DBHelper.TABLE_HEWAN, null, values);
        Cursor cursor = mDatabase.query(DBHelper.TABLE_HEWAN, mAllColumns,
                DBHelper.HEWAN_ID + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Hewan newHewan = cursorToHewan(cursor);
        cursor.close();
        return newHewan;
    }

    public void deleteHewan(Hewan hewan) {
        int id = hewan.getIdhewan();
        System.out.println("the deleted ingredient has the id: " + id);
        mDatabase.delete(DBHelper.TABLE_HEWAN, DBHelper.HEWAN_ID
                + " = " + id, null);
    }

    public void addHewanJson(Hewan hewan) {
        try{
            ContentValues values = new ContentValues();
            values.put(DBHelper.HEWAN_ID, hewan.getIdhewan());
            values.put(DBHelper.HEWAN, hewan.getHewan());
            values.put(DBHelper.TUJUAN, hewan.getTujuan());
            values.put(DBHelper.HIJAU, hewan.getHijau());
            values.put(DBHelper.KONSENTRAT, hewan.getKonsentrat());
            values.put(DBHelper.BK_HEWAN, hewan.getBk_hewan());
            values.put(DBHelper.PK_HEWAN, hewan.getPk_hewan());
            values.put(DBHelper.HARGA_JUAL, hewan.getHargajual());

            mDatabase.insert(DBHelper.TABLE_HEWAN, null, values);

            mDatabase.update(DBHelper.TABLE_HEWAN, values, DBHelper.HEWAN_ID + " = " + hewan.getIdhewan(), null);


            //mDatabase.close();
        }catch (Exception e){
            Log.e("problem",e+"");
        }
    }

    public List<Hewan> getAllHewan() {
        List<Hewan> listHewan = new ArrayList<Hewan>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_HEWAN, mAllColumns, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Hewan hewan = cursorToHewan(cursor);
                listHewan.add(hewan);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();
        }
        return listHewan;
    }

    public Hewan getHewanById(int id) {
        Cursor cursorxx = mDatabase.query(DBHelper.TABLE_HEWAN, mAllColumns, DBHelper.HEWAN_ID + " = ",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursorxx != null)
            cursorxx.moveToFirst();

        Hewan hewan = cursorToHewan(cursorxx);
        // return contact
        return hewan;

    }

    public Hewan getHewanByHewanTujuan(String hewan, String tujuan)
    {
        String countQuery = "SELECT * FROM " + DBHelper.TABLE_HEWAN + " WHERE " + DBHelper.HEWAN +
                " = '" + hewan + "' and " + DBHelper.TUJUAN + " = '" + tujuan + "'";
        Cursor cursor = mDatabase.rawQuery(countQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        Hewan newHewan = cursorToHewan(cursor);
        cursor.close();
        return newHewan;
    }


    private Hewan cursorToHewan(Cursor cursor) {
        Hewan hewan = new Hewan();

        hewan.setIdhewan(cursor.getInt(0));
        hewan.setHewan(cursor.getString(1));
        hewan.setTujuan(cursor.getString(2));
        hewan.setHijau(cursor.getDouble(3));
        hewan.setKonsentrat(cursor.getDouble(4));
        hewan.setBk_hewan(cursor.getDouble(5));
        hewan.setPk_hewan(cursor.getDouble(6));
        hewan.setHargajual(cursor.getInt(7));
        return hewan;
    }

}
