package com.android.mytdvbapp.mytdvbapplication.fragments;

import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.mytdvbapp.mytdvbapplication.R;
import com.android.mytdvbapp.mytdvbapplication.base.AbstractFragment;

import butterknife.BindView;

/**
 * Created by antoinepelletier on 24/12/2017.
 */

public class SeriesDetailedMainInfosFragment extends AbstractFragment {

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tv_overview)
    TextView tv_overview;

    @BindView(R.id.genre_container)
    LinearLayout genre_container;

    @BindView(R.id.genre_content)
    TextView tv_genre_content;

    @BindView(R.id.rating_container)
    LinearLayout rating_container;

    @BindView(R.id.rating)
    RatingBar mRating;

    @BindView(R.id.rating_value)
    TextView tv_rating_value;

    @BindView(R.id.actors_container)
    RelativeLayout actors_container;

    @BindView(R.id.episodes_container)
    RelativeLayout episodes_container;

    private static String ID = "SeriesDetailedMainInfosFragment";

    /**
     * initialize view
     */
    @Override
    protected void initViews() {


        // Title
        if (mHost.serie.getSeriesName() != null && !TextUtils.isEmpty(mHost.serie.getSeriesName())) {
            tv_title.setText(mHost.serie.getSeriesName());
        } else {
            tv_title.setVisibility(View.GONE);
        }

        // Summary
        if (mHost.serie.getOverview() != null && !TextUtils.isEmpty(mHost.serie.getOverview())) {
            tv_overview.setText(mHost.serie.getOverview());
        } else {
            tv_overview.setVisibility(View.GONE);
        }

        // Genre
        String genre = "[";
        if (mHost.serie.getGenre() != null && mHost.serie.getGenre().size() > 0) {
            for (int i = 0; i < mHost.serie.getGenre().size(); i++) {
                if (i == mHost.serie.getGenre().size() - 1) {
                    genre += mHost.serie.getGenre().get(i) + "]";
                } else {
                    genre += mHost.serie.getGenre().get(i) + ", ";
                }
            }
            tv_genre_content.setText(genre);
        } else {
            genre_container.setVisibility(View.GONE);
        }

        // Rating
        float rating = 0;
        if (mHost.serie.getSiteRating() != 0) {
            rating = mHost.serie.getSiteRating() / 2;
            mRating.setRating(rating);
            tv_rating_value.setText(String.valueOf(rating));
        } else {
            rating_container.setVisibility(View.GONE);
        }

        initListeners();
    }

    private void initListeners() {
        actors_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHost.launchActorsFragment();
            }
        });

        episodes_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHost.launchEpisodesFragment();
            }
        });
    }

    @Override
    public String getID() {
        return ID;
    }

    /**
     * @return Fragment layout
     */
    @Override
    protected int getLayout() {
        return R.layout.series_detailed_fragment;
    }


}
