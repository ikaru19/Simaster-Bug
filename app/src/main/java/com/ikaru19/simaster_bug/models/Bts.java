package com.ikaru19.simaster_bug.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bts implements Parcelable {

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
    public final static Creator<Bts> CREATOR = new Creator<Bts>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Bts createFromParcel(android.os.Parcel in) {
            return new Bts(in);
        }

        public Bts[] newArray(int size) {
            return (new Bts[size]);
        }

    }
            ;

    protected Bts(android.os.Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.konten = ((String) in.readValue((String.class.getClassLoader())));
        this.dateCreated = ((String) in.readValue((String.class.getClassLoader())));
        this.judul = ((String) in.readValue((String.class.getClassLoader())));
        this.penulis = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Bts() {
    }

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

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(konten);
        dest.writeValue(dateCreated);
        dest.writeValue(judul);
        dest.writeValue(penulis);
    }

    public int describeContents() {
        return 0;
    }

}