package com.android.mytdvbapp.mytdvbapplication.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.mytdvbapp.mytdvbapplication.R;

/**
 * Created by antoinepelletier on 24/12/2017.
 */

public class EpisodeViewHolder extends RecyclerView.ViewHolder {

    public TextView tv_number;
    public TextView tv_last_update;
    public TextView tv_title;
    public TextView tv_overview;

    public EpisodeViewHolder(View itemView) {
        super(itemView);

        tv_number = itemView.findViewById(R.id.episode_number);
        tv_last_update = itemView.findViewById(R.id.episode_last_update);
        tv_title = itemView.findViewById(R.id.episode_name);
        tv_overview = itemView.findViewById(R.id.episode_overview);
    }
}
