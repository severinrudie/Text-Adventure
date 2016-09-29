package com.rudie.severin.machosquad.Adapters;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rudie.severin.machosquad.R;
import com.rudie.severin.machosquad.Utility.ItemTouchHelperAdapter;
import com.rudie.severin.machosquad.Utility.OnStartDragListener;

import java.util.Collections;
import java.util.List;

/**
 * Created by erikrudie on 9/26/16.
 */


public class CharacterStatisticsAdapter extends
  RecyclerView.Adapter<CharacterStatisticsAdapter.ViewHolder>
  implements ItemTouchHelperAdapter {

  public static class ViewHolder extends RecyclerView.ViewHolder {
    public Button button;
    public View parent;

    public ViewHolder(View itemView) {
      super(itemView);

      button = (Button) itemView.findViewById(R.id.textview_statistic_characterStatsRecycler);
      parent = (View) itemView;
    }
  }

  private List<String> mStatistics;
  private Context mContext;
  private final OnStartDragListener mDragStartListener;

  public CharacterStatisticsAdapter(Context context, List<String> statistics,
                                    OnStartDragListener dragStartListener) {
    mStatistics = statistics;
    mContext = context;
    mDragStartListener = dragStartListener;
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
  public void onBindViewHolder(final CharacterStatisticsAdapter.ViewHolder viewHolder,
                               int position) {
    String statName = mStatistics.get(position);

    Button button = viewHolder.button;
    button.setText(statName);

    button.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        if (MotionEventCompat.getActionMasked(event) ==
          MotionEvent.ACTION_DOWN) {
          mDragStartListener.onStartDrag(viewHolder);
        }
        return false;
      }
    });
  }

  // Returns the total count of items in the list
  @Override
  public int getItemCount() {
    return mStatistics.size();
  }

  @Override
  public void onItemDismiss(int position) {
    mStatistics.remove(position);
    notifyItemRemoved(position);
  }

  @Override
  public boolean onItemMove(int fromPosition, int toPosition) {
    if (fromPosition < toPosition) {
      for (int i = fromPosition; i < toPosition; i++) {
        Collections.swap(mStatistics, i, i + 1);
      }
    } else {
      for (int i = fromPosition; i > toPosition; i--) {
        Collections.swap(mStatistics, i, i - 1);
      }
    }
    notifyItemMoved(fromPosition, toPosition);
    return true;
  }

}

