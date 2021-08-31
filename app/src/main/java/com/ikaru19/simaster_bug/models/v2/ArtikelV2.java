package com.ikaru19.simaster_bug.models.v2;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ArtikelV2 implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("judul")
    @Expose
    private String judul;
    @SerializedName("konten")
    @Expose
    private String konten;
    @SerializedName("penulis")
    @Expose
    private String penulis;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    public final static Creator<ArtikelV2> CREATOR = new Creator<ArtikelV2>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ArtikelV2 createFromParcel(android.os.Parcel in) {
            return new ArtikelV2(in);
        }

        public ArtikelV2 [] newArray(int size) {
            return (new ArtikelV2[size]);
        }

    }
            ;
    private final static long serialVersionUID = -1879534545220804449L;

    protected ArtikelV2(android.os.Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.thumbnail = ((String) in.readValue((String.class.getClassLoader())));
        this.judul = ((String) in.readValue((String.class.getClassLoader())));
        this.konten = ((String) in.readValue((String.class.getClassLoader())));
        this.penulis = ((String) in.readValue((String.class.getClassLoader())));
        this.dateCreated = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ArtikelV2() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(thumbnail);
        dest.writeValue(judul);
        dest.writeValue(konten);
        dest.writeValue(penulis);
        dest.writeValue(dateCreated);
    }

    public int describeContents() {
        return 0;
    }

}