package com.ikaru19.simaster_bug.apihelper;

import com.ikaru19.simaster_bug.models.Bts;
import com.ikaru19.simaster_bug.models.Contact;
import com.ikaru19.simaster_bug.models.Hama;
import com.ikaru19.simaster_bug.models.VideoResponse;
import com.ikaru19.simaster_bug.models.v2.ArtikelV2;
import com.ikaru19.simaster_bug.models.v2.HamaV2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("api/v2/artikel")
    Call<List<ArtikelV2>> getArtikel();

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

    // MARK: V2
    @GET("api/v2/Hama?kategori=wijen")
    Call<List<HamaV2>> getHamaWijenV2();

    @GET("api/v2/Hama?kategori=tebu")
    Call<List<HamaV2>> getHamaTebuV2();

    @GET("api/v2/Hama?kategori=tembakau")
    Call<List<HamaV2>> getHamaTembakauV2();

    @GET("api/v2/vidio")
    Call<List<VideoResponse>> getVideo();
}
