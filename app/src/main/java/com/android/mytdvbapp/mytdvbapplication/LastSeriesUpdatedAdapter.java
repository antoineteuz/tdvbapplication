package com.android.mytdvbapp.mytdvbapplication;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.mytdvbapp.mytdvbapplication.activities.SerieDetailedActivity;
import com.android.mytdvbapp.mytdvbapplication.models.SeriesInfo;

import java.util.List;

import static com.android.mytdvbapp.mytdvbapplication.helper.Constants.SERIE_NUMBER;

/**
 * Created by antoinepelletier on 17/12/2017.
 */

public class LastSeriesUpdatedAdapter extends RecyclerView.Adapter<LastSeriesUpdatedViewHolder> {

    private static String TAG = "LastSeriesUpdatedAdapter";

    private List<SeriesInfo> series;
    private Context mContext;

    public LastSeriesUpdatedAdapter(List<SeriesInfo> series, Context context) {
        this.series = series;
        mContext = context;
    }

    @Override
    public LastSeriesUpdatedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.series_updated_item, parent, false);
        return new LastSeriesUpdatedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LastSeriesUpdatedViewHolder holder, int position) {
        final SeriesInfo data = series.get(position);

        holder.tv_id.setText(data.getId());
        holder.tv_time.setText(data.getLastUpdated());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDetailActivity(data.getId());
            }
        });
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

    /**
     * Method to launch details series activity
     */
    private void launchDetailActivity(String id) {
        Intent intent = new Intent(mContext, SerieDetailedActivity.class);
        Bundle args = new Bundle();
        args.putSerializable(SERIE_NUMBER, id);
        intent.putExtras(args);
        try {
            mContext.startActivity(intent);
        } catch (final ActivityNotFoundException ex) {
            Log.e("", "Couldn't find activity:", ex);
        }
    }
}
