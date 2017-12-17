package com.android.mytdvbapp.mytdvbapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by antoinepelletier on 17/12/2017.
 */

public class LastSeriesUpdatedViewHolder extends RecyclerView.ViewHolder {

    public TextView tv_id;
    public TextView tv_time;

    public LastSeriesUpdatedViewHolder(View itemView) {
        super(itemView);
        tv_id = itemView.findViewById(R.id.series_updated_item_id);
        tv_time = itemView.findViewById(R.id.series_updated_item_time);
    }
}
