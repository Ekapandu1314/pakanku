package com.android.mirzaadr.pakanku.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.mirzaadr.pakanku.Model.Bahan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eka Pandu Winata on 4/2/2016.
 */
public class BahanDAO {

    public static final String TAG = "BahanDAO";

    private Context mContext;

    // Database fields
    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private String[] mAllColumns = { DBHelper.BAHAN_ID,
            DBHelper.NAMA_BAHAN,
            DBHelper.BK_PRS,
            DBHelper.PK_PRS,
            DBHelper.KATEGORI,
            DBHelper.HARGA
    };

    public BahanDAO(Context context) {
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

    public Bahan createBahan(String nama_bahan,double bk_prs,
                             double pk_prs, String kategori, int harga) {
        ContentValues values = new ContentValues();
        //values.put(DBHelper.BAHAN_ID, idbahan);
        values.put(DBHelper.NAMA_BAHAN, nama_bahan);
        values.put(DBHelper.BK_PRS, bk_prs);
        values.put(DBHelper.PK_PRS, pk_prs);
        values.put(DBHelper.KATEGORI, kategori);
        values.put(DBHelper.HARGA, harga);

        int insertId = (int) mDatabase
                .insert(DBHelper.TABLE_BAHAN, null, values);
        Cursor cursor = mDatabase.query(DBHelper.TABLE_BAHAN, mAllColumns,
                DBHelper.BAHAN_ID + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Bahan newBahan = cursorToBahan(cursor);
        cursor.close();
        return newBahan;
    }

    public void deleteBahan(Bahan bahan) {
        int id = bahan.getIdbahan();
        System.out.println("the deleted ingredient has the id: " + id);
        mDatabase.delete(DBHelper.TABLE_BAHAN, DBHelper.BAHAN_ID
                + " = " + id, null);
    }

    public void addBahanJson(Bahan bahan) {
        //SQLiteDatabase db = this.getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put(DBHelper.BAHAN_ID, bahan.getIdbahan());
            values.put(DBHelper.NAMA_BAHAN, bahan.getNamaBahan());
            values.put(DBHelper.BK_PRS, bahan.getBk_prs());
            values.put(DBHelper.PK_PRS, bahan.getPk_prs());
            values.put(DBHelper.KATEGORI,bahan.getKategori());
            values.put(DBHelper.HARGA, bahan.getHarga());

            mDatabase.insert(DBHelper.TABLE_BAHAN, null, values);

            mDatabase.update(DBHelper.TABLE_BAHAN, values, DBHelper.BAHAN_ID + " = " + bahan.getIdbahan(), null);


            //mDatabase.close();
        }catch (Exception e){
            Log.e("problem",e+"");
        }
    }

    public List<Bahan> getAllBahan() {
        List<Bahan> listBahan = new ArrayList<Bahan>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_BAHAN, mAllColumns, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Bahan bahan = cursorToBahan(cursor);
                listBahan.add(bahan);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();
        }
        return listBahan;
    }

    public Bahan getBahanById(int id) {
        String countQuery = "SELECT  * FROM " + DBHelper.TABLE_BAHAN + " WHERE " + DBHelper.BAHAN_ID +
                " = " + id;
        Cursor cursor = mDatabase.rawQuery(countQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        Bahan newBahan = cursorToBahan(cursor);
        cursor.close();
        return newBahan;

    }

    public ArrayList<Bahan> getAllBahanArray() {

        ArrayList<Bahan> bahanList = null;
        try {
            bahanList = new ArrayList<Bahan>();
            String QUERY = "SELECT * FROM " + DBHelper.TABLE_BAHAN;
            Cursor cursor = mDatabase.rawQuery(QUERY, null);
            if (!cursor.isLast()) {
                while (cursor.moveToNext()) {
                    Bahan bahan = new Bahan();
                    bahan.setIdBahan(cursor.getInt(0));
                    bahan.setNamaBahan(cursor.getString(1));
                    bahan.setBk_prs(cursor.getDouble(2));
                    bahan.setPk_prs(cursor.getDouble(3));
                    bahan.setKategori(cursor.getString(4));
                    bahan.setHarga(cursor.getInt(5));
                    bahanList.add(bahan);
                }
            }
            //db.close();
        } catch (Exception e) {
            Log.e("error", e + "");
        }
        return bahanList;
    }

    /*public void updateBahanJSON(Bahan bahan) {

        ContentValues values = new ContentValues();
        values.put(DBHelper.BAHAN_ID, bahan.getIdbahan());
        values.put(DBHelper.NAMA_BAHAN, bahan.getNama_bahan());
        values.put(DBHelper.BK_GR,bahan.getBk_gr());
        values.put(DBHelper.TDN_GR, bahan.getTdn_gr());
        values.put(DBHelper.PK_GR, bahan.getPk_gr());
        values.put(DBHelper.CA,bahan.getCa());
        values.put(DBHelper.P, bahan.getP());
        values.put(DBHelper.BK_PRS, bahan.getBk_prs());
        values.put(DBHelper.TDN_PRS,bahan.getTdn_prs());
        values.put(DBHelper.PK_PRS, bahan.getPk_prs());
        values.put(DBHelper.KATEGORI,bahan.getKategori());
        values.put(DBHelper.HARGA, bahan.getHarga());

    }*/

    public int getBahanCount() {
        String countQuery = "SELECT  * FROM " + DBHelper.TABLE_BAHAN;
        Cursor cursor = mDatabase.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    private Bahan cursorToBahan(Cursor cursor) {
        Bahan bahan = new Bahan();

        bahan.setIdBahan(cursor.getInt(0));
        bahan.setNamaBahan(cursor.getString(1));
        bahan.setBk_prs(cursor.getDouble(2));
        bahan.setPk_prs(cursor.getDouble(3));
        bahan.setKategori(cursor.getString(4));
        bahan.setHarga(cursor.getInt(5));
        return bahan;
    }
}
