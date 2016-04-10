package com.android.mirzaadr.pakanku.Model;

import java.io.Serializable;

/**
 * Created by Eka Pandu Winata on 4/4/2016.
 */
public class Hewan implements Serializable {

    public static final String TAG = "Hewan";
    private static final long serialVersionUID = -7406082437623008161L;

    private int idhewan;
    private String hewan;
    private String tujuan;
    private double hijau;
    private double konsentrat;
    private double bk_hewan;
    private double pk_hewan;
    private double adg;
    private int harga_jual;

    public Hewan() {

    }

    public Hewan(int idhewan, String hewan, String tujuan,
                 double hijau, double konsentrat, double bk_hewan, double pk_hewan, double adg, int harga_jual)
    {
        this.idhewan = idhewan;
        this.hewan = hewan;
        this.tujuan = tujuan;
        this.hijau = hijau;
        this.konsentrat = konsentrat;
        this.bk_hewan = bk_hewan;
        this.pk_hewan = pk_hewan;
        this.adg = adg;
        this.harga_jual = harga_jual;
    }

    public Hewan(String hewan, String tujuan,
                 double hijau, double konsentrat, double bk_hewan, double pk_hewan, double adg, int harga_jual)
    {
        this.hewan = hewan;
        this.tujuan = tujuan;
        this.hijau = hijau;
        this.konsentrat = konsentrat;
        this.bk_hewan = bk_hewan;
        this.pk_hewan = pk_hewan;
        this.adg = adg;
        this.harga_jual = harga_jual;
    }

    public void setIdhewan(int id) {
        this.idhewan = id;
    }

    public void setHewan(String hewan) {
        this.hewan = hewan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public void setHijau(double hijau) {
        this.hijau = hijau;
    }

    public void setKonsentrat(double konsentrat)
    {
        this.konsentrat = konsentrat;
    }

    public void setBk_hewan(double bk_hewan)
    {
        this.bk_hewan = bk_hewan;
    }

    public void setPk_hewan(double pk_hewan)
    {
        this.pk_hewan = pk_hewan;
    }

    public void setAdg(double adg)
    {
        this.adg = adg;
    }

    public void setHargajual(int harga_jual)
    {
        this.harga_jual = harga_jual;
    }

    public int getIdhewan() {
        return this.idhewan;
    }

    public String getHewan() {
        return this.hewan;
    }

    public String getTujuan() {
        return this.tujuan;
    }

    public double getHijau() {
        return this.hijau;
    }

    public double getKonsentrat()
    {
        return this.konsentrat;
    }

    public double getBk_hewan()
    {
        return this.bk_hewan;
    }

    public double getPk_hewan()
    {
        return this.pk_hewan;
    }

    public double getAdg()
    {
        return this.adg;
    }

    public int getHargajual()
    {
        return this.harga_jual;
    }

}
