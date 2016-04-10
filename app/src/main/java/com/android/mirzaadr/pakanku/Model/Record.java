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
    private double rberat;
    private int rjternak;
    private String pbahan;
    private String jbahan;
    private int rtuang;
    private int rtuntung;


    public Record() {

    }

    public Record(int idrecord,
             String nama_record,
             String rtanggal,
             String rhewan,
             String rtujuan,
             double rberat,
             int rjternak,
             String pbahan,
             String jbahan,
             int rtuang,
             int rtuntung)
    {
        this.idrecord = idrecord;
        this.nama_record = nama_record;
        this.rtanggal = rtanggal;
        this.rhewan = rhewan;
        this.rtujuan = rtujuan;
        this.rberat = rberat;
        this.rjternak = rjternak;
        this.pbahan = pbahan;
        this.jbahan = jbahan;
        this.rtuang = rtuang;
        this.rtuntung = rtuntung;
    }

    public Record( String nama_record,
                 String rtanggal,
                 String rhewan,
                 String rtujuan,
                 double rberat,
                 int rjternak,
                 String pbahan,
                 String jbahan,
                 int rtuang,
                 int rtuntung)
    {
        this.nama_record = nama_record;
        this.rtanggal = rtanggal;
        this.rhewan = rhewan;
        this.rtujuan = rtujuan;
        this.rberat = rberat;
        this.rjternak = rjternak;
        this.pbahan = pbahan;
        this.jbahan = jbahan;
        this.rtuang = rtuang;
        this.rtuntung = rtuntung;
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

    public void setRberat(double rberat) {
        this.rberat = rberat;
    }

    public double getRberat() {
        return this.rberat;
    }

    public void setRjternak(int rjternak) {
        this.rjternak = rjternak;
    }

    public int getRjternak() {
        return this.rjternak;
    }

    public void setPbahan(String pbahan) {
        this.pbahan = pbahan;
    }

    public String getPbahan() {
        return this.pbahan;
    }

    public void setJbahan(String jbahan) {
        this.jbahan = jbahan;
    }

    public String getJbahan() {
        return this.jbahan;
    }

    public void setRtuang(int rtuang) {
        this.rtuang = rtuang;
    }

    public int getRtuang() {
        return rtuang;
    }

    public void setRtuntung(int rtuntung) {
        this.rtuntung = rtuntung;
    }

    public int getRtuntung() {
        return this.rtuntung;
    }

}
