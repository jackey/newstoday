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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity{
	
	final Context context = this;
	Bundle savedInstanceState;
	ListView listview;
    final String TAG = "MainActivity";

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        final float y = e.getY();
        switch (e.getAction()) {
            case MotionEvent.ACTION_UP :
                Log.d(TAG, "Action up");
                break;
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "Action DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "Action MOVE");
                break;
        }

        return super.onTouchEvent(e);
    }

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Bundle savedInstanceStateLocal = savedInstanceState;
		this.setContentView(R.layout.main);
		
		listview = (ListView)findViewById(R.id.mainListView);
		
		final NewsEntityArrayList newsList = NewsEntityArrayList.instance();
		final ArrayList<NewsEntity> list = newsList.nextPager();
        final StableArrayAdapter adapter = new StableArrayAdapter(this, R.layout.main_list_item, list);
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
        listview.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                Log.d("ScrollState", String.valueOf(i));
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisible, int visibleCount, int totalCount) {

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
        if (item != null) {
            return hashMap.get(item.getUUID());
        }
        return 0;
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
        final ImageView imageview;
        imageview = (ImageView)rowview.findViewById(R.id.main_list_item_image);

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

        new DownLoadImageAsync().execute(news.get("imgurl"));
		return rowview;
	}


}



