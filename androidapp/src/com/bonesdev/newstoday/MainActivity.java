package com.bonesdev.newstoday;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	ListView listview;
	ArrayAdapter adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activiy_main);
		
		listview = (ListView)findViewById(R.id.mainListView);
		
		String[] values = new String[]{"Android", "iphone", "Windows mobile", "WebOS"};
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i < values.length; i++) {
			list.add(values[i]);
		}
		
		adapter = new ArrayAdapter(this, R.layout.list_main_item, list);
		listview.setAdapter(adapter);
	}
}