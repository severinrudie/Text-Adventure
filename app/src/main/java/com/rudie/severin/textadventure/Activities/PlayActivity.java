package com.rudie.severin.textadventure.Activities;

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
import com.rudie.severin.textadventure.FragmentClasses.PopupFragment;
import com.rudie.severin.textadventure.Adapters.ChoiceAdapter;
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
    int inventoryFragmentIndex = 0;
    int gamePlayFragmentIndex = 1;
    int statsFragmentIndex = 2;

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
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

//  Some basic information regarding the current node is collected here, but most associated
//  logic is found in the changeToNewNode method
//        helper = DBInterfacer.getInstance(this);
//        int currentNode = helper.getCurrentNode(currentCharacterId, this);
//        textviewText = (TextView) findViewById(R.id.textviewPlayContent);
//        rvChoices = (RecyclerView) findViewById(R.id.recyclerviewPlayChoices);
//        changeToNewNode(currentNode, currentCharacterId);
//
//        Button setNextNode = (Button) findViewById(R.id.buttonPlayContinue);
//        setNextNode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (adapter.getSelectedButtonPos() != -1) {
//                    int selectedButtonPos = adapter.getSelectedButtonPos();
//                    ChoiceData selectedChoice = choiceList.get(selectedButtonPos);
//                    int testType = selectedChoice.getTestType();
//                    int testDifficulty = selectedChoice.getDifficulty();
//                    int testedValue = 0;
//                    if (testType != -1) {
//                        HashMap<Integer, Integer> charStats = CurrentInventoryAndStats.getCurrentStats();
//                        testedValue = charStats.get(testType);
//                        testedValue += CurrentInventoryAndStats.getBestValueForTest(testType);
//                    }
//                    nextNode = choiceList.get(selectedButtonPos).getToNode();
//                    int popupId;
//                    FragmentManager manager = getSupportFragmentManager();
//                    PopupFragment newFragment = PopupFragment.newInstance();
//                    if ((testType == -1) || testedValue >= testDifficulty ) {
//                        // TODO: store nextnode.  set popup.  On popup destroy, set nextnode
//                        popupId = choiceList.get(selectedButtonPos).getConnectedSuccessPopup();
////                        nextNode = choiceList.get(selectedButtonPos).getToNode();
////                        changeToNewNode(nextNode, currentCharacterId);
////                        FragmentManager manager = getSupportFragmentManager();
////                        PopupFragment newFragment = PopupFragment.newInstance();
//
////                        Bundle bundle = new Bundle();
////                        bundle.putInt(PH.POPUP_ID, popupId);
////                        newFragment.show(manager, "dialog");
//                    } else {
//                        popupId = choiceList.get(selectedButtonPos).getConnectedFailPopup();
////                        nextNode = choiceList.get(selectedButtonPos).getToNode();
////                        changeToNewNode(nextNode, currentCharacterId);
////                        FragmentManager manager = getSupportFragmentManager();
////                        PopupFragment newFragment = PopupFragment.newInstance();
////                        newFragment.show(manager, "dialog");
//                        // TODO: trash this in favor of the above todo
//                    }
//                    Bundle bundle = new Bundle();
//                    bundle.putInt(PH.POPUP_ID, popupId);
//                    newFragment.setArguments(bundle);
//                    newFragment.show(manager, "dialog");
//                }
//                adapter.resetSelectedButton();
//            }
//        });  // END setNextNode.setOnClickListener


    }  // end onCreate

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;
        SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
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
                    return GameplayFragment.newInstance(currentCharacterId);
//                case 1: // Fragment # 0 - This will show FirstFragment different title
//                    return FirstFragment.newInstance(1, "Page # 2");
//                case 2: // Fragment # 1 - This will show SecondFragment
//                    return SecondFragment.newInstance(2, "Page # 3");
                default:
                    return GameplayFragment.newInstance(currentCharacterId);
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
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
    }

    // BEGIN getters and setters

}
