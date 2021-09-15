package com.ikaru19.simaster_bug.models.v2;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HamaV2 implements Parcelable
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
    @SerializedName("kategori")
    @Expose
    private String kategori;
    @SerializedName("oleh")
    @Expose
    private String oleh;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    public final static Creator<HamaV2> CREATOR = new Creator<HamaV2>() {


        @SuppressWarnings({
                "unchecked"
        })
        public HamaV2 createFromParcel(android.os.Parcel in) {
            return new HamaV2(in);
        }

        public HamaV2 [] newArray(int size) {
            return (new HamaV2[size]);
        }

    }
            ;

    protected HamaV2(android.os.Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.thumbnail = ((String) in.readValue((String.class.getClassLoader())));
        this.judul = ((String) in.readValue((String.class.getClassLoader())));
        this.konten = ((String) in.readValue((String.class.getClassLoader())));
        this.kategori = ((String) in.readValue((String.class.getClassLoader())));
        this.dateCreated = ((String) in.readValue((String.class.getClassLoader())));
        this.oleh = ((String) in.readValue((String.class.getClassLoader())));
    }

    public HamaV2() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOleh() {
        return oleh;
    }

    public void setOleh(String id) {
        this.oleh = id;
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

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
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
        dest.writeValue(kategori);
        dest.writeValue(dateCreated);
        dest.writeValue(oleh);
    }

    public int describeContents() {
        return 0;
    }

}