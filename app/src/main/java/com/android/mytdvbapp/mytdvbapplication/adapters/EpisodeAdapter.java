package com.android.mytdvbapp.mytdvbapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.mytdvbapp.mytdvbapplication.viewholders.EpisodeViewHolder;
import com.android.mytdvbapp.mytdvbapplication.R;
import com.android.mytdvbapp.mytdvbapplication.models.EpisodeInfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by antoinepelletier on 24/12/2017.
 */

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeViewHolder> {

    private List<EpisodeInfo> episodes;

    public EpisodeAdapter(List<EpisodeInfo> episodes) {
        this.episodes = episodes;
    }

    @Override
    public EpisodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.episode_item, parent, false);
        return new EpisodeViewHolder(view);
    }


    @Override
    public void onBindViewHolder(EpisodeViewHolder holder, int position) {
        final EpisodeInfo episode = episodes.get(position);

        String season = "Season ";
        season += episode.getAiredSeason();
        String episodeString = "Episode ";
        episodeString += episode.getAiredEpisodeNumber();

        String serieInfo = season + " - " + episodeString;

        holder.tv_number.setText(serieInfo);
        holder.tv_last_update.setText(beautifyDate(episode.getLastUpdated()));
        holder.tv_overview.setText(episode.getOverview());
        holder.tv_title.setText(episode.getEpisodeName());
    }

    /**
     * Function to take the great format into the recycler view
     *
     * @param epochTime
     * @return
     */
    private String beautifyDate(long epochTime) {
        Date datetest = new Date(epochTime * 1000);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String datetostring = df.format(datetest);
        return datetostring;
    }

    @Override
    public int getItemCount() {
        return episodes != null ? episodes.size() : 0;
    }
}
