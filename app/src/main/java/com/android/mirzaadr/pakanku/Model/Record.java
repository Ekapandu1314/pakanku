package com.android.mirzaadr.pakanku.Model;

import java.io.Serializable;

/**
 * Created by Eka Pandu Winata on 4/4/2016.
 */
public class Record implements Serializable {

    public static final String TAG = "Record";
    private static final long serialVersionUID = -7406082437623008161L;

    private int idrecord;
    private String nama_record;
    private String rtanggal;
    private String rhewan;
    private String rtujuan;
    private double rberat1;
    private double rberat2;
    private int rjternak;
    private int rlama;
    private String pbahan;


    public Record() {

    }

    public Record(int idrecord,
             String nama_record,
             String rtanggal,
             String rhewan,
             String rtujuan,
             double rberat1,
             double rberat2,
             int rjternak,
             int rlama,
             String pbahan,
             int rtuang,
             int rtuntung)
    {
        this.idrecord = idrecord;
        this.nama_record = nama_record;
        this.rtanggal = rtanggal;
        this.rhewan = rhewan;
        this.rtujuan = rtujuan;
        this.rberat1 = rberat1;
        this.rberat2 = rberat2;
        this.rjternak = rjternak;
        this.rlama = rlama;
        this.pbahan = pbahan;
    }

    public Record( String nama_record,
                 String rtanggal,
                 String rhewan,
                 String rtujuan,
                 double rberat1,
                 double rberat2,
                 int rjternak,
                 int rlama,
                 String pbahan,
                 int rtuang,
                 int rtuntung)
    {
        this.nama_record = nama_record;
        this.rtanggal = rtanggal;
        this.rhewan = rhewan;
        this.rtujuan = rtujuan;
        this.rberat1 = rberat1;
        this.rberat2 = rberat2;
        this.rjternak = rjternak;
        this.rlama = rlama;
        this.pbahan = pbahan;
    }

    public void setIdrecord(int id) {
        this.idrecord = id;
    }

    public void setNama_record(String nama_record) {
        this.nama_record = nama_record;
    }

    public int getIdrecord() {
        return this.idrecord;
    }

    public String getNama_record() {
        return this.nama_record;
    }

    public void setRtanggal(String rtanggal){
        this.rtanggal = rtanggal;
    }

    public String getRtanggal() {
        return this.rtanggal;
    }

    public void setRhewan(String rhewan) {
        this.rhewan = rhewan;
    }

    public String getRhewan(){
        return this.rhewan;
    }

    public void setRtujuan(String rtujuan) {
        this.rtujuan = rtujuan;
    }

    public String getRtujuan(){
        return this.rtujuan;
    }

    public void setRberat1(double rberat1) {
        this.rberat1 = rberat1;
    }

    public void setRberat2(double rberat2) {
        this.rberat2 = rberat2;
    }

    public double getRberat1() {
        return this.rberat1;
    }

    public double getRberat2() {
        return this.rberat2;
    }

    public void setRjternak(int rjternak) {
        this.rjternak = rjternak;
    }

    public int getRjternak() {
        return this.rjternak;
    }

    public void setRlama(int rlama) {
        this.rlama = rlama;
    }

    public int getRlama() {
        return this.rlama;
    }

    public void setPbahan(String pbahan) {
        this.pbahan = pbahan;
    }

    public String getPbahan() {
        return this.pbahan;
    }

}
