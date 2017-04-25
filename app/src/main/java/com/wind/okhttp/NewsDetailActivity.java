package com.wind.okhttp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

/**
 * Created by zhangcong on 2017/4/25.
 */

public class NewsDetailActivity extends Activity{
    private WebView webView;
    private String url;
    private TextView back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        webView= (WebView) findViewById(R.id.wb_new);
        back= (TextView) findViewById(R.id.tv_back);
        Intent intent=getIntent();
        url=intent.getStringExtra("url");
        initWebView();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initWebView() {
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;//返回为true默认webview自带浏览器打开，false调用三方浏览器
            }
        });
    }
}
