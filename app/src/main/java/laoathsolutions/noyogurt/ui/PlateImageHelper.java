package laoathsolutions.noyogurt.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

import laoathsolutions.noyogurt.R;

/**
 * Created by ckrishna on 11/16/14.
 */
public class PlateImageHelper {
    private static ArrayList<Bitmap> bitmapCache;

    public static Bitmap getBitmapForPostion(Context context, int position) {
        if(bitmapCache == null) {
            bitmapCache = new ArrayList<Bitmap>();
            Resources r = context.getResources();
            bitmapCache.add(0, BitmapFactory.decodeResource(r, R.drawable.blue));
            bitmapCache.add(1, BitmapFactory.decodeResource(r, R.drawable.green));
            bitmapCache.add(2, BitmapFactory.decodeResource(r, R.drawable.purple));
            bitmapCache.add(3, BitmapFactory.decodeResource(r, R.drawable.purple));
        }
        return bitmapCache.get(position);
    }

    public static int getPlateImageForUserAt(int position) {
        int ret = R.drawable.purple;
        switch(position) {
            case 0: ret = R.drawable.blue;
                break;
            case 1: ret = R.drawable.green;
        }
        return ret;
    }
    public static int getBigPlateImageForUserAt(int position) {
        int ret = R.drawable.purple_ring;
        switch(position) {
            case 0: ret = R.drawable.blue_ring;
                break;
            case 1: ret = R.drawable.green_ring;
        }
        return ret;
    }
}
