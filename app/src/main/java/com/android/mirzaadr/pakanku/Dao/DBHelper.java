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
    public static final String TABLE_HEWAN = "TABLE_HEWAN";
    public static final String TABLE_RECORD = "TABLE_RECORD";

    // columns of the bahan harga table
    public static final String BAHAN_ID = "idbahan";
    public static final String NAMA_BAHAN = "nama_bahan";
    public static final String BK_PRS = "bk_prs";
    public static final String PK_PRS = "pk_prs";
    public static final String KATEGORI = "kategori";
    public static final String HARGA = "harga";

    // columns of the hewan table
    public static final String HEWAN_ID = "idhewan";
    public static final String HEWAN = "hewan";
    public static final String TUJUAN = "tujuan";
    public static final String HIJAU = "hijau";
    public static final String KONSENTRAT = "konsentrat";
    public static final String BK_HEWAN = "bk_hewan";
    public static final String PK_HEWAN = "pk_hewan";
    //public static final String ADG = "adg";
    public static final String HARGA_JUAL = "harga_jual";

    // columns of the version table
    public static final String TANGGAL = "tanggal";
    public static final String VERSION = "version";

    // columns of the record table
    public static final String RECORD_ID = "idrecord";
    public static final String RTANGGAL = "rtanggal";
    public static final String RHEWAN = "rhewan";
    public static final String RTUJUAN = "rtujuan";
    public static final String RBERAT = "rberat";
    public static final String RJTERNAK = "rjternak";
    public static final String PBAHAN = "pbahan";
    public static final String JBAHAN = "jbahan";
    public static final String RTUANG = "rtuang";
    public static final String RTUNTUNG = "rtuntung";

    private static final String DATABASE_NAME = "pakanku.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_BAHAN = "create table " + TABLE_BAHAN + "(" + BAHAN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAMA_BAHAN + " TEXT NOT NULL, " + BK_PRS + " DOUBLE NOT NULL, " + PK_PRS + " DOUBLE NOT NULL, " + KATEGORI + " TEXT NOT NULL, " + HARGA + " INTEGER);";

    private static final String CREATE_TABLE_VERSION = "create table " + TABLE_VERSION + "(" + VERSION
            + " TEXT PRIMARY KEY NOT NULL, " + TANGGAL + " TEXT NOT NULL);";

    private static final String CREATE_TABLE_HEWAN = "create table " + TABLE_HEWAN + "(" + HEWAN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + HEWAN + " TEXT NOT NULL, "+ TUJUAN + " TEXT NOT NULL, " + HIJAU + " DOUBLE NOT NULL," + KONSENTRAT + " DOUBLE NOT NULL, " + BK_HEWAN + " DOUBLE NOT NULL, " + PK_HEWAN + " DOUBLE NOT NULL, " + HARGA_JUAL + " INTEGER);";

    private static final String CREATE_TABLE_RECORD = "create table " + TABLE_RECORD + "(" + RECORD_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + RTANGGAL + " TEXT NOT NULL, "+ RHEWAN + " TEXT NOT NULL, " + RTUJUAN + " TEXT NOT NULL," + RBERAT + " DOUBLE NOT NULL, "  + RJTERNAK + " INTEGER NOT NULL, "  + PBAHAN + " TEXT NOT NULL, "  + JBAHAN + " TEXT NOT NULL, "  + RTUANG + " INTEGER NOT NULL, "  + RTUNTUNG + " INTEGER);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_TABLE_BAHAN);
        database.execSQL(CREATE_TABLE_VERSION);
        database.execSQL(CREATE_TABLE_HEWAN);
        database.execSQL(CREATE_TABLE_RECORD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading the database from version " + oldVersion + " to " + newVersion);
        // clear all data
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BAHAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VERSION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HEWAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORD);

        // recreate the tables
        onCreate(db);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

}
