package com.rudie.severin.textadventure.InformationHolders;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.res.ResourcesCompat;

import com.rudie.severin.textadventure.R;

import java.util.HashMap;

/**
 * Created by erikrudie on 8/2/16.
 */
public class ImageConstructor {

    private static ImageConstructor instance;
    private HashMap<String, Integer> imageHashMap = new HashMap<>();
    private Context mContext;

    private ImageConstructor() {
        imageHashMap.put(PH.COOKIE, R.drawable.cookie_small);
        imageHashMap.put("PodsLanding", R.drawable.pods_landing);
        imageHashMap.put("PodsScavange", R.drawable.pods_scavenge);
        imageHashMap.put("RoboBodies", R.drawable.robo_pile);
        imageHashMap.put("ProfessorNotevil", R.drawable.professor_notevil);
    }

    public void giveContext(Context context) {
        mContext = context;
    }

    public static ImageConstructor getInstance() {
        if (instance == null) {
            instance = new ImageConstructor();
        }
        return instance;
    }

    public Integer getDrawable(String key) {
        return imageHashMap.get(key);
    }

}

