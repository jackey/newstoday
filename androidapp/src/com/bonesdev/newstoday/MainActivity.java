package com.bonesdev.newstoday;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	final Context context = this;
	Bundle savedInstanceState;
	ListView listview;
	StableArrayAdapter adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Bundle savedInstanceStateLocal = savedInstanceState;
		this.setContentView(R.layout.activiy_main);
		
		listview = (ListView)findViewById(R.id.mainListView);
		
		NewsEntity[] newss = {new NewsEntity("Today news", "Jziwenchen", "2013-07-15", "Today, we are in Shanghai to learn android")};
		final ArrayList<NewsEntity> list = new ArrayList<NewsEntity>();
		for (NewsEntity news : newss) {
			list.add(news);
		}
		
		adapter = new StableArrayAdapter(this, R.layout.main_list_item, list);
		listview.setAdapter(adapter);
		
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				final NewsEntity news = (NewsEntity)parent.getItemAtPosition(position);
				Intent intent = new Intent(context, NewsDetailActivity.class);
				intent.putExtra("com.bonesdev.newstoday.NewsEntity", news);
				context.startActivity(intent, savedInstanceStateLocal);
			}
		});
	}
}

class StableArrayAdapter extends ArrayAdapter<NewsEntity> {
	private final Context context;
	HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
	public StableArrayAdapter(Context context, int textviewResourceId, List<NewsEntity> objects) {
		super(context, textviewResourceId, objects);
		this.context = context;
		for (int i = 0; i < objects.size(); i++) {
			hashMap.put(objects.get(i).getUUID(), i);
		}
	}
	
	@Override
	public long getItemId(int position) {
		NewsEntity item = this.getItem(position);
		return hashMap.get(item.getUUID());
	}
	
	@Override
	public boolean hasStableIds() {
		return true;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowview = inflater.inflate(R.layout.main_list_item, parent, false);
		
		// Set data to single news view.
		NewsEntity news = this.getItem(position);
		((TextView)rowview.findViewById(R.id.main_list_item_title)).setText(news.get("title"));
		((TextView)rowview.findViewById(R.id.main_list_item_teaser)).setText(news.get("teaser"));
		((TextView)rowview.findViewById(R.id.main_list_item_time)).setText(news.get("time"));
		((TextView)rowview.findViewById(R.id.main_list_item_author)).setText(news.get("author"));
		return rowview;
	}
}

