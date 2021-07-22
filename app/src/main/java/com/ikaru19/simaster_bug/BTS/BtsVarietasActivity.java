package com.ikaru19.simaster_bug.BTS;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ikaru19.simaster_bug.R;
import com.ikaru19.simaster_bug.adapters.BtsAdapter;
import com.ikaru19.simaster_bug.apihelper.ApiService;
import com.ikaru19.simaster_bug.component.LottieLoading;
import com.ikaru19.simaster_bug.generator.ServiceGenerator;
import com.ikaru19.simaster_bug.models.Bts;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BtsVarietasActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private List<Bts> btsData = new ArrayList<>();
    private BtsAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ApiService apiService;
    private View noInternetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bts_varietas);
        apiService = ServiceGenerator.createService(ApiService.class);
        recyclerView = findViewById(R.id.rv_BTS);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshBTS);
        swipeRefreshLayout.setOnRefreshListener(this);
        noInternetView = findViewById(R.id.btsNoInternet);
        getData();
        adapter = new BtsAdapter(btsData);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(BtsVarietasActivity.this, BtsDetailActivity.class);
                intent.putExtra("BtsDetail", btsData.get(position));
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(BtsVarietasActivity.this));
        recyclerView.setAdapter(adapter);
    }

    private void getData(){
        final LottieLoading lottieLoading = new LottieLoading(this);
        lottieLoading.show();
        try{
            Call<List<Bts>> btsCall = null;
            btsCall = apiService.getBts();
            btsCall.enqueue(new Callback<List<Bts>>() {
                @Override
                public void onResponse(Call<List<Bts>> call, Response<List<Bts>> response) {
                    if (response.code() == 200){
                        btsData = response.body();


                        adapter.refill(btsData);
                        adapter.notifyDataSetChanged();
                    }else{

                        Toast.makeText(BtsVarietasActivity.this, "Terjadi gangguan pada server", Toast.LENGTH_LONG).show();

                    }
                    lottieLoading.dismiss();
                }

                @Override
                public void onFailure(Call<List<Bts>> call, Throwable t) {
                    noInternetView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.INVISIBLE);
                    lottieLoading.dismiss();
                    Log.d("SIMASTER_DEBUG",t.getMessage());
                }
            });
        }catch (Exception e){
            Toast.makeText(BtsVarietasActivity.this,"Terjadi Kesalahan",Toast.LENGTH_LONG).show();
            Log.d("SIMASTER DEBUG",e.getMessage());
        }

    }

    @Override
    public void onRefresh() {
        Toast.makeText(this, "Memuat Ulang...", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
}