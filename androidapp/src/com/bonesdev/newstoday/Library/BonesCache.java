package com.bonesdev.newstoday.Library;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * Created by jackey on 7/29/13.
 */
public class BonesCache {
    private BonesCache() {
        ;
    }

    public static IBonesCache instance(String class_name) {
        try {
            if (class_name == "BonesImageCache") {
                return new BonesImageCache();
            }
            else {
                return null;
            }
        }
        catch (Exception e) {
            ;
        }
        return null;
    }
}


