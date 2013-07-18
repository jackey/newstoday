package com.bonesdev.newstoday;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class NewsDetailActivity extends Activity implements OnClickListener{
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.overridePendingTransition(R.anim.swapin, R.anim.swapout);
		setContentView(R.layout.news_detail);
		
		// Set data back.
		Intent intent = this.getIntent();
		NewsEntity news = (NewsEntity)intent.getSerializableExtra("com.bonesdev.newstoday.NewsEntity");
		((TextView)this.findViewById(R.id.news_detail_title)).setText(news.get("title"));
		((TextView)this.findViewById(R.id.news_detail_teaser)).setText(news.get("teaser"));
		((TextView)this.findViewById(R.id.news_detail_time)).setText(news.get("time"));
		((TextView)this.findViewById(R.id.news_detail_author)).setText(news.get("author"));
		
		Button btnBack = ((Button)findViewById(R.id.news_detail_back));
		btnBack.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		finish();
	}
}
