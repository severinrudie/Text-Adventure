package com.rudie.severin.textadventure.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rudie.severin.textadventure.R;

import java.util.List;

/**
 * Created by erikrudie on 9/26/16.
 */


public class CharacterStatisticsAdapter extends
  RecyclerView.Adapter<CharacterStatisticsAdapter.ViewHolder> {

  public static class ViewHolder extends RecyclerView.ViewHolder {
    public Button button;

    public ViewHolder(View itemView) {
      super(itemView);

      button = (Button) itemView.findViewById(R.id.textview_statistic_characterStatsRecycler);
    }
  }

  private List<String> mStatistics;
  private Context mContext;

  public CharacterStatisticsAdapter(Context context, List<String> statistics) {
    mStatistics = statistics;
    mContext = context;
  }

  private Context getContext() {
    return mContext;
  }

  @Override
  public CharacterStatisticsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    LayoutInflater inflater = LayoutInflater.from(context);

    View view = inflater.inflate(R.layout.recycleritem_character_statistic, parent, false);

    ViewHolder viewHolder = new ViewHolder(view);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(CharacterStatisticsAdapter.ViewHolder viewHolder, int position) {
    String statName = mStatistics.get(position);

    Button textView = viewHolder.button;
    textView.setText(statName);
  }

  // Returns the total count of items in the list
  @Override
  public int getItemCount() {
    return mStatistics.size();
  }
}

