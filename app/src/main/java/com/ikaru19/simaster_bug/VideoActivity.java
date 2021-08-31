package com.ikaru19.simaster_bug;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ikaru19.simaster_bug.adapters.VideoAdapter;
import com.ikaru19.simaster_bug.apihelper.ApiService;
import com.ikaru19.simaster_bug.component.LottieLoading;
import com.ikaru19.simaster_bug.generator.ServiceGenerator;
import com.ikaru19.simaster_bug.models.VideoResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private ApiService apiService;
    private List<VideoResponse> artikels = new ArrayList<>();
    private VideoAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View noInternetView;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        apiService = ServiceGenerator.createService(ApiService.class);
        recyclerView = findViewById(R.id.rv_video);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        noInternetView = findViewById(R.id.artikelNoInternet);
        getData();
        swipeRefreshLayout.setOnRefreshListener(this);
        adapter = new VideoAdapter(artikels);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                 String url = artikels.get(position).getUri();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                Log.d("SIMASTER_DEBUG","CLICKED");
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(VideoActivity.this));
        recyclerView.setAdapter(adapter);
    }

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

    private void getData() {
        final LottieLoading lottieLoading = new LottieLoading(VideoActivity.this);
        lottieLoading.show();
        Call<List<VideoResponse>> videoCall = apiService.getVideo();
        videoCall.enqueue(new Callback<List<VideoResponse>>() {
            @Override
            public void onResponse(Call<List<VideoResponse>> call, Response<List<VideoResponse>> response) {
                noInternetView.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                artikels = response.body();

                if (artikels == null || artikels.isEmpty() ){
                    Toast.makeText(VideoActivity.this,"Data Kosong",Toast.LENGTH_SHORT).show();
                }else{
                    adapter.refill(artikels);
                    adapter.notifyDataSetChanged();
                }
                lottieLoading.dismiss();
            }

            @Override
            public void onFailure(Call<List<VideoResponse>> call, Throwable t) {
                noInternetView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.INVISIBLE);
                lottieLoading.dismiss();
                Log.d("SIMASTER_DEBUG",t.getMessage());
            }

            {
        }
        });
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