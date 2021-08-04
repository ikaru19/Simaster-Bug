package com.ikaru19.simaster_bug.v2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.ikaru19.simaster_bug.R;
import com.ikaru19.simaster_bug.component.LottieLoading;
import com.ikaru19.simaster_bug.models.v2.HamaV2;
import com.squareup.picasso.Picasso;

import static com.ikaru19.simaster_bug.Constant.BASE_URL_IMG;

public class HamaV2DetailActivity extends AppCompatActivity {

    private HamaV2 hama;
    private ImageView iv_hama_detail;
    private TextView tv_judul;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hama_v2_detail);
        hama = getIntent().getParcelableExtra("HamaDetail");
//        tv_judul =  findViewById(R.id.tv_v2_hama_judul);
        iv_hama_detail = findViewById(R.id.iv_hama_v2_detail);
        webView = findViewById(R.id.webview_hama);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setTitle(hama.getJudul());

        collapsingToolbarLayout.setCollapsedTitleTextColor(
                ContextCompat.getColor(this, R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(
                ContextCompat.getColor(this, R.color.white));
        updateUI();
    }

    private void updateUI() {
        final LottieLoading lottieLoading = new LottieLoading(HamaV2DetailActivity.this);
        lottieLoading.show();
        Picasso.get()
                .load(BASE_URL_IMG+hama.getThumbnail())
                .resize(1280, 720)
                .placeholder(R.drawable.img_placeholder)
                .onlyScaleDown().into(iv_hama_detail);
        webView.getSettings().setJavaScriptEnabled(true);
        String htmlData = "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">";
        htmlData = htmlData + hama.getKonten();
        webView.loadDataWithBaseURL(null, htmlData, "text/html", "utf-8", null);
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
                lottieLoading.dismiss();
            }
        });
    }
}