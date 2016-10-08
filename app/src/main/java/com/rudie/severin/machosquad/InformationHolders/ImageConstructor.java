package com.rudie.severin.machosquad.InformationHolders;

import android.content.Context;

import com.rudie.severin.machosquad.R;

import java.util.HashMap;

/**
 * Created by erikrudie on 8/2/16.
 */
public class ImageConstructor {

  private static ImageConstructor instance;
  private HashMap<String, String> urlHashMap = new HashMap<>();
  private HashMap<String, Integer> drawableHashMap = new HashMap<>();
  private Context mContext;

  private ImageConstructor() {

    urlHashMap.put("PodsLanding", "https://s5.postimg.org/495sn2b0j/Pods_Landing.jpg");
    urlHashMap.put("PodsScavange", "https://s5.postimg.org/fmsby9lj7/Pods_Scavange.jpg");
    urlHashMap.put("RoboBodies", "https://s5.postimg.org/3ngtqyfyb/Robo_Pile.jpg");
    urlHashMap.put("ProfessorNotevil", "https://s5.postimg.org/e90p2ym9v/Professor_Notevil.jpg");
    urlHashMap.put("Mechaspider", "https://s5.postimg.org/ub389clz7/Mechaspider.jpg");
    urlHashMap.put("BuddyLounging", "https://s5.postimg.org/lesg5evcz/Buddy_Lounging.jpg");
    urlHashMap.put("EmergencyFlag", "https://s5.postimg.org/r97e2a2ir/Emergency_Flag.jpg");
    urlHashMap.put("BuffEagle", "https://s5.postimg.org/4ay3zg6dj/buff_bird_medium.png");
    urlHashMap.put("HackTheButton", "https://s5.postimg.org/lz2fazi9v/Hack_The_Button.jpg");

    drawableHashMap.put("PodsLanding", R.drawable.pods_landing);
    drawableHashMap.put("PodsScavange", R.drawable.pods_scavange);
    drawableHashMap.put("RoboBodies", R.drawable.robo_pile);
    drawableHashMap.put("ProfessorNotevil", R.drawable.professor_notevil);
    drawableHashMap.put("Mechaspider", R.drawable.mechaspider);
    drawableHashMap.put("BuddyLounging", R.drawable.buddy_lounging);
    drawableHashMap.put("EmergencyFlag", R.drawable.emergency_flag);
    drawableHashMap.put("BuffEagle", R.drawable.buff_bird);
    drawableHashMap.put("HackTheButton", R.drawable.hack_the_button);
  }

  public static ImageConstructor getInstance() {
    if (instance == null) {
      instance = new ImageConstructor();
    }
    return instance;
  }

  public void giveContext(Context context) {
    mContext = context;
  }

  public String getUrl(String key) {
    return urlHashMap.get(key);
  }

  public int getDrawable(String key) {
    return drawableHashMap.get(key);
  }

}

