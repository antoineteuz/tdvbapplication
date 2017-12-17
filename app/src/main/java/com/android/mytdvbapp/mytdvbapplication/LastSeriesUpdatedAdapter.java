package com.android.mytdvbapp.mytdvbapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.mytdvbapp.mytdvbapplication.models.SeriesInfo;

import java.util.List;

/**
 * Created by antoinepelletier on 17/12/2017.
 */

public class LastSeriesUpdatedAdapter extends RecyclerView.Adapter<LastSeriesUpdatedViewHolder> {

    private List<SeriesInfo> series;

    public LastSeriesUpdatedAdapter(List<SeriesInfo> series) {
        this.series = series;
    }

    @Override
    public LastSeriesUpdatedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.series_updated_item, parent, false);
        return new LastSeriesUpdatedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LastSeriesUpdatedViewHolder holder, int position) {
        SeriesInfo data = series.get(position);

        holder.tv_id.setText(data.getId());
        holder.tv_time.setText(data.getLastUpdated());
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return series.size();
    }

    /**
     * Function to take the great format into the recycler view
     *
     * @param epochTime
     * @return
     */
    private String BeautifyDate(String epochTime) {

        return "";
    }
}
