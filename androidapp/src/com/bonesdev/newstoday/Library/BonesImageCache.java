package com.bonesdev.newstoday.Library;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * @author  Jackey Chen
 * @description Image cache tools.
 */
public class BonesImageCache implements IBonesCache<Bitmap>{
    String TAG = this.getClass().getSimpleName();
    public void set(String key, Bitmap bitmap) {
        Log.d(TAG, "cache set");
    }

    public BonesImageCache() {
        ;
    }

    public Bitmap get(String key) {
        Log.d(TAG, "cache get");
        return null;
    }
}
