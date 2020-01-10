package com.ikaru19.simaster_bug.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hama implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nama_hama")
    @Expose
    private String namaHama;
    @SerializedName("deskipsi")
    @Expose
    private String deskipsi;
    @SerializedName("penyelesaian_hama")
    @Expose
    private String penyelesaianHama;
    @SerializedName("rekom_pesti")
    @Expose
    private String rekomPesti;
    @SerializedName("gejala")
    @Expose
    private String gejala;
    @SerializedName("kategori")
    @Expose
    private String kategori;
    @SerializedName("img")
    @Expose
    private String img;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaHama() {
        return namaHama;
    }

    public void setNamaHama(String namaHama) {
        this.namaHama = namaHama;
    }

    public String getDeskipsi() {
        return deskipsi;
    }

    public void setDeskipsi(String deskipsi) {
        this.deskipsi = deskipsi;
    }

    public String getPenyelesaianHama() {
        return penyelesaianHama;
    }

    public void setPenyelesaianHama(String penyelesaianHama) {
        this.penyelesaianHama = penyelesaianHama;
    }

    public String getRekomPesti() {
        return rekomPesti;
    }

    public void setRekomPesti(String rekomPesti) {
        this.rekomPesti = rekomPesti;
    }

    public String getGejala() {
        return gejala;
    }

    public void setGejala(String gejala) {
        this.gejala = gejala;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.namaHama);
        dest.writeString(this.deskipsi);
        dest.writeString(this.penyelesaianHama);
        dest.writeString(this.rekomPesti);
        dest.writeString(this.gejala);
        dest.writeString(this.kategori);
        dest.writeString(this.img);
    }

    public Hama() {
    }

    protected Hama(Parcel in) {
        this.id = in.readString();
        this.namaHama = in.readString();
        this.deskipsi = in.readString();
        this.penyelesaianHama = in.readString();
        this.rekomPesti = in.readString();
        this.gejala = in.readString();
        this.kategori = in.readString();
        this.img = in.readString();
    }

    public static final Parcelable.Creator<Hama> CREATOR = new Parcelable.Creator<Hama>() {
        @Override
        public Hama createFromParcel(Parcel source) {
            return new Hama(source);
        }

        @Override
        public Hama[] newArray(int size) {
            return new Hama[size];
        }
    };
}
