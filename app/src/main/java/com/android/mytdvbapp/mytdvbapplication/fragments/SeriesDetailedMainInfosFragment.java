package com.android.mytdvbapp.mytdvbapplication.fragments;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.mytdvbapp.mytdvbapplication.R;
import com.android.mytdvbapp.mytdvbapplication.base.AbstractFragment;
import com.android.mytdvbapp.mytdvbapplication.base.AlertDialogFragment;
import com.android.mytdvbapp.mytdvbapplication.helper.GeneralUtils;
import com.android.mytdvbapp.mytdvbapplication.models.Session;
import com.android.mytdvbapp.mytdvbapplication.models.response.FavoritesResponse;
import com.android.mytdvbapp.mytdvbapplication.network.ServiceException;

import butterknife.BindView;
import retrofit2.Response;
import rx.Subscriber;

/**
 * Created by antoinepelletier on 24/12/2017.
 */

public class SeriesDetailedMainInfosFragment extends AbstractFragment {

    private static String ID = "SeriesDetailedMainInfosFragment";

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.img_favorite)
    ImageView btn_favorite;

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

    @BindView(R.id.user_rating_container)
    LinearLayout user_rating_container;

    @BindView(R.id.user_rating)
    RatingBar mUserRating;

    @BindView(R.id.user_rating_value)
    TextView tv_user_rating_value;

    @BindView(R.id.btn_send_note)
    Button btn_send_note;

    @BindView(R.id.actors_container)
    RelativeLayout actors_container;

    @BindView(R.id.episodes_container)
    RelativeLayout episodes_container;

    @BindView(R.id.btn_share)
    Button btn_share;

    private boolean isChecked = false;

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

        mUserRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                tv_user_rating_value.setText(String.valueOf(v));
            }
        });

        btn_send_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GeneralUtils.showAlertDialog(getContext(),
                        getContext().getString(R.string.title_note_tdvb),
                        getContext().getString(R.string.description_note_tdvb),
                        new AlertDialogFragment.AlertDialogListener() {
                            @Override
                            public void onPositiveButton() {
                                // todo : call WS SEND NOTE
                                getActivity().finish();
                            }

                            @Override
                            public void onNegativeButton() {
                                // todo : dismiss dialog
                            }
                        });
            }
        });

        btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isChecked) {
                    btn_favorite.setImageDrawable(getResources().getDrawable(R.drawable.star_unchecked));
                    isChecked = false;
                } else {
                    GeneralUtils.showAlertDialog(getContext(),
                            getContext().getString(R.string.title_note_tdvb),
                            getContext().getString(R.string.description_note_tdvb),
                            new AlertDialogFragment.AlertDialogListener() {
                                @Override
                                public void onPositiveButton() {
                                    // todo : call WS SEND NOTE
                                    try {
                                        mHost.serviceManager.putFavorite(mHost.id, Session.get().getSessionToken().getToken(), new Subscriber<Response<FavoritesResponse>>() {
                                            @Override
                                            public void onCompleted() {
                                                // todo: progress dialog
                                            }

                                            @Override
                                            public void onError(Throwable e) {

                                            }

                                            @Override
                                            public void onNext(Response<FavoritesResponse> response) {
                                                if (response.isSuccessful()) {
                                                    // todo : show toast or dialog that favorite has been added
                                                    btn_favorite.setImageDrawable(getResources().getDrawable(R.drawable.star_checked));
                                                }
                                            }
                                        });
                                    } catch (ServiceException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onNegativeButton() {
                                    // todo : dismiss dialog
                                }
                            });
                    isChecked = true;
                }
            }
        });

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "I saw this serie tv on tdvb.com, i think you should try it : " + mHost.serie.getSeriesName();
                String shareSub = "Hey i want share this serie with you !";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share this serie"));

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
