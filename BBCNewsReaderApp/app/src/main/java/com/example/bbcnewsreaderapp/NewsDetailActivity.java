package com.example.bbcnewsreaderapp;


import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class NewsDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        WebView webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());

        String link = getIntent().getStringExtra("link");
        webView.loadUrl(link);
    }
}
