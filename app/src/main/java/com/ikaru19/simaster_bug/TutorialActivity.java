package com.ikaru19.simaster_bug;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.ikaru19.simaster_bug.apihelper.ApiService;
import com.ikaru19.simaster_bug.component.LottieLoading;
import com.ikaru19.simaster_bug.generator.ServiceGenerator;
import com.ikaru19.simaster_bug.models.v2.ArtikelV2;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TutorialActivity extends AppCompatActivity {

    private List<ArtikelV2> rawArtikels = new ArrayList<>();
    private ApiService apiService;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar;
        actionBar = getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_tutorial);
        webView = findViewById(R.id.wvTutorial);
        apiService = ServiceGenerator.createService(ApiService.class);
        getData();
    }

    private void getData(){
        final LottieLoading lottieLoading = new LottieLoading(TutorialActivity.this);
        lottieLoading.show();
        Call<List<ArtikelV2>> artikelCall = apiService.getTutorial();
        artikelCall.enqueue(new Callback<List<ArtikelV2>>() {
            @Override
            public void onResponse(Call<List<ArtikelV2>> call, Response<List<ArtikelV2>> response) {
                rawArtikels = response.body();

                if (rawArtikels == null || rawArtikels.isEmpty() ){
                    Toast.makeText(TutorialActivity.this,"Tidak Ada Data",Toast.LENGTH_SHORT).show();
                }else{
                    getWeb(rawArtikels.get(0));
                }
                lottieLoading.dismiss();
            }

            @Override
            public void onFailure(Call<List<ArtikelV2>> call, Throwable t) {
                lottieLoading.dismiss();
                Log.d("SIMASTER_DEBUG",t.getMessage());
            }
        });
    }

    private void getWeb(ArtikelV2 data) {
        final LottieLoading lottieLoading = new LottieLoading(TutorialActivity.this);
        webView.getSettings().setJavaScriptEnabled(true);
        String html = generateHtml(data);
        webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL,
                            Uri.parse(url));
                    startActivity(intent);
                    return false;
                }
                else if(url.startsWith("http:") || url.startsWith("https:")) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                    return false;
                }

                return true;
            }

            public void onPageFinished(WebView view, String url) {
                // your code
                webView.loadUrl("javascript:document.body.style.margin=\"4%\"; void 0");
                lottieLoading.dismiss();
            }
        });
    }

    private String generateHtml(ArtikelV2 data) {
        String htmlData = "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">";
        htmlData = htmlData + "<style>img{display: inline;height: auto;max-width: 100%;}</style>";
        htmlData = htmlData + data.getKonten();
        return htmlData;
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