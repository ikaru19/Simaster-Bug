package com.ikaru19.simaster_bug.BTS;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ikaru19.simaster_bug.R;
import com.ikaru19.simaster_bug.adapters.BtsAdapter;
import com.ikaru19.simaster_bug.apihelper.ApiService;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bts_varietas);
        apiService = ServiceGenerator.createService(ApiService.class);
        recyclerView = findViewById(R.id.rv_BTS);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshBTS);
        swipeRefreshLayout.setOnRefreshListener(this);
        getData();
        adapter = new BtsAdapter(btsData);
        recyclerView.setLayoutManager(new LinearLayoutManager(BtsVarietasActivity.this));
        recyclerView.setAdapter(adapter);
    }

    private void getData(){
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Mengambil Data Dari Internet");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
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
                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<List<Bts>> call, Throwable t) {
                    Toast.makeText(BtsVarietasActivity.this, "Mohon Hubungkan Ponsel Anda Ke Internet", Toast.LENGTH_LONG).show();
                    progress.dismiss();
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

    }
}