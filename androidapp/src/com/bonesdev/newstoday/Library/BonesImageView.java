package com.bonesdev.newstoday.Library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.bonesdev.newstoday.R;
import com.bonesdev.newstoday.Library.BonesCache;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by jackey on 7/29/13.
 */
public class BonesImageView extends ImageView {
    String url = "";
    String TAG = this.getClass().getSimpleName();
    ImageView imageView = this;
    boolean useCache = true;
    public BonesImageView(Context context) {
        super(context);
        init(context);
    }

    public BonesImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.BonesImageView,
                0, 0);
        try {
            this.url = a.getString(R.styleable.BonesImageView_URL);
            this.useCache = a.getBoolean(R.styleable.BonesImageView_useCache, false);
        }
        catch(Exception e) {
            a.recycle();
        }
        init(context);
    }

    public BonesImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.BonesImageView,
                defStyle, 0);
        try {
            this.url = a.getString(R.styleable.BonesImageView_URL);
            this.useCache = a.getBoolean(R.styleable.BonesImageView_useCache, false);
        }
        catch(Exception e) {
            a.recycle();
        }
        init(context);
    }

    public void init(Context context) {

        if (!this.url.equals("")) {
            try{
                final BonesImageCache imageCache = (BonesImageCache) BonesCache.instance("BonesImageCache");
                Bitmap bitmap = imageCache.get(this.url);
                if (bitmap == null) {
                    class DownLoadImageAsync extends AsyncTask<String, Integer, Bitmap> {
                        protected Bitmap doInBackground(String ...args) {
                            String _url = args[0];
                            final BonesImageCache imageCache = (BonesImageCache) BonesCache.instance("BonesImageCache");
                            Bitmap bitmap = imageCache.get(_url);
                            if (bitmap == null) {
                                try {
                                    URL urlo = new URL(_url);
                                    InputStream in;
                                    BufferedInputStream buf;
                                    in = urlo.openStream();
                                    buf = new BufferedInputStream(in);
                                    Bitmap bmap = BitmapFactory.decodeStream(buf);
                                    imageCache.set(url, bmap);
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
                            else {
                                return bitmap;
                            }
                        }

                        protected void onPostExecute(Bitmap image) {
                            imageView.setImageBitmap(image);
                        }
                    }

                    new DownLoadImageAsync().execute(this.url);
                    Log.d(TAG, this.url);
                    Log.d(TAG, "No cache item found");
                }
                else {
                    Log.d(TAG, "Found cached item");
                }
                if (bitmap != null) {
                    this.setImageBitmap(bitmap);
                }
            }
            catch (Exception e) {
                Log.d(TAG, "Exception");
                Log.d(TAG, e.getMessage());
                ;
            }
        }
    }


}
