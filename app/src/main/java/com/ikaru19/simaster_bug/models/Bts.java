package com.ikaru19.simaster_bug.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bts {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("konten")
    @Expose
    private String konten;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("judul")
    @Expose
    private String judul;
    @SerializedName("penulis")
    @Expose
    private String penulis;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

}