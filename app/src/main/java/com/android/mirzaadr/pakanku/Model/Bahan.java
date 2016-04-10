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
    private double bk_prs;
    private double pk_prs;
    private String kategori;
    private int harga;
    private Boolean selected = false;


    public Bahan() {

    }

    public Bahan(int idbahan, String nama_bahan, double bk_prs,
                 double pk_prs, String kategori, int harga)
    {
        this.idbahan = idbahan;
        this.nama_bahan = nama_bahan;
        this.bk_prs = bk_prs;
        this.pk_prs = pk_prs;
        this.kategori = kategori;
        this.harga = harga;
    }

    public Bahan(String nama_bahan, double bk_prs,
                 double pk_prs, String kategori, int harga)
    {
        this.nama_bahan = nama_bahan;

        this.bk_prs = bk_prs;
        this.pk_prs = pk_prs;
        this.kategori = kategori;
        this.harga = harga;
    }

    public void setIdBahan(int id) {
        this.idbahan = id;
    }

    public void setNamaBahan(String nama_bahan) {
        this.nama_bahan = nama_bahan;
    }

    public void setBk_prs(double bk_prs) {
        this.bk_prs = bk_prs;
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

    public String getNamaBahan() {
        return this.nama_bahan;
    }

    public double getBk_prs() {
        return this.bk_prs;
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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
