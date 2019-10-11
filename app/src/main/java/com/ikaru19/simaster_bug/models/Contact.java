package com.ikaru19.simaster_bug.models;

public class Contact {
    String nama;
    String jabatan;
    String no_telp;
    String gambar;


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public Contact(String nama, String jabatan, String no_telp, String gambar) {
        this.nama = nama;
        this.jabatan = jabatan;
        this.no_telp = no_telp;
        this.gambar = gambar;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
