package com.bonesdev.newstoday;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class NewsDetailActivity extends Activity implements OnClickListener{

    WebView webview;
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_detail);
		
		// Set data back.
		Intent intent = this.getIntent();
		NewsEntity news = (NewsEntity)intent.getSerializableExtra("com.bonesdev.newstoday.NewsEntity");
		((TextView)this.findViewById(R.id.news_detail_title)).setText(news.get("title"));
		((TextView)this.findViewById(R.id.news_detail_time)).setText(news.get("time"));
		((TextView)this.findViewById(R.id.news_detail_author)).setText(news.get("author"));

        this.webview = (WebView)this.findViewById(R.id.news_detail_webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadData("\n" +
                "<html><body><h1>It works!</h1>\n" +
                "<p>This is the default web page for this server.</p>\n" +
                "<p>The web server software is running but no content has been added, yet.</p>\n" +
                "</body></html>\n", "text/html", "UTF-8");
		
		Button btnBack = ((Button)findViewById(R.id.news_detail_back));
		btnBack.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		finish();
	}
}
