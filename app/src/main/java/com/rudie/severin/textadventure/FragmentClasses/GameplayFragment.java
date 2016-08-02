package com.rudie.severin.textadventure.FragmentClasses;

import android.content.Context;
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
import android.widget.TextView;

import com.rudie.severin.textadventure.Adapters.ChoiceAdapter;
import com.rudie.severin.textadventure.DatabaseClasses.DBInterfacer;
import com.rudie.severin.textadventure.InformationHolders.ChoiceData;
import com.rudie.severin.textadventure.InformationHolders.CurrentInventoryAndStats;
import com.rudie.severin.textadventure.InformationHolders.PH;
import com.rudie.severin.textadventure.R;

import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameplayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GameplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
//implements PopupFragment.PopupCompleteListener
public class GameplayFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    private List<ChoiceData> choiceList;
    private RecyclerView rvChoices;
    private ChoiceAdapter adapter;
    private DBInterfacer helper;
    private TextView textviewText;
    private int nextNode;
    private int currentCharacterId;

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

//    private OnFragmentInteractionListener mListener;

    public GameplayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
    // * @param param1 Parameter 1.
    // * @param param2 Parameter 2.
     * @return A new instance of fragment GameplayFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        if (nextNode == 0) {
            nextNode = helper.getCurrentNode(currentCharacterId, getActivity());
        }
        textviewText = (TextView) view.findViewById(R.id.textviewPlayContent);
        rvChoices = (RecyclerView) view.findViewById(R.id.recyclerviewPlayChoices);
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
                    nextNode = choiceList.get(selectedButtonPos).getToNode();
                    int popupId;
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    PopupFragment newFragment = PopupFragment.newInstance();
                    if ((testType == -1) || testedValue >= testDifficulty ) {
                        popupId = choiceList.get(selectedButtonPos).getConnectedSuccessPopup();
//                        nextNode = choiceList.get(selectedButtonPos).getToNode();
//                        changeToNewNode(nextNode, currentCharacterId);
//                        FragmentManager manager = getSupportFragmentManager();
//                        PopupFragment newFragment = PopupFragment.newInstance();

//                        Bundle bundle = new Bundle();
//                        bundle.putInt(PH.POPUP_ID, popupId);
//                        newFragment.show(manager, "dialog");
                    } else {
                        popupId = choiceList.get(selectedButtonPos).getConnectedFailPopup();
//                        nextNode = choiceList.get(selectedButtonPos).getToNode();
//                        changeToNewNode(nextNode, currentCharacterId);
//                        FragmentManager manager = getSupportFragmentManager();
//                        PopupFragment newFragment = PopupFragment.newInstance();
//                        newFragment.show(manager, "dialog");
                    }
                    Bundle bundle = new Bundle();
                    bundle.putInt(PH.POPUP_ID, popupId);
                    newFragment.setArguments(bundle);
                    newFragment.show(manager, "dialog");
                }
                adapter.resetSelectedButton();
            }
        });  // END setNextNode.setOnClickListener



        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

//int nodeId,
    public void changeToNewNode( int charId) {
        // image
        // animation
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
        String nodeText = helper.getCurrentNodeText(nextNode, getActivity());
        nodeText = insertNamesIntoNodeText(nodeText, charId);
        nodeText = cleanEscapeCharactersFromText(nodeText);
        textviewText.setText(nodeText);
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

//    @Override
//    public void closePopupNow() {
//        FragmentManager manager = getActivity().getSupportFragmentManager();
//        PopupFragment fragment = (PopupFragment) manager.getFragments().get(0);
//        manager.beginTransaction().remove(fragment).commit();
//        changeToNewNode(nextNode, currentCharacterId);
//    }

    // BEGIN getters and setters

}
