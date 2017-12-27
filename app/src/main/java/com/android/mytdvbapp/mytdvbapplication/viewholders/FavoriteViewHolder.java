package com.android.mytdvbapp.mytdvbapplication.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mytdvbapp.mytdvbapplication.R;

/**
 * Created by antoinepelletier on 24/12/2017.
 */

public class FavoriteViewHolder extends RecyclerView.ViewHolder {

    public TextView tv_favorite_id;
    public ImageView img_favorite_delete;

    public FavoriteViewHolder(View itemView) {
        super(itemView);

        tv_favorite_id = itemView.findViewById(R.id.favorite_id);
        img_favorite_delete = itemView.findViewById(R.id.favorite_delete);
    }
}
