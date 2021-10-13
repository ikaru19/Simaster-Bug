package com.ikaru19.simaster_bug;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ikaru19.simaster_bug.adapters.ContactAdapter;
import com.ikaru19.simaster_bug.apihelper.ApiService;
import com.ikaru19.simaster_bug.component.LottieLoading;
import com.ikaru19.simaster_bug.generator.ServiceGenerator;
import com.ikaru19.simaster_bug.models.Contact;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private ApiService apiService;
    private ServiceGenerator serviceGenerator;
    RecyclerView recyclerView;
    ContactAdapter adapter;
    List<Contact> contacts = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
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
        setContentView(R.layout.activity_contact_list);
        recyclerView = findViewById(R.id.rv_contacts);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshContact);
        noInternetView = findViewById(R.id.contactNoInternet);
        apiService = ServiceGenerator.createService(ApiService.class);
        getData();
        adapter = new ContactAdapter(contacts, this);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String url = "https://wa.me/"+contacts.get(position).getNoTelp()+"?text=Saya%20dari%20aplikasi%20Simaster%20ingin%20bertanya%20kepada%20anda";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void getData(){
        final LottieLoading lottieLoading = new LottieLoading(this);
        lottieLoading.show();
        Call<List<Contact>> contactCall = apiService.getContact();
        contactCall.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                noInternetView.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                lottieLoading.dismiss();
                contacts = response.body();
                if (contacts.isEmpty() || contacts == null){
                    Toast.makeText(ContactListActivity.this , "Data Kosong",Toast.LENGTH_SHORT).show();
                }else{
                    adapter.refill(contacts);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                noInternetView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.INVISIBLE);
                lottieLoading.dismiss();
            }
        });
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
