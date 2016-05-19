package com.android.mirzaadr.pakanku;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.android.mirzaadr.pakanku.Internet.WebViewClientImpl;

@SuppressLint("setJavaScriptEnabled")
public class BukaArtikel extends AppCompatActivity {
	// Declare Variables

	String link, judul;
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
		judul = i.getStringExtra("judul");

		this.webView = (WebView) findViewById(R.id.webview);

		if(getSupportActionBar() != null){
		getSupportActionBar().setTitle(judul);}

		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);

		WebViewClientImpl webViewClient = new WebViewClientImpl(this);
		webView.setWebViewClient(webViewClient);

		webView.loadUrl(link);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_resep_ransum, menu);

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		if(id == R.id.action_close_resepransum) {
			finish();
		}

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}