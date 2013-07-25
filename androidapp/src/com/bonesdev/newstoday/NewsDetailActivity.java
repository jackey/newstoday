package com.bonesdev.newstoday;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

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
        Log.i("TEASER", news.get("teaser"));
        this.webview = (WebView)this.findViewById(R.id.news_detail_webview);
        webview.getSettings().setJavaScriptEnabled(true);

        webview.loadDataWithBaseURL("", "\n" +
                "<html><meta charset='utf8' /><body>\n" +
                "<p>"+news.get("teaser")+"</p>\n" +
                "</body></html>\n", "text/html", "UTF-8", "");
		
		Button btnBack = ((Button)findViewById(R.id.news_detail_back));
		btnBack.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		finish();
	}
}
