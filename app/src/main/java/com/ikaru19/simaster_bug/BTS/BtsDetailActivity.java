package com.ikaru19.simaster_bug.BTS;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ikaru19.simaster_bug.R;
import com.ikaru19.simaster_bug.component.LottieLoading;
import com.ikaru19.simaster_bug.models.Bts;
import com.ikaru19.simaster_bug.Constant;

public class BtsDetailActivity extends AppCompatActivity {

    private Bts btsData;
    private TextView tv_judul_detail, tv_penulis_detail;
    private WebView bts_webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bts_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("  ");

        btsData = getIntent().getParcelableExtra("BtsDetail");
        tv_penulis_detail = findViewById(R.id.tv_penulis_bts_detail);
        tv_judul_detail = findViewById(R.id.tv_judul_bts_detail);
        bts_webview = findViewById(R.id.webview_bts);
        updateUI();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void updateUI() {
        final LottieLoading lottieLoading = new LottieLoading(BtsDetailActivity.this);
        lottieLoading.show();
        tv_judul_detail.setText(btsData.getJudul());
        tv_penulis_detail.setText("oleh : " + btsData.getPenulis());
        bts_webview.getSettings().setJavaScriptEnabled(true);
        String html = generateHtml();
        bts_webview.loadDataWithBaseURL(null, html, Constant.HTML_MIME_TYPE, Constant.ENCODING_UTF_8, null);
        bts_webview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                    startActivity(intent);
                    return false;
                } else if (url.startsWith("http:") || url.startsWith("https:")) {
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

    private String generateHtml() {
        String htmlData = "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">";
        htmlData = htmlData + "<style>img{display: inline;height: auto;max-width: 100%;}</style>";
        htmlData = htmlData + btsData.getKonten();
        return htmlData;
    }
}