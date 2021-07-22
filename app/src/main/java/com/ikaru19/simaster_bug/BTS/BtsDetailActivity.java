package com.ikaru19.simaster_bug.BTS;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ikaru19.simaster_bug.R;
import com.ikaru19.simaster_bug.component.LottieLoading;
import com.ikaru19.simaster_bug.models.Bts;

public class BtsDetailActivity extends AppCompatActivity {

    private Bts btsData;
    private TextView tv_judul_detail , tv_penulis_detail;
    private WebView bts_webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bts_detail);
        btsData = getIntent().getParcelableExtra("BtsDetail");
        tv_penulis_detail = findViewById(R.id.tv_penulis_bts_detail);
        tv_judul_detail = findViewById(R.id.tv_judul_bts_detail);
        bts_webview = findViewById(R.id.webview_bts);
        updateUI();
    }

    private void updateUI() {
        final LottieLoading lottieLoading = new LottieLoading(BtsDetailActivity.this);
        lottieLoading.show();
        tv_judul_detail.setText(btsData.getJudul());
        tv_penulis_detail.setText("oleh : " + btsData.getPenulis());
        bts_webview.getSettings().setJavaScriptEnabled(true);
        String htmlData = "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">";
        htmlData = htmlData + btsData.getKonten() + btsData.getKonten() + btsData.getKonten() + btsData.getKonten();
        bts_webview.loadData(htmlData, "text/html; charset=utf-8", "UTF-8");
        bts_webview.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                // your code
                lottieLoading.dismiss();
            }
        });

    }
}