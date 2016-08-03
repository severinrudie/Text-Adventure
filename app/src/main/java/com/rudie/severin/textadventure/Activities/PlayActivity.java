package com.rudie.severin.textadventure.Activities;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rudie.severin.textadventure.FragmentClasses.GameplayFragment;
import com.rudie.severin.textadventure.FragmentClasses.InventoryFragment;
import com.rudie.severin.textadventure.FragmentClasses.PopupFragment;
import com.rudie.severin.textadventure.Adapters.ChoiceAdapter;
import com.rudie.severin.textadventure.FragmentClasses.StatisticsFragment;
import com.rudie.severin.textadventure.InformationHolders.ChoiceData;
import com.rudie.severin.textadventure.InformationHolders.CurrentInventoryAndStats;
import com.rudie.severin.textadventure.DatabaseClasses.DBInterfacer;
import com.rudie.severin.textadventure.InformationHolders.PH;
import com.rudie.severin.textadventure.R;

import java.util.List;

public class PlayActivity extends AppCompatActivity implements PopupFragment.PopupCompleteListener {

    private List<ChoiceData> choiceList;
    private RecyclerView rvChoices;
    private ChoiceAdapter adapter;
    private DBInterfacer helper;
    private TextView textviewText;
    private int nextNode;
    private static int currentCharacterId;
    MyPagerAdapter adapterViewPager;
    ViewPager vpPager;
    static final int inventoryFragmentIndex = 0;
    static final int gamePlayFragmentIndex = 1;
    static final int statsFragmentIndex = 2;

    // Inventory needs to be used by the adapter, but updated once every time PlayActivity.changeToNewNode
// is called.  Private static List is maintained in InventoryActivity.  This is flushed and rebuilt
// by a sql query from DBInterfacer every time the inventory is changed.  All other activities get
// inventory information from InventoryActivity

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

    }  // end onCreate

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;
        SparseArray<Fragment> registeredFragments = new SparseArray<>();
        private Context mContext;

        public MyPagerAdapter(FragmentManager fragmentManager, Context context) {
            super(fragmentManager);
            mContext = context;
        }
        InventoryFragment inventoryFragment = InventoryFragment.newInstance(currentCharacterId);
        GameplayFragment gameplayFragment = GameplayFragment.newInstance(currentCharacterId);
        StatisticsFragment statisticsFragment = StatisticsFragment.newInstance(currentCharacterId);

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                // TODO: give the inventory fragment the charid
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

//    private void changeToNewNode(int nodeId, int charId) {
//        // image
//        // animation
//        if (choiceList == null) {
//            choiceList = helper.getAvailableChoices(nodeId, this);
//            adapter = new ChoiceAdapter(this, choiceList);
//            rvChoices.setAdapter(adapter);
//            rvChoices.setLayoutManager(new LinearLayoutManager(this));
//        } else {
//            choiceList.clear();
//            choiceList.addAll(helper.getAvailableChoices(nodeId, this));
//            adapter.notifyDataSetChanged();
//        }
//        String nodeText = helper.getCurrentNodeText(nodeId, this);
//        nodeText = insertNamesIntoNodeText(nodeText, charId);
//        nodeText = cleanEscapeCharactersFromText(nodeText);
//        textviewText.setText(nodeText);
//    }
//
//    private String insertNamesIntoNodeText(String nodeText, int charId) {
//        DBInterfacer helper = DBInterfacer.getInstance(this);
//        String[] firstLastNick = helper.getCharacterFirstNickLast(charId, this);
//        nodeText = nodeText.replace("FIRSTNAME", firstLastNick[0]);
//        nodeText = nodeText.replace("NICKNAME", firstLastNick[1]);
//        nodeText = nodeText.replace("LASTNAME", firstLastNick[2]);
//        return nodeText;
//    }
//
//    private String cleanEscapeCharactersFromText(String text) {
//        text = text.replace("''", "'");
//        text = text.replace("\\", "");
//        return text;
//    }
//
    @Override
    public void closePopupNow() {
//        FragmentManager manager = this.getSupportFragmentManager();
//        PopupFragment fragment = (PopupFragment) manager.getFragments().get(0);
//        manager.beginTransaction().remove(fragment).commit();
//        changeToNewNode(nextNode, currentCharacterId);

        FragmentManager manager = this.getSupportFragmentManager();
//        PopupFragment popup = (PopupFragment) manager.findFragmentById(R.id.fragmentPopup);
//        manager.beginTransaction().remove(popup).commit();

//        GameplayFragment gpFrag = (GameplayFragment)
//                manager.findFragmentById(R.id.fragmentGameplay);
//        gpFrag.changeToNewNode(nextNode, currentCharacterId);

        GameplayFragment gpFrag = (GameplayFragment) adapterViewPager.getRegisteredFragment(gamePlayFragmentIndex);
        gpFrag.changeToNewNode(currentCharacterId);

        PopupFragment popupFragment = (PopupFragment) manager.findFragmentByTag(PH.tbl_popup_id);
        manager.beginTransaction().remove(popupFragment).commit();
    }

    // BEGIN getters and setters


    public static int getCurrentCharacterId() {
        return currentCharacterId;
    }
}
