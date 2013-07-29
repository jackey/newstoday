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
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.bonesdev.newstoday.Library.BonesListView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class MainActivity extends Activity{
	
	final Context context = this;
	Bundle savedInstanceState;
    BonesListView listview;
    Button aboutmeBtn;
    final String TAG = "MainActivity";
    private LinkedList<NewsEntity> list;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Bundle savedInstanceStateLocal = savedInstanceState;
		this.setContentView(R.layout.main);
		
		listview = (BonesListView)findViewById(R.id.mainListView);
		
		final NewsEntityArrayList newsList = NewsEntityArrayList.instance();
        list = newsList.nextPager();
        final StableArrayAdapter adapter = new StableArrayAdapter(this, R.layout.main_list_item, list);
		listview.setAdapter(adapter);
		
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final NewsEntity news = (NewsEntity) parent.getItemAtPosition(position);
                Intent intent = new Intent(context, NewsDetailActivity.class);
                intent.putExtra("com.bonesdev.newstoday.NewsEntity", news);
                context.startActivity(intent, savedInstanceStateLocal);
                overridePendingTransition(R.anim.activity_close_to_right, R.anim.activity_close_to_left);
            }
        });

        listview.setOnRefreshListener(new BonesListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d(TAG, "On refresh");
                list.addFirst(new NewsEntity("News", "Jackey", "2013-05-10", "News"));
                adapter.notifyDataSetChanged();
                listview.onRefreshComplete();
            }
        });

//        aboutmeBtn = (Button)findViewById(R.id.aboutbtn);
//        aboutmeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, AboutmeActivity.class);
//                context.startActivity(intent, savedInstanceStateLocal);
//            }
//        });
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

        //new DownLoadImageAsync().execute(news.get("imgurl"));
		return rowview;
	}


}


