package com.android.mytdvbapp.mytdvbapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.mytdvbapp.mytdvbapplication.R;
import com.android.mytdvbapp.mytdvbapplication.models.ActorInfo;
import com.android.mytdvbapp.mytdvbapplication.viewholders.ActorViewHolder;

import java.util.List;

/**
 * Created by antoinepelletier on 24/12/2017.
 */

public class ActorAdapter extends RecyclerView.Adapter<ActorViewHolder> {

    private List<ActorInfo> actors;

    public ActorAdapter(List<ActorInfo> actors) {
        this.actors = actors;
    }

    @Override
    public ActorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.actor_item, parent, false);
        return new ActorViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ActorViewHolder holder, int position) {
        final ActorInfo actor = actors.get(position);

        holder.tv_actor_id.setText("ID : " + actor.getId());
        holder.tv_actor_name.setText(actor.getName());
        holder.tv_actor_role.setText("Role : " + actor.getRole());
    }


    @Override
    public int getItemCount() {
        return actors != null ? actors.size() : 0;
    }
}
