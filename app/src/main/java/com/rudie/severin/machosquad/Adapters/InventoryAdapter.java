package com.rudie.severin.machosquad.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rudie.severin.machosquad.InformationHolders.ItemData;
import com.rudie.severin.machosquad.R;

import java.util.List;


public class InventoryAdapter extends
  RecyclerView.Adapter<InventoryAdapter.ViewHolder> {

  private List<ItemData> itemList;
  private Context mContext;
  public InventoryAdapter(Context context, List<ItemData> inventory) {
    itemList = inventory;
    mContext = context;
  }

  private Context getContext() {
    return mContext;
  }

  @Override
  public InventoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    LayoutInflater inflater = LayoutInflater.from(context);

    View contactView = inflater.inflate(R.layout.recycleritem_inventory_layout, parent, false);

    ViewHolder viewHolder = new ViewHolder(contactView);
    return viewHolder;
  }

  // Involves populating data into the item through holder
  @Override
  public void onBindViewHolder(InventoryAdapter.ViewHolder viewHolder, int position) {
    ItemData item = itemList.get(position);

    TextView tvName = viewHolder.tvName;
    tvName.setText(item.getItemName());
    TextView tvPower = viewHolder.tvPower;
    int power = item.getItemPower();
    if (power != 0) {
      tvPower.setText("Power: " + String.valueOf(power));
    } else {
      tvPower.setText("");
    }
    TextView tvStat = viewHolder.tvStat;
    if (power != 0) {
      tvStat.setText("Boosts: " + item.getItemStatName());
    } else {
      tvStat.setText("");
    }
    TextView tvType = viewHolder.tvType;
    tvType.setText("Type: " + item.getItemTypeName());

  }

  // Returns the total count of items in the list
  @Override
  public int getItemCount() {
    return itemList.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    public TextView tvName, tvPower, tvType, tvStat;

    public ViewHolder(View itemView) {
      super(itemView);
      tvName = (TextView) itemView.findViewById(R.id.textview_itemName_inventoryRecyclerview);
      tvPower = (TextView) itemView.findViewById(R.id.textview_itemPower_inventoryRecyclerview);
      tvType = (TextView) itemView.findViewById(R.id.textview_itemType_inventoryRecyclerview);
      tvStat = (TextView) itemView.findViewById(R.id.textview_itemStat_inventoryRecyclerview);
    }
  }


}

