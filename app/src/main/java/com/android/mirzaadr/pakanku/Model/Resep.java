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
    private String proporsi;
    private String harga_bahan;

    public Resep() {

    }

    public Resep(int idresep, String nama_bahan, String proporsi, String harga_bahan )
    {
        this.idresep = idresep;
        this.nama_bahan = nama_bahan;
        this.proporsi = proporsi;
        this.harga_bahan = harga_bahan;

    }

    public void setIdresep(int idresep)
    {
        this.idresep = idresep;
    }

    public void setNama_bahan(String nama_bahan)
    {
        this.nama_bahan = nama_bahan;
    }

    public void setProporsi (String proporsi)
    {
        this.proporsi = proporsi;
    }

    public void setHarga_bahan (String harga_bahan)
    {
        this.harga_bahan = harga_bahan;
    }

    public int getIdresep()
    {
        return this.idresep;
    }

    public String getNama_bahan()
    {
        return this.nama_bahan;
    }

    public String getHarga_bahan()
    {
        return this.harga_bahan;
    }

    public String getProporsi()
    {
        return this.proporsi;
    }

}
