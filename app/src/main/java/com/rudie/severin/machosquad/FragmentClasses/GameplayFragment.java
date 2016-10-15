package com.rudie.severin.machosquad.FragmentClasses;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.rudie.severin.machosquad.Adapters.ChoiceAdapter;
import com.rudie.severin.machosquad.DatabaseClasses.DBInterfacer;
import com.rudie.severin.machosquad.InformationHolders.ChoiceData;
import com.rudie.severin.machosquad.InformationHolders.CurrentInventoryAndStats;
import com.rudie.severin.machosquad.InformationHolders.ImageConstructor;
import com.rudie.severin.machosquad.InformationHolders.PH;
import com.rudie.severin.machosquad.R;

import java.util.HashMap;
import java.util.List;

public class GameplayFragment extends android.support.v4.app.Fragment {

  private List<ChoiceData> choiceList;
  private RecyclerView rvChoices;
  private ChoiceAdapter adapter;
  private DBInterfacer helper;
  private TextView textviewText;
  private int currentCharacterId;
  private ImageView imageView;
  private ScrollView scrollView;

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
    scrollView = (ScrollView) view.findViewById(R.id.scrollView);

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
          if ((testType == -1) || testedValue >= testDifficulty) {
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

  public void changeToNewNode(int charId) {
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

      int drawableInt = ImageConstructor.getInstance().getDrawable(nodeImage);
      String url = "Dummy URL to load error image";

      Ion.with(imageView)
        .error(drawableInt)
        .load(url)
        .withBitmapInfo();

    }

    scrollView.fullScroll(scrollView.FOCUS_UP);

  }

  private String insertNamesIntoNodeText(String nodeText, int charId) {
    DBInterfacer helper = DBInterfacer.getInstance(getActivity());
    String[] firstNickLast = helper.getCharacterFirstNickLast(charId, getActivity());
    firstNickLast = removeNullNames(firstNickLast);
    nodeText = nodeText.replace("&PlayerCharacter&", firstNickLast[0] + " " + firstNickLast[1]
      + " " + firstNickLast[2]);
    firstNickLast = removeNullNames(firstNickLast);
    nodeText = nodeText.replace("FIRSTNAME", firstNickLast[0]);
    nodeText = nodeText.replace("NICKNAME", firstNickLast[1]);
    nodeText = nodeText.replace("LASTNAME", firstNickLast[2]);
    return nodeText;
  }

  public String[] removeNullNames(String[] firstNickLast) {
    if (firstNickLast[1].equals(PH.NULL)) {
      firstNickLast[1] = "";
    }
    if (firstNickLast[2].equals(PH.NULL)) {
      firstNickLast[2] = "";
    }
    return firstNickLast;
  }

  public String[] useAvailableNames(String[] firstNickLast) {
    if (firstNickLast[1].equals("")) {
      firstNickLast[1] = firstNickLast[0];
    }
    if (firstNickLast[2].equals("")) {
      firstNickLast[2] = firstNickLast[0];
    }
    return firstNickLast;
  }

  private String cleanEscapeCharactersFromText(String text) {
    text = text.replace("''", "'");
    text = text.replace("\\", "");
    text = text.replace("``", "\"");
    text = text.replace("`", "'");
    return text;
  }

  // BEGIN getters and setters

}
