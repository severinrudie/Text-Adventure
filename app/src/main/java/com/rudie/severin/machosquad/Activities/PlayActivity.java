package com.rudie.severin.machosquad.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rudie.severin.machosquad.Adapters.ChoiceAdapter;
import com.rudie.severin.machosquad.DatabaseClasses.DBInterfacer;
import com.rudie.severin.machosquad.EventBus.PopupCompleteBus;
import com.rudie.severin.machosquad.FragmentClasses.GameplayFragment;
import com.rudie.severin.machosquad.FragmentClasses.InventoryFragment;
import com.rudie.severin.machosquad.FragmentClasses.PopupFragment;
import com.rudie.severin.machosquad.FragmentClasses.StatisticsFragment;
import com.rudie.severin.machosquad.InformationHolders.ChoiceData;
import com.rudie.severin.machosquad.InformationHolders.CurrentInventoryAndStats;
import com.rudie.severin.machosquad.InformationHolders.PH;
import com.rudie.severin.machosquad.R;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class PlayActivity extends AppCompatActivity {

  static final int inventoryFragmentIndex = 0;
  static final int gamePlayFragmentIndex = 1;
  static final int statsFragmentIndex = 2;
  private static int currentCharacterId;
  MyPagerAdapter adapterViewPager;
  ViewPager vpPager;
  private List<ChoiceData> choiceList;
  private RecyclerView rvChoices;
  private ChoiceAdapter adapter;
  private DBInterfacer helper;
  private TextView textviewText;
  private int nextNode;
  private CompositeDisposable compositeDisposable;
  private PopupCompleteBus popupCompleteBus;

  // Inventory needs to be used by the adapter, but updated once every time PlayActivity.changeToNewNode
// is called.  Private static List is maintained in InventoryActivity.  This is flushed and rebuilt
// by a sql query from DBInterfacer every time the inventory is changed.  All other activities get
// inventory information from InventoryActivity

  public static int getCurrentCharacterId() {
    return currentCharacterId;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_play);

    currentCharacterId = getIntent().getIntExtra(PH.CURRENT_CHARACTER, -1);
    if (currentCharacterId == -1) {
      Log.e("SEVCRASH: ", "currentCharacterId is set to -1");
      finish();
    }
// New character, so new inventory is loaded from the DB.  This also sets a boolean that lets
// ChoiceAdapter know to update its knowlege of the inventory the next time it binds a view
    CurrentInventoryAndStats.refreshFromDb(currentCharacterId, this);

    vpPager = (ViewPager) findViewById(R.id.vpPager);
    adapterViewPager = new MyPagerAdapter(getSupportFragmentManager(), getBaseContext());
    vpPager.setAdapter(adapterViewPager);
    vpPager.setCurrentItem(gamePlayFragmentIndex);

    compositeDisposable = new CompositeDisposable();
    popupCompleteBus = PopupCompleteBus.getInstance();
    compositeDisposable.add(popupCompleteBus.getSubject().subscribe(event -> closePopupNow()));

  }  // end onCreate

  public void closePopupNow() {
    FragmentManager manager = this.getSupportFragmentManager();

    GameplayFragment gpFrag = (GameplayFragment) adapterViewPager.getRegisteredFragment(gamePlayFragmentIndex);
    gpFrag.changeToNewNode(currentCharacterId);

    PopupFragment popupFragment = (PopupFragment) manager.findFragmentByTag(PH.tbl_popup_id);
    try {
      manager.beginTransaction().remove(popupFragment).commit();
    } catch (RuntimeException e) {
      e.printStackTrace();
      // if the screen is rotated while a popup is up, it will not need to be destroyed
    }
  }

  public static class MyPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;
    SparseArray<Fragment> registeredFragments = new SparseArray<>();
    InventoryFragment inventoryFragment = InventoryFragment.newInstance(currentCharacterId);
    GameplayFragment gameplayFragment = GameplayFragment.newInstance(currentCharacterId);
    StatisticsFragment statisticsFragment = StatisticsFragment.newInstance(currentCharacterId);
    private Context mContext;
    public MyPagerAdapter(FragmentManager fragmentManager, Context context) {
      super(fragmentManager);
      mContext = context;
    }

    // Returns total number of pages
    @Override
    public int getCount() {
      return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
      switch (position) {
        case 0: // Fragment # 0 - This will show FirstFragment
          return inventoryFragment;
        case 1: // Fragment # 0 - This will show FirstFragment different title
          return gameplayFragment;
        case 2: // Fragment # 1 - This will show SecondFragment
          return statisticsFragment;
        default:
          return GameplayFragment.newInstance(currentCharacterId);
      }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
      String title = "";
      if (position == gamePlayFragmentIndex) {
        title = "Story";
      } else if (position == inventoryFragmentIndex) {
        title = "Inventory";
      } else if (position == statsFragmentIndex) {
        title = "Statistics";
      }
      return title;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      Fragment fragment = (Fragment) super.instantiateItem(container, position);
      registeredFragments.put(position, fragment);
      return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      registeredFragments.remove(position);
      super.destroyItem(container, position, object);
    }

    public Fragment getRegisteredFragment(int position) {
      return registeredFragments.get(position);
    }
  }
}
