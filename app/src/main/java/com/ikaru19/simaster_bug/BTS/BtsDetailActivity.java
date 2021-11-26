package com.ikaru19.simaster_bug.BTS;

import static com.ikaru19.simaster_bug.Constant.BASE_URL_IMG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.ikaru19.simaster_bug.R;
import com.ikaru19.simaster_bug.component.LottieLoading;
import com.ikaru19.simaster_bug.models.Bts;
import com.squareup.picasso.Picasso;

public class BtsDetailActivity extends AppCompatActivity {

    private Bts btsData;
    private ImageView iv_hama_detail;
    private WebView bts_webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bts_detail);
        btsData = getIntent().getParcelableExtra("BtsDetail");
        iv_hama_detail = findViewById(R.id.iv_hama_v2_detail);
        bts_webview = findViewById(R.id.webview_hama);
        setupToolBar();
        updateUI();
    }

    private void setupToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setTitle("  ");
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });
    }

    private void updateUI() {
        final LottieLoading lottieLoading = new LottieLoading(BtsDetailActivity.this);
        lottieLoading.show();
        Picasso.get()
                .load(BASE_URL_IMG+btsData.getThumbnail())
                .resize(1280, 720)
                .placeholder(R.drawable.img_placeholder)
                .onlyScaleDown().into(iv_hama_detail);
        bts_webview.getSettings().setJavaScriptEnabled(true);
        String html = generateHtml();
        bts_webview.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
        bts_webview.setWebViewClient(new WebViewClient() {
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
                bts_webview.loadUrl("javascript:document.body.style.margin=\"4%\"; void 0");
                lottieLoading.dismiss();
            }
        });
    }

    private String generateHtml() {
        String htmlData = "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">";
        htmlData = htmlData + "<h2>" + btsData.getJudul() + "</h2> <br>";
        htmlData = htmlData + "<p> oleh: " + btsData.getPenulis() + "</p> <br> <br>";
        htmlData = htmlData + "<style>img{display: inline;height: auto;max-width: 100%;}</style>";
        htmlData = htmlData + btsData.getKonten();
        return htmlData;
    }
}