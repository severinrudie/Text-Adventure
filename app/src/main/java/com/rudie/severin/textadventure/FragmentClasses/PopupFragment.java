package com.rudie.severin.textadventure.FragmentClasses;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rudie.severin.textadventure.InformationHolders.CurrentInventoryAndStats;
import com.rudie.severin.textadventure.InformationHolders.ImageConstructor;
import com.rudie.severin.textadventure.InformationHolders.ItemData;
import com.rudie.severin.textadventure.R;
import com.rudie.severin.textadventure.DatabaseClasses.DBInterfacer;
import com.rudie.severin.textadventure.InformationHolders.PH;

public class PopupFragment extends DialogFragment {
    PopupCompleteListener mCallback;
    Context mContext;

    public static PopupFragment newInstance() {
        return new PopupFragment();
    }

    public interface PopupCompleteListener {
        public void closePopupNow();
    }

    TextView tvText, tvDamage, tvItem;
    ImageView image;
    Button button;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popup, container, false);

        // TODO: image
//        image = (ImageView) view.findViewById(R.id.imageview_image_popupFragment);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview_image_popupFragment);
        button = (Button) view.findViewById(R.id.button_continue_popupFragment);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    try {
                        mCallback = (PopupCompleteListener) mContext;
                        mCallback.closePopupNow();
                    } catch (ClassCastException e) {
                        e.printStackTrace();
                        throw new ClassCastException(mContext.toString()
                                + " must implement PopupCompleteListener");
                    }
            }
        });
        Bundle bundle = getArguments();
        int popupId = bundle.getInt(PH.POPUP_ID);
        int charId = bundle.getInt(PH.tbl_character_id);
        DBInterfacer helper = DBInterfacer.getInstance(mContext);
        Bundle popupData = helper.getPopupData(popupId);
        String text = popupData.getString(PH.tbl_popup_text);
        int damage = popupData.getInt(PH.tbl_popup_damage);
        int item = popupData.getInt(PH.tbl_popup_item);

        tvText = (TextView) view.findViewById(R.id.textview_text_popupFragment);
        tvText.setText(text);

        tvDamage = (TextView) view.findViewById(R.id.textview_damageTaken_popupFragment);
        if (damage != -1) {
            tvDamage.setText("You took " + damage + " damage!");
            tvDamage.setVisibility(View.VISIBLE);
            helper.setCharacterDamageDealt(damage, charId);
        }
        else {
            tvDamage.setVisibility(View.GONE);
        }

        tvItem = (TextView) view.findViewById(R.id.textview_itemFound_popupFragment);
        if (item == 1) {
            tvItem.setVisibility(View.VISIBLE);
            // TODO: you found an item!
            // TODO: give an item
            ItemData template = CurrentInventoryAndStats.getRandomItem();
            ItemData newItem = new ItemData(template.getItemName(), template.getItemPower(),
                    template.getItemTypeId(), charId, template.getAcquireText());
            template = null;
            helper.addItemToInventory(newItem);
            CurrentInventoryAndStats.refreshFromDb(charId, getActivity());
            tvItem.setText(newItem.getItemName());
            tvText.setText(newItem.getAcquireText());
        } else {
            tvItem.setVisibility(View.GONE);
        }

        String image = popupData.getString(PH.tbl_popup_image);
        if (image.equals(PH.NULL)) {
            imageView.setVisibility(View.GONE);
        } else {
            imageView.setVisibility(View.VISIBLE);
            // TODO: image constructor stuff
            Drawable imageDrawable = getResources().getDrawable(ImageConstructor.getInstance().getDrawable(image));
            imageView.setImageDrawable(imageDrawable);
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }




}

