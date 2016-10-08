package com.rudie.severin.machosquad.FragmentClasses;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rudie.severin.machosquad.DatabaseClasses.DBInterfacer;
import com.rudie.severin.machosquad.InformationHolders.PH;
import com.rudie.severin.machosquad.R;

import java.util.HashMap;

public class StatisticsFragment extends android.support.v4.app.Fragment {
  Context mContext;
  int charId;
  private TextView tvHp, tvStrength, tvAgility, tvComradery;
  private ImageView ivHp, ivStrength, ivAgility, ivComradery;

  public static StatisticsFragment newInstance(int currentCharacterId) {
    StatisticsFragment statisticsFragment = new StatisticsFragment();
    Bundle bundle = new Bundle();
    bundle.putInt(PH.tbl_character_id, currentCharacterId);
    statisticsFragment.setArguments(bundle);
    return statisticsFragment;
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    mContext = context;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_statistics, container, false);

    tvHp = (TextView) view.findViewById(R.id.textviewStatsHP);
    tvStrength = (TextView) view.findViewById(R.id.textviewStatsStrength);
    tvAgility = (TextView) view.findViewById(R.id.textviewStatsAgility);
    tvComradery = (TextView) view.findViewById(R.id.textviewStatsComradery);

    ivHp = (ImageView) view.findViewById(R.id.imageviewStatsHP);
    ivStrength = (ImageView) view.findViewById(R.id.imageviewStatsStrength);
    ivAgility = (ImageView) view.findViewById(R.id.imageviewStatsAgility);
    ivComradery = (ImageView) view.findViewById(R.id.imageviewStatsComradery);

    Bundle bundle = getArguments();
    charId = bundle.getInt(PH.tbl_character_id);

    setText(charId);

    return view;
  }

  private void setText(int charId) {
    DBInterfacer helper = DBInterfacer.getInstance(getActivity());
    HashMap<Integer, Integer> stats = helper.getStatsForCharacter(charId);
    int hp = helper.getCharacterHp(charId);

    int[] statArray = new int[]{stats.get(PH.STRENGTH_ID), stats.get(PH.AGILITY_ID),
      stats.get(PH.COMRADERY_ID)};

    for (int i = 0; i < statArray.length; i++) {
      statArray[i] = (statArray[i] + 7);
    }

    tvHp.setText("Hit Points: " + hp);
    tvStrength.setText("Strength: " + statArray[0]);
    tvAgility.setText("Agility: " + statArray[1]);
    tvComradery.setText("Comradery: " + statArray[2]);

    DisplayMetrics metrics = getActivity().getResources().getDisplayMetrics();
    int width = metrics.widthPixels;
    int height = metrics.heightPixels;

    int baseLength = (int) (width * .7);

    ivHp.getLayoutParams().width = ((baseLength / 10) * hp);
    ivStrength.getLayoutParams().width = ((baseLength / 10) * (statArray[0]));
    ivAgility.getLayoutParams().width = ((baseLength / 10) * (statArray[1]));
    ivComradery.getLayoutParams().width = ((baseLength / 10) * (statArray[2]));

    ivHp.requestLayout();
    ivStrength.requestLayout();
    ivAgility.requestLayout();
    ivComradery.requestLayout();

  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }

  @Override
  public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    if (isVisibleToUser) {
      DBInterfacer helper = DBInterfacer.getInstance(getActivity());
      setText(charId);

    }
  }


}

