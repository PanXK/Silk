package com.afollestad.silk.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Handles writing/reading images to and from the external disk cache.
 *
 * @author Aidan Follestad
 */
public class DiskCache {

    public DiskCache(Context context) {
        this.context = context;
        setCacheDirectory(null);
    }

    private final Context context;
    private static File CACHE_DIR;

    public void put(String key, Bitmap image) throws Exception {
        File fi = getFile(key);
        Log.d("SilkImageManager.DiskCache", "Writing image to " + fi.getAbsolutePath());
        FileOutputStream os = new FileOutputStream(fi);
        image.compress(Bitmap.CompressFormat.JPEG, 100, os);
    }

    public Bitmap get(String key) {
        File fi = getFile(key);
        if (!fi.exists()) return null;
        return BitmapFactory.decodeFile(fi.getAbsolutePath());
    }

    public File getFile(String key) {
        return new File(CACHE_DIR, key + ".jpeg");
    }

    public void setCacheDirectory(File dir) {
        if (dir == null)
            CACHE_DIR = context.getExternalCacheDir();
        else CACHE_DIR = dir;
        CACHE_DIR.mkdirs();
    }
}
