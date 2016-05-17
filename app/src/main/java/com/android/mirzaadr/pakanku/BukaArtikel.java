package com.android.mirzaadr.pakanku;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.android.mirzaadr.pakanku.Internet.WebViewClientImpl;

@SuppressLint("setJavaScriptEnabled")
public class BukaArtikel extends AppCompatActivity {
	// Declare Variables

	String link;
	public WebView webView = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_artikel);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_informasi);
		setSupportActionBar(toolbar);

		Intent i = getIntent();
		// Get the result of link
		link = i.getStringExtra("link");

		this.webView = (WebView) findViewById(R.id.webview);

		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);

		WebViewClientImpl webViewClient = new WebViewClientImpl(this);
		webView.setWebViewClient(webViewClient);

		webView.loadUrl(link);
	}
}