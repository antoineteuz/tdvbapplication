package com.android.mytdvbapp.mytdvbapplication.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.mytdvbapp.mytdvbapplication.R;

/**
 * Created by antoinepelletier on 24/12/2017.
 */

public class ActorViewHolder extends RecyclerView.ViewHolder {

    public TextView tv_actor_id;
    public TextView tv_actor_name;
    public TextView tv_actor_role;

    public ActorViewHolder(View itemView) {
        super(itemView);

        tv_actor_id = itemView.findViewById(R.id.actor_id);
        tv_actor_name = itemView.findViewById(R.id.actor_name);
        tv_actor_role = itemView.findViewById(R.id.actor_role);
    }
}
