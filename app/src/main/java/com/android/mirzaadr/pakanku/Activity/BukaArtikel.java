package com.android.mirzaadr.pakanku.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.android.mirzaadr.pakanku.Internet.WebViewClientImpl;
import com.android.mirzaadr.pakanku.R;

public class BukaArtikel extends Activity {
	// Declare Variables

	String link;
	private WebView webView = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_artikel);

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