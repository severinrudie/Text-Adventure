package com.rudie.severin.textadventure.FragmentClasses;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rudie.severin.textadventure.DatabaseClasses.DBInterfacer;
import com.rudie.severin.textadventure.InformationHolders.PH;
import com.rudie.severin.textadventure.R;

import java.util.HashMap;

public class StatisticsFragment extends android.support.v4.app.Fragment {
    Context mContext;

    public static StatisticsFragment newInstance() {
        return new StatisticsFragment();
    }

    TextView tvHp, tvStrength, tvAgility, tvComradery;
    ImageView ivHp, ivStrength, ivAgility, ivComradery;

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
        int charId = bundle.getInt(PH.tbl_character_id);

        DBInterfacer helper = DBInterfacer.getInstance(getActivity());
        HashMap<Integer, Integer> stats = helper.getStatsForCharacter(charId);
        int hp = helper.getCharacterHp(charId);

//        int strength = stats.get(PH.STRENGTH);
//        int agility = stats.get(PH.AGILITY);
//        int comradery = stats.get(PH.COMRADERY);
        int[] statArray = new int[] {stats.get(PH.STRENGTH_ID), stats.get(PH.AGILITY_ID),
                stats.get(PH.COMRADERY_ID)};

        for (int i : statArray) {
            i = (i + 7) * 10;
        }

        tvHp.setText("Hit Points: " + hp);
        tvStrength.setText("Strength: " + statArray[0]);
        tvAgility.setText("Agility: " + statArray[1]);
        tvComradery.setText("Comradery: " + statArray[2]);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }




}

