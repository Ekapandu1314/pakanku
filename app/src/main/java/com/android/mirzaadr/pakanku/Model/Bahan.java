package com.android.mirzaadr.pakanku.Model;

import java.io.Serializable;

/**
 * Created by Eka Pandu Winata on 4/2/2016.
 */
public class Bahan implements Serializable {

    public static final String TAG = "Bahan";
    private static final long serialVersionUID = -7406082437623008161L;

    private int idbahan;
    private String nama_bahan;
    private double bk_gr;
    private double tdn_gr;
    private double pk_gr;
    private double ca;
    private double p;
    private double bk_prs;
    private double tdn_prs;
    private double pk_prs;
    private String kategori;
    private String tanggal;
    private int harga;


    public Bahan() {

    }

    public Bahan(int idbahan, String nama_bahan, double bk_gr, double tdn_gr,
                 double pk_gr, double ca, double p, double bk_prs,
                 double tdn_prs, double pk_prs, String kategori, String tanggal, int harga)
    {
        this.idbahan = idbahan;
        this.nama_bahan = nama_bahan;
        this.bk_gr = bk_gr;
        this.tdn_gr = tdn_gr;
        this.pk_gr = pk_gr;
        this.ca = ca;
        this.p = p;
        this.bk_prs = bk_prs;
        this.tdn_prs = tdn_prs;
        this.pk_prs = pk_prs;
        this.kategori = kategori;
        this.tanggal = tanggal;
        this.harga = harga;
    }

    public Bahan(String nama_bahan, double bk_gr, double tdn_gr,
                 double pk_gr, double ca, double p, double bk_prs,
                 double tdn_prs, double pk_prs, String kategori, String tanggal, int harga)
    {
        this.nama_bahan = nama_bahan;
        this.bk_gr = bk_gr;
        this.tdn_gr = tdn_gr;
        this.pk_gr = pk_gr;
        this.ca = ca;
        this.p = p;
        this.bk_prs = bk_prs;
        this.tdn_prs = tdn_prs;
        this.pk_prs = pk_prs;
        this.kategori = kategori;
        this.tanggal = tanggal;
        this.harga = harga;
    }

    public void setIdBahan(int id) {
        this.idbahan = id;
    }

    public void setNamaBahan(String nama_bahan) {
        this.nama_bahan = nama_bahan;
    }

    public void setBk_gr(double bk_gr) {
        this.bk_gr = bk_gr;
    }

    public void setTdn_gr(double tdn_gr) {
        this.tdn_gr = tdn_gr;
    }

    public void setPk_gr(double pk_gr) {
        this.pk_gr = pk_gr;
    }

    public void setCa(double ca) {
        this.ca = ca;
    }

    public void setP(double p) {
        this.p = p;
    }

    public void setBk_prs(double bk_prs) {
        this.bk_prs = bk_prs;
    }

    public void setTdn_prs(double tdn_prs) {
        this.tdn_prs = tdn_prs;
    }

    public void setPk_prs(double pk_prs) {
        this.pk_prs = pk_prs;
    }

    public void setKategori(String kategori)
    {
        this.kategori = kategori;
    }

    public void setHarga(int harga)
    {
        this.harga = harga;
    }

    public int getIdbahan() {
        return this.idbahan;
    }

    public String getNama_bahan() {
        return this.nama_bahan;
    }

    public double getBk_gr() {
        return this.bk_gr;
    }

    public double getTdn_gr() {
        return this.tdn_gr;
    }

    public double getPk_gr() {
        return this.pk_gr;
    }

    public double getCa() {
        return this.ca;
    }

    public double getP() {
        return this.p;
    }

    public double getBk_prs() {
        return this.bk_prs;
    }

    public double getTdn_prs() {
        return this.tdn_prs;
    }

    public double getPk_prs() {
        return this.pk_prs;
    }

    public String getKategori()
    {
        return this.kategori;
    }

    public int getHarga()
    {
        return this.harga;
    }

}
