package com.ikaru19.simaster_bug.apihelper;

import com.ikaru19.simaster_bug.models.Artikel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("api/Artikel")
    Call<List<Artikel>> getArtikel();

}
