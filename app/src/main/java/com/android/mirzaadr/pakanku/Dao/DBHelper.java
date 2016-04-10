package com.android.mirzaadr.pakanku.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Eka Pandu Winata on 4/2/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String TAG = "DBHelper";

    // Table Name
    public static final String TABLE_BAHAN = "TABLE_BAHAN";
    public static final String TABLE_VERSION = "TABLE_VERSION";

    // columns of the bahan harga table
    public static final String BAHAN_ID = "idbahan";
    public static final String NAMA_BAHAN = "nama_bahan";
    public static final String BK_GR = "bk_gr";
    public static final String TDN_GR = "tdn_gr";
    public static final String PK_GR = "pk_gr";
    public static final String CA = "ca";
    public static final String P = "p";
    public static final String BK_PRS = "bk_prs";
    public static final String TDN_PRS = "tdn_prs";
    public static final String PK_PRS = "pk_prs";
    public static final String KATEGORI = "kategori";
    public static final String HARGA = "harga";

    // columns of the version table

    public static final String TANGGAL = "tanggal";
    public static final String VERSION = "version";

    private static final String DATABASE_NAME = "pakanku.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_BAHAN = "create table " + TABLE_BAHAN + "(" + BAHAN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAMA_BAHAN + " TEXT NOT NULL, " + BK_GR + " DOUBLE NOT NULL, " + TDN_GR + " DOUBLE NOT NULL, " + PK_GR + " DOUBLE NOT NULL, " + CA + " DOUBLE NOT NULL, " + P + " DOUBLE NOT NULL, " + BK_PRS + " DOUBLE NOT NULL, " + TDN_PRS + " DOUBLE NOT NULL, " + PK_PRS + " DOUBLE NOT NULL, " + KATEGORI + " TEXT NOT NULL, " + HARGA + " INTEGER NOT NULL);";

    private static final String CREATE_TABLE_VERSION = "create table " + TABLE_VERSION + "(" + VERSION
            + " TEXT PRIMARY KEY NOT NULL, " + TANGGAL + " TEXT NOT NULL);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_TABLE_BAHAN);
        database.execSQL(CREATE_TABLE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading the database from version " + oldVersion + " to " + newVersion);
        // clear all data
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BAHAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VERSION);

        // recreate the tables
        onCreate(db);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

}
