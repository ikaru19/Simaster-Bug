package com.ikaru19.simaster_bug.apihelper;

import com.ikaru19.simaster_bug.models.Artikel;
import com.ikaru19.simaster_bug.models.Bts;
import com.ikaru19.simaster_bug.models.Contact;
import com.ikaru19.simaster_bug.models.Hama;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("api/Artikel")
    Call<List<Artikel>> getArtikel();

    @GET("api/Hama?kategori=wijen")
    Call<List<Hama>> getHamaWijen();

    @GET("api/Hama?kategori=tebu")
    Call<List<Hama>> getHamaTebu();

    @GET("api/Hama?kategori=tembakau")
    Call<List<Hama>> getHamaTembakau();

    @GET("api/kontak")
    Call<List<Contact>> getContact();

    @GET("api/bts")
    Call<List<Bts>> getBts();

    @GET("api/bts?tipe=varietas")
    Call<List<Bts>> getBtsVarietas();

    @GET("api/bts?tipe=stok")
    Call<List<Bts>> getBtsStok();

    @GET("api/bts?tipe=hama_penyakit")
    Call<List<Bts>> getBtsHamaPenyakit();

    @GET("api/bts?tipe=budidaya")
    Call<List<Bts>> getBtsBudidaya();
}
