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
//    private HashMap<String, Integer> imageHashMap = new HashMap<>();
    private HashMap<String, String> imageHashMap = new HashMap<>();
    private Context mContext;

    private ImageConstructor() {
//        imageHashMap.put(PH.COOKIE, R.drawable.cookie_small);
//        imageHashMap.put("PodsLanding", R.drawable.pods_landing);
//        imageHashMap.put("PodsScavange", R.drawable.pods_scavenge);
//        imageHashMap.put("RoboBodies", R.drawable.robo_pile);
//        imageHashMap.put("ProfessorNotevil", R.drawable.professor_notevil);

        imageHashMap.put("PodsLanding", "https://s5.postimg.org/495sn2b0j/Pods_Landing.jpg");
        imageHashMap.put("PodsScavange", "https://s5.postimg.org/fmsby9lj7/Pods_Scavange.jpg");
        imageHashMap.put("RoboBodies", "https://s5.postimg.org/3ngtqyfyb/Robo_Pile.jpg");
        imageHashMap.put("ProfessorNotevil", "https://s5.postimg.org/e90p2ym9v/Professor_Notevil.jpg");
        imageHashMap.put("Mechaspider", "https://s5.postimg.org/ub389clz7/Mechaspider.jpg");
        imageHashMap.put("BuddyLounging", "https://s5.postimg.org/lesg5evcz/Buddy_Lounging.jpg");
        imageHashMap.put("EmergencyFlag", "https://s5.postimg.org/r97e2a2ir/Emergency_Flag.jpg");
        imageHashMap.put("BuffEagle", "https://s5.postimg.org/4ay3zg6dj/buff_bird_medium.png");
        imageHashMap.put("HackTheButton", "https://s5.postimg.org/lz2fazi9v/Hack_The_Button.jpg");
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

    public String getDrawable(String key) {
        return imageHashMap.get(key);
    }

}

