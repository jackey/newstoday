package com.bonesdev.newstoday.Library;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.bonesdev.newstoday.R;
import com.bonesdev.newstoday.Library.BonesCache;

import java.net.URL;

/**
 * Created by jackey on 7/29/13.
 */
public class BonesImageView extends ImageView {
    String url = "";
    String TAG = this.getClass().getSimpleName();
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
                BonesImageCache imageCache = (BonesImageCache) BonesCache.instance("BonesImageCache");
                Bitmap bitmap = imageCache.get(this.url);
                if (bitmap == null) {
                    Log.d(TAG, "No cache item found");
                }
                else {
                    Log.d(TAG, "Found cached item");
                }
                URL _url = new URL(this.url);
                Log.d(TAG, this.url);
            }
            catch (Exception e) {
                Log.d(TAG, "Exception");
                Log.d(TAG, e.getMessage());
                ;
            }
        }
    }
}
