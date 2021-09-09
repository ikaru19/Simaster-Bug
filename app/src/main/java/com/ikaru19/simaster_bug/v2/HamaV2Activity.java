package com.ikaru19.simaster_bug.v2;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ikaru19.simaster_bug.R;
import com.ikaru19.simaster_bug.adapters.HamaV2Adapter;
import com.ikaru19.simaster_bug.apihelper.ApiService;
import com.ikaru19.simaster_bug.component.LottieLoading;
import com.ikaru19.simaster_bug.generator.ServiceGenerator;
import com.ikaru19.simaster_bug.models.v2.HamaV2;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HamaV2Activity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private ApiService apiService;
    private List<HamaV2> hamas = new ArrayList<>();
    private HamaV2Adapter adapter;
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
        ActionBar actionBar;
        actionBar = getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_hama_v2);
        jenisHama = getIntent().getStringExtra("JenisHama");
        apiService = ServiceGenerator.createService(ApiService.class);
        recyclerView = findViewById(R.id.rv_hama);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshHama);
        noInternetView = findViewById(R.id.hamaNoInternet);
        getData();
        swipeRefreshLayout.setOnRefreshListener(this);


        adapter = new HamaV2Adapter(hamas);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(HamaV2Activity.this, HamaV2DetailActivity.class);
                intent.putExtra("HamaDetail", hamas.get(position));
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(HamaV2Activity.this));
        recyclerView.setAdapter(adapter);
    }

    private void getData(){
        final LottieLoading lottieLoading = new LottieLoading(this);
        lottieLoading.show();
        try{
            Call<List<HamaV2>> hamaCall = null;
            if (jenisHama.equalsIgnoreCase("wijen")){
                hamaCall = apiService.getHamaWijenV2();
            }else if(jenisHama.equalsIgnoreCase("tebu")){
                hamaCall = apiService.getHamaTebuV2();
            }else if(jenisHama.equalsIgnoreCase("tembakau")){
                hamaCall = apiService.getHamaTembakauV2();
            }

            hamaCall.enqueue(new Callback<List<HamaV2>>() {
                @Override
                public void onResponse(Call<List<HamaV2>> call, Response<List<HamaV2>> response) {

                    if (response.code() == 200){
                        noInternetView.setVisibility(View.INVISIBLE);
                        recyclerView.setVisibility(View.VISIBLE);
                        hamas = response.body();
                        adapter.refill(hamas);
                        adapter.notifyDataSetChanged();
                    }else{
                        noInternetView.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.INVISIBLE);
                        Toast.makeText(HamaV2Activity.this, "Terjadi gangguan pada server", Toast.LENGTH_LONG).show();
                    }


                    lottieLoading.dismiss();

                }

                @Override
                public void onFailure(Call<List<HamaV2>> call, Throwable t) {
                    noInternetView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.INVISIBLE);
                    lottieLoading.dismiss();
                    Log.d("SIMASTER_DEBUG",t.getMessage());
                }
            });
        }catch (Exception e){
            Toast.makeText(HamaV2Activity.this,"Terjadi Kesalahan",Toast.LENGTH_LONG).show();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}