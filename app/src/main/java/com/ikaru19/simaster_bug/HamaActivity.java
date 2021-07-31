package com.ikaru19.simaster_bug;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ikaru19.simaster_bug.adapters.HamaAdapter;
import com.ikaru19.simaster_bug.apihelper.ApiService;
import com.ikaru19.simaster_bug.component.LottieLoading;
import com.ikaru19.simaster_bug.generator.ServiceGenerator;
import com.ikaru19.simaster_bug.models.Hama;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HamaActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private ApiService apiService;
    private List<Hama> hamas = new ArrayList<>();
    private HamaAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String jenisHama;
    private View noInternetView;
    Handler handler = new Handler();

    @Override
    public void onBackPressed() {
        handler.removeCallbacksAndMessages(null);
        super.onBackPressed();
        Animatoo.animateSlideRight(this);
    }

    @Override
    protected void onStop() {
        handler.removeCallbacksAndMessages(null);
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hama);
        jenisHama = getIntent().getStringExtra("JenisHama");
        apiService = ServiceGenerator.createService(ApiService.class);
        recyclerView = findViewById(R.id.rv_hama);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshHama);
        noInternetView = findViewById(R.id.hamaNoInternet);
        getData();
        swipeRefreshLayout.setOnRefreshListener(this);


        adapter = new HamaAdapter(hamas);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(HamaActivity.this,HamaDetailActivity.class);
                intent.putExtra("HamaDetail", hamas.get(position));
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
    }

    private void getData(){
        final LottieLoading lottieLoading = new LottieLoading(this);
        lottieLoading.show();
        try{
            Call<List<Hama>> hamaCall = null;
            if (jenisHama.equalsIgnoreCase("wijen")){
                hamaCall = apiService.getHamaWijen();
            }else if(jenisHama.equalsIgnoreCase("tebu")){
                hamaCall = apiService.getHamaTebu();
            }else if(jenisHama.equalsIgnoreCase("tembakau")){
                hamaCall = apiService.getHamaTembakau();
            }

            hamaCall.enqueue(new Callback<List<Hama>>() {
                @Override
                public void onResponse(Call<List<Hama>> call, Response<List<Hama>> response) {

                    if (response.code() == 200){
                        noInternetView.setVisibility(View.INVISIBLE);
                        recyclerView.setVisibility(View.VISIBLE);
                        hamas = response.body();
                        adapter.refill(hamas);
                        adapter.notifyDataSetChanged();
                    }else{
                        noInternetView.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.INVISIBLE);
                        Toast.makeText(HamaActivity.this, "Terjadi gangguan pada server", Toast.LENGTH_LONG).show();
                    }


                    lottieLoading.dismiss();

                }

                @Override
                public void onFailure(Call<List<Hama>> call, Throwable t) {
                    noInternetView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.INVISIBLE);
                    lottieLoading.dismiss();
                    Log.d("SIMASTER_DEBUG",t.getMessage());
                }
            });
        }catch (Exception e){
            Toast.makeText(HamaActivity.this,"Terjadi Kesalahan",Toast.LENGTH_LONG).show();
            Log.d("SIMASTER DEBUG",e.getMessage());
        }

    }

    @Override
    public void onRefresh() {
        Toast.makeText(this, "Memuat Ulang...", Toast.LENGTH_SHORT).show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
}
