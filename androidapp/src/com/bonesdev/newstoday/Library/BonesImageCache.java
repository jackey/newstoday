package com.bonesdev.newstoday.Library;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author  Jackey Chen
 * @description Image cache tools.
 */
public class BonesImageCache implements IBonesCache<Bitmap>{
    static String cacheDir = android.os.Environment.getExternalStorageDirectory().toString();
    String TAG = this.getClass().getSimpleName();
    public void set(String key, Bitmap bitmap) {
       String filename = this.getValideKey(key);
        Log.d(TAG, filename);
        try {
            File file = new File(cacheDir, filename);
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        }
        catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }

        Log.d(TAG, "cache set " + filename);
    }

    public String getValideKey(String key) {
        return key.replace("http://", "http_file_").replaceAll("^:[0-9]+/", "").replace("/", "_").replace(".", "_");
    }

    public BonesImageCache() {

    }

    public Bitmap get(String key) {
        String filename = this.getValideKey(key);
        try {
            File file = new File(cacheDir, filename);
            FileInputStream in = new FileInputStream(file);
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            return bitmap;
        }
        catch (Exception e) {
            Log.d(TAG,e.getMessage());
        }
        Log.d(TAG, "cache get " + key);
        return null;
    }
}
