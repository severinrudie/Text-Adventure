package com.rudie.severin.textadventure.FragmentClasses;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.rudie.severin.textadventure.Adapters.ChoiceAdapter;
import com.rudie.severin.textadventure.DatabaseClasses.DBInterfacer;
import com.rudie.severin.textadventure.InformationHolders.ChoiceData;
import com.rudie.severin.textadventure.InformationHolders.CurrentInventoryAndStats;
import com.rudie.severin.textadventure.InformationHolders.ImageConstructor;
import com.rudie.severin.textadventure.InformationHolders.PH;
import com.rudie.severin.textadventure.R;

import java.util.HashMap;
import java.util.List;

public class GameplayFragment extends android.support.v4.app.Fragment {

    private List<ChoiceData> choiceList;
    private RecyclerView rvChoices;
    private ChoiceAdapter adapter;
    private DBInterfacer helper;
    private TextView textviewText;
//    private int nextNode;
    private int currentCharacterId;
    private ImageView imageView;

    public GameplayFragment() {
        // Required empty public constructor
    }

    public static GameplayFragment newInstance(int currentCharacterId) {
        GameplayFragment fragment = new GameplayFragment();
        Bundle args = new Bundle();
        args.putInt(PH.CURRENT_CHARACTER, currentCharacterId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentCharacterId = getArguments().getInt(PH.CURRENT_CHARACTER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gameplay, container, false);

        helper = DBInterfacer.getInstance(getActivity());
        textviewText = (TextView) view.findViewById(R.id.textviewPlayContent);
        rvChoices = (RecyclerView) view.findViewById(R.id.recyclerviewPlayChoices);
        imageView = (ImageView) view.findViewById(R.id.imageviewPlayHeader);

        changeToNewNode(currentCharacterId);

        Button setNextNode = (Button) view.findViewById(R.id.buttonPlayContinue);
        setNextNode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter.getSelectedButtonPos() != -1) {
                    int selectedButtonPos = adapter.getSelectedButtonPos();
                    ChoiceData selectedChoice = choiceList.get(selectedButtonPos);
                    int testType = selectedChoice.getTestType();
                    int testDifficulty = selectedChoice.getDifficulty();
                    int testedValue = 0;
                    if (testType != -1) {
                        HashMap<Integer, Integer> charStats = CurrentInventoryAndStats.getCurrentStats();
                        testedValue = charStats.get(testType);
                        testedValue += CurrentInventoryAndStats.getBestValueForTest(testType);
                    }
                    int nextNode = choiceList.get(selectedButtonPos).getToNode();
                    helper.setCharacterAtNode(nextNode, currentCharacterId);
                    int popupId;
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    PopupFragment popupFragment = PopupFragment.newInstance();
                    if ((testType == -1) || testedValue >= testDifficulty ) {
                        popupId = choiceList.get(selectedButtonPos).getConnectedSuccessPopup();
                    } else {
                        popupId = choiceList.get(selectedButtonPos).getConnectedFailPopup();
                    }
                    Bundle bundle = new Bundle();
                    bundle.putInt(PH.tbl_popup_id, popupId);
                    bundle.putInt(PH.tbl_character_id, currentCharacterId);
                    popupFragment.setArguments(bundle);
                    popupFragment.show(manager, PH.tbl_popup_id);
                }
                adapter.resetSelectedButton();
            }
        });  // END setNextNode.setOnClickListener



        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void changeToNewNode( int charId) {
        if (helper.getCharacterHp(currentCharacterId) <= 0) {
            helper.setCharacterAtNode(PH.DEATH_NODE, currentCharacterId);
        }
        int nextNode = helper.getCurrentNode(currentCharacterId);
        if (choiceList == null) {
            choiceList = helper.getAvailableChoices(nextNode, getActivity());
            adapter = new ChoiceAdapter(getActivity(), choiceList);
            rvChoices.setAdapter(adapter);
            rvChoices.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else {
            choiceList.clear();
            choiceList.addAll(helper.getAvailableChoices(nextNode, getActivity()));
            adapter.notifyDataSetChanged();
        }
        String nodeText = helper.getCurrentNodeText(nextNode);
        nodeText = insertNamesIntoNodeText(nodeText, charId);
        nodeText = cleanEscapeCharactersFromText(nodeText);
        textviewText.setText(nodeText);

        String nodeImage = helper.getCurrentNodeImage(nextNode);
        if (nodeImage.equals(PH.NULL)) {
            imageView.setVisibility(View.GONE);
        } else {
            imageView.setVisibility(View.VISIBLE);
//            Drawable imageDrawable = getResources().getDrawable(ImageConstructor.getInstance().getDrawable(nodeImage));
//            imageView.setImageDrawable(imageDrawable);


            String url = ImageConstructor.getInstance().getDrawable(nodeImage);
            Ion.with(imageView)
                    .placeholder(R.color.colorPrimary)
                    .error(R.color.colorAccent)
//                .animateLoad(spinAnimation)
//                .animateIn(fadeInAnimation)
                    .load(url)
                    .withBitmapInfo();
//                    .setCallback(NotFoundImageLoader.handleNotFound(holder.photo, mContext));

        }

    }

    private String insertNamesIntoNodeText(String nodeText, int charId) {
        DBInterfacer helper = DBInterfacer.getInstance(getActivity());
        String[] firstLastNick = helper.getCharacterFirstNickLast(charId, getActivity());
        nodeText = nodeText.replace("FIRSTNAME", firstLastNick[0]);
        nodeText = nodeText.replace("NICKNAME", firstLastNick[1]);
        nodeText = nodeText.replace("LASTNAME", firstLastNick[2]);
        return nodeText;
    }

    private String cleanEscapeCharactersFromText(String text) {
        text = text.replace("''", "'");
        text = text.replace("\\", "");
        return text;
    }

    // BEGIN getters and setters

}
