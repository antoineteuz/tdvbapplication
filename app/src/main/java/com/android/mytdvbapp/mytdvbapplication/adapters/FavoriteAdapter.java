package com.android.mytdvbapp.mytdvbapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.mytdvbapp.mytdvbapplication.R;
import com.android.mytdvbapp.mytdvbapplication.viewholders.FavoriteViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antoinepelletier on 24/12/2017.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteViewHolder> {

    private List<String> mDatas = new ArrayList<>();
    private OnDeleteFavorite onDeleteFavorite;

    public FavoriteAdapter(List<String> favorites, OnDeleteFavorite onDeleteFavorite) {
        mDatas = favorites;
        this.onDeleteFavorite = onDeleteFavorite;
    }

    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FavoriteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_item, parent, false));
    }


    @Override
    public void onBindViewHolder(final FavoriteViewHolder holder, final int position) {
        final String data = mDatas.get(position);

        holder.tv_favorite_id.setText(data);
        holder.img_favorite_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeleteFavorite.OnDeleteFavoriteClicked(holder.getAdapterPosition(), data);
            }
        });

    }

    public void remove(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

}
