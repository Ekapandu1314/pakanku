package com.android.mirzaadr.pakanku.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.mirzaadr.pakanku.Model.Bahan;
import com.android.mirzaadr.pakanku.Model.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eka Pandu Winata on 4/4/2016.
 */
public class RecordDAO {

    public static final String TAG = "RecordDAO";

    private Context mContext;

    // Database fields
    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private String[] mAllColumns = { DBHelper.RECORD_ID,
            DBHelper.NAMA_RECORD,
            DBHelper.RTANGGAL,
            DBHelper.RHEWAN,
            DBHelper.RTUJUAN,
            DBHelper.RBERAT1,
            DBHelper.RBERAT2,
            DBHelper.RJTERNAK,
            DBHelper.RLAMA,
            DBHelper.PBAHAN

    };

    public RecordDAO(Context context) {
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

    public Record createRecordAdg(String nama_record,
                               String rtanggal,
                               String rhewan,
                               String rtujuan,
                               double rberat1,
                               double rberat2,
                               int rjternak,
                               int rlama,
                               String pbahan
) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.NAMA_RECORD, nama_record);
        values.put(DBHelper.RTANGGAL, rtanggal);
        values.put(DBHelper.RHEWAN, rhewan);
        values.put(DBHelper.RTUJUAN, rtujuan);
        values.put(DBHelper.RBERAT1, rberat1);
        values.put(DBHelper.RBERAT2, rberat2);
        values.put(DBHelper.RJTERNAK, rjternak);
        values.put(DBHelper.RLAMA, rlama);
        values.put(DBHelper.PBAHAN, pbahan);

        int insertId = (int) mDatabase
                .insert(DBHelper.TABLE_RECORD, null, values);
        Cursor cursor = mDatabase.query(DBHelper.TABLE_RECORD, mAllColumns,
                DBHelper.RECORD_ID + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Record newRecord = cursorToRecord(cursor);
        cursor.close();
        return newRecord;
    }

    public Record createRecord(String nama_record,
                                  String rtanggal,
                                  String rhewan,
                                  String rtujuan,
                                  double rberat,
                                  int rjternak,
                                  int rlama,
                                  String pbahan
) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.NAMA_RECORD, nama_record);
        values.put(DBHelper.RTANGGAL, rtanggal);
        values.put(DBHelper.RHEWAN, rhewan);
        values.put(DBHelper.RTUJUAN, rtujuan);
        values.put(DBHelper.RBERAT1, rberat);
        values.putNull(DBHelper.RBERAT2);
        values.put(DBHelper.RJTERNAK, rjternak);
        values.put(DBHelper.RLAMA, rlama);
        values.put(DBHelper.PBAHAN, pbahan);


        int insertId = (int) mDatabase
                .insert(DBHelper.TABLE_RECORD, null, values);
        Cursor cursor = mDatabase.query(DBHelper.TABLE_RECORD, mAllColumns,
                DBHelper.RECORD_ID + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Record newRecord = cursorToRecord(cursor);
        cursor.close();
        return newRecord;
    }

    public Record createRecord(String nama_record,
                               String rtanggal,
                               String rhewan,
                               String rtujuan,
                               double rberat,
                               double produk,
                               int rjternak,
                               int rlama,
                               String pbahan
    ) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.NAMA_RECORD, nama_record);
        values.put(DBHelper.RTANGGAL, rtanggal);
        values.put(DBHelper.RHEWAN, rhewan);
        values.put(DBHelper.RTUJUAN, rtujuan);
        values.put(DBHelper.RBERAT1, rberat);
        values.put(DBHelper.RBERAT2, produk);
        values.put(DBHelper.RJTERNAK, rjternak);
        values.put(DBHelper.RLAMA, rlama);
        values.put(DBHelper.PBAHAN, pbahan);


        int insertId = (int) mDatabase
                .insert(DBHelper.TABLE_RECORD, null, values);
        Cursor cursor = mDatabase.query(DBHelper.TABLE_RECORD, mAllColumns,
                DBHelper.RECORD_ID + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Record newRecord = cursorToRecord(cursor);
        cursor.close();
        return newRecord;
    }

    public void deleteRecord(Record record) {
        int id = record.getIdrecord();
        System.out.println("the deleted ingredient has the id: " + id);
        mDatabase.delete(DBHelper.TABLE_RECORD, DBHelper.RECORD_ID
                + " = " + id, null);
    }

    public List<Record> getAllRecord() {
        List<Record> listRecord = new ArrayList<Record>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_RECORD, mAllColumns, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Record record = cursorToRecord(cursor);
                listRecord.add(record);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();
        }
        return listRecord;
    }

    public List<Record> getAllRecordByHewan(String hewan) {

        List<Record> listRecord = new ArrayList<Record>();
        String countQuery;

        if(hewan.equals("Semua")) {
            countQuery = "SELECT  * FROM " + DBHelper.TABLE_RECORD;
        }
        else {
            countQuery = "SELECT  * FROM " + DBHelper.TABLE_RECORD + " WHERE " + DBHelper.RHEWAN +
                    " = '" + hewan + "'";
        }

        Cursor cursor = mDatabase.rawQuery(countQuery, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Record record = cursorToRecord(cursor);
                listRecord.add(record);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();
        }
        return listRecord;
    }

    public Record getRecordById(int id) {

        String countQuery = "SELECT * FROM " + DBHelper.TABLE_RECORD + " WHERE " + DBHelper.RECORD_ID +
                " = " + id;
        Cursor cursor = mDatabase.rawQuery(countQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        Record newRecord = cursorToRecord(cursor);
        cursor.close();
        return newRecord;

    }



    private Record cursorToRecord(Cursor cursor) {
        Record record = new Record();

        record.setIdrecord(cursor.getInt(0));
        record.setNama_record(cursor.getString(1));
        record.setRtanggal(cursor.getString(2));
        record.setRhewan(cursor.getString(3));
        record.setRtujuan(cursor.getString(4));
        record.setRberat1(cursor.getDouble(5));
        record.setRberat2(cursor.getDouble(6));
        record.setRjternak(cursor.getInt(7));
        record.setRlama(cursor.getInt(8));
        record.setPbahan(cursor.getString(9));

        return record;
    }

}
