package com.android.mirzaadr.pakanku.Model;

import java.io.Serializable;

/**
 * Created by Eka Pandu Winata on 4/9/2016.
 */
public class Resep implements Serializable {

    private static final String TAG = "resep";
    private static final long serialVersionUID = -7406082437623008161L;

    private int idresep;
    private String nama_bahan;
    private String harga_kg;
    private String proporsi;
    private String harga_bahan;
    private String jenis_bahan;

    public Resep(int idresep, String nama_bahan, String harga_kg, String proporsi, String harga_bahan, String jenis_bahan )
    {
        this.idresep = idresep;
        this.nama_bahan = nama_bahan;
        this.harga_kg = harga_kg;
        this.proporsi = proporsi;
        this.harga_bahan = harga_bahan;
        this.jenis_bahan = jenis_bahan;

    }

    public void setIdresep(int idresep)
    {
        this.idresep = idresep;
    }

    public void setNama_bahan(String nama_bahan)
    {
        this.nama_bahan = nama_bahan;
    }

    public void setHarga_kg(String harga_kg)
    {
        this.harga_kg = harga_kg;
    }

    public void setProporsi (String proporsi)
    {
        this.proporsi = proporsi;
    }

    public void setHarga_bahan (String harga_bahan)
    {
        this.harga_bahan = harga_bahan;
    }

    public void setJenis_bahan (String jenis_bahan)
    {
        this.jenis_bahan = jenis_bahan;
    }

    public int getIdresep()
    {
        return this.idresep;
    }

    public String getNama_bahan()
    {
        return this.nama_bahan;
    }

    public String getHarga_kg()
    {
        return this.harga_kg;
    }

    public String getHarga_bahan()
    {
        return this.harga_bahan;
    }

    public String getJenis_bahan()
    {
        return this.jenis_bahan;
    }

    public String getProporsi()
    {
        return this.proporsi;
    }

}
