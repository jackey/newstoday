package com.bonesdev.newstoday;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity {
	
	final Context context = this;
	Bundle savedInstanceState;
	ListView listview;
	StableArrayAdapter adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Bundle savedInstanceStateLocal = savedInstanceState;
		this.setContentView(R.layout.main);
		
		listview = (ListView)findViewById(R.id.mainListView);
		
		NewsEntity[] newss = {new NewsEntity("Today news news", "Jziwenchen", "2013-07-15", "Today, we are in Shanghai to learn android")
            , new NewsEntity("JIang Zeming Jiejian Yindu Zongli", "QQ.com", "2013-07-25", "Very useful")};
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
        final ImageView imageview = (ImageView)rowview.findViewById(R.id.main_list_item_image);

         class DownLoadImageAsync extends AsyncTask<String, Integer, Bitmap> {
            protected Bitmap doInBackground(String ...args) {
                String _url = args[0];
                try {
                    URL url = new URL(_url);
                    InputStream in;
                    BufferedInputStream buf;
                    in = url.openStream();
                    buf = new BufferedInputStream(in);
                    Bitmap bmap = BitmapFactory.decodeStream(buf);
                    if (in != null) {
                        in.close();
                    }
                    if (buf != null){
                        buf.close();
                    }
                    return (bmap);
                }
                catch(Exception e) {
                    return null;
                }
            }

            protected void onPostExecute(Bitmap image) {
                imageview.setImageBitmap(image);
            }
        }

        new DownLoadImageAsync().execute("http://sc.52design.com/pic2/201210/2012102414590411411.jpg");
		return rowview;
	}


}



