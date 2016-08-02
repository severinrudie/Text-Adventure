package com.rudie.severin.textadventure.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.rudie.severin.textadventure.R;
import com.rudie.severin.textadventure.InformationHolders.ChoiceData;
import com.rudie.severin.textadventure.InformationHolders.CurrentInventoryAndStats;
import com.rudie.severin.textadventure.InformationHolders.ItemData;
import com.rudie.severin.textadventure.InformationHolders.PH;

import java.util.List;

/**
 * Created by erikrudie on 7/30/16.
 */
public class ChoiceAdapter extends
        RecyclerView.Adapter<ChoiceAdapter.ViewHolder> {

    private int selectedButtonPos = -1;
    private List<ChoiceData> mChoices;
    private Context mContext;
    private List<ItemData> currentInventory;
    private RecyclerView parent;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView checkType;
        private RadioButton radioButton;

        public ViewHolder(View itemView) {
            super(itemView);
            checkType = (TextView) itemView.findViewById(R.id.textviewCheckTypeRecyclerLayout);
            radioButton = (RadioButton) itemView.findViewById(R.id.radiobuttonRecyclerLayout);
        }
    }

    public ChoiceAdapter(Context context, List<ChoiceData> choices) {
        mChoices = choices;
        mContext = context;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        parent = recyclerView;
    }

    @Override
    public ChoiceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View choiceView = inflater.inflate(R.layout.recycleritem_choice_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(choiceView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    RadioButton lastCheckedRB = null;
    @Override
    public void onBindViewHolder(final ChoiceAdapter.ViewHolder viewHolder, final int position) {
        if (CurrentInventoryAndStats.getAdapterNewInventoryAndSetFalse()) {
            currentInventory = CurrentInventoryAndStats.getCurrentInventory();
        }

        ChoiceData choice = mChoices.get(position);
        int checkInt = choice.getTestType();
        String checkString = "   ";
        switch (checkInt) {
            case PH.STRENGTH_ID: checkString += PH.STRENGTH;
                break;
            case PH.AGILITY_ID: checkString += PH.AGILITY;
                break;
            case PH.COMRADERY_ID: checkString += PH.COMRADERY;
                break;
            default: checkString = "";
        }

        TextView checkType = viewHolder.checkType;
//        checkType.setText(checkString);
        RadioButton radioButton = viewHolder.radioButton;
        radioButton.setText(choice.getText());
        List<Integer> currentItemTypes = CurrentInventoryAndStats.getCurrentItemTypes();
        if (choice.getItemRequired() == -1 ||
                currentItemTypes.contains(choice.getItemRequired())) {
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ((lastCheckedRB != null) && (parent.getChildCount() != 1)) {
                        lastCheckedRB.setChecked(false);
                    }
                    lastCheckedRB = (RadioButton) view;
                    selectedButtonPos = position;
                }
            });
            radioButton.setClickable(true);
            radioButton.setTypeface(null, Typeface.NORMAL);
            radioButton.setTextColor(mContext.getResources().getColor(R.color.normalText));
            checkType.setText(checkString);
        } else if (!(CurrentInventoryAndStats.getCurrentItemTypes().contains(choice.getItemRequired()))) {
            radioButton.setClickable(false);
            radioButton.setTypeface(null, Typeface.ITALIC);
            radioButton.setTextColor(mContext.getResources().getColor(R.color.unSelectableText));
            checkType.setText("   Missing Required Item");
        }
    }

    @Override
    public int getItemCount() {
        return mChoices.size();
    }

    public int getSelectedButtonPos() {
        return selectedButtonPos;
    }

    public void resetSelectedButton() {
        selectedButtonPos = -1;
    }
}

