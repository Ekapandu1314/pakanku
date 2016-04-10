package com.android.mirzaadr.pakanku.Model;

import java.io.Serializable;

/**
 * Created by Eka Pandu Winata on 4/2/2016.
 */
public class Version implements Serializable {

    private static final String TAG = "version";
    private static final long serialVersionUID = -7406082437623008161L;

    private String versi_bahan;
    private String tanggal;

    public Version() {

    }

    public Version(String versi_bahan, String tanggal )
    {
        this.versi_bahan = versi_bahan;
        this.tanggal = tanggal;

    }

    public void setVersiBahan(String versi_bahan)
    {
        this.versi_bahan = versi_bahan;
    }

    public void setTanggal (String tanggal)
    {
        this.tanggal = tanggal;
    }


    public String getVersiBahan()
    {
        return this.versi_bahan;
    }

    public String getTanggal()
    {
        return this.tanggal;
    }


}
