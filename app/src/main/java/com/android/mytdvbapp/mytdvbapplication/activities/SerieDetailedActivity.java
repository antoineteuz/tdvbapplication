package com.android.mytdvbapp.mytdvbapplication.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.mytdvbapp.mytdvbapplication.R;
import com.android.mytdvbapp.mytdvbapplication.base.AbstractActivity;
import com.android.mytdvbapp.mytdvbapplication.models.SerieDetailsInfo;
import com.android.mytdvbapp.mytdvbapplication.models.Session;
import com.android.mytdvbapp.mytdvbapplication.models.response.SerieDetailedResponse;
import com.android.mytdvbapp.mytdvbapplication.network.ServiceException;
import com.android.mytdvbapp.mytdvbapplication.network.ServiceManager;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;
import rx.Subscriber;

import static com.android.mytdvbapp.mytdvbapplication.helper.Constants.SERIE_NUMBER;

public class SerieDetailedActivity extends AbstractActivity {

    private static String TAG = "SerieDetailedActivity";

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

    @BindView(R.id.actors_container)
    RelativeLayout actors_container;

    @BindView(R.id.episodes_container)
    RelativeLayout episodes_container;

    private SerieDetailsInfo serie;
    private ServiceManager serviceManager;
    private Bundle args;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie_detailed);
        //
        ButterKnife.bind(this);
        //
        initDatas();
    }

    /**
     * Method to initialize a data
     */
    private void initDatas() {
        args = getIntent().getExtras();
        if (args != null) {
            id = args.getString(SERIE_NUMBER);
            //
            serviceManager = new ServiceManager();
            //
            progressDialog.show();
            serviceManager.getDetailsSerie(buildHeaders(), id, new Subscriber<Response<SerieDetailedResponse>>() {
                @Override
                public void onCompleted() {
                    progressDialog.dismiss();
                    Log.d(TAG, "series details - onCompleted");
                }

                @Override
                public void onError(Throwable e) {
                    progressDialog.dismiss();
                    Log.d(TAG, e.getMessage());
                }

                @Override
                public void onNext(Response<SerieDetailedResponse> response) {
                    progressDialog.dismiss();
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            serie = response.body().getData();
                            initViews();
                        } else {
                            //todo : alert dialog
                        }
                    } else {
                        //todo: alert dialog
                    }
                }
            });
        } else {
            //todo : alert dialog or finish ?
        }
    }

    /**
     * Method to initialize views with content from web service
     */
    private void initViews() {
        // Title
        if (serie.getSeriesName() != null && !TextUtils.isEmpty(serie.getSeriesName())) {
            tv_title.setText(serie.getSeriesName());
        } else {
            tv_title.setVisibility(View.GONE);
        }

        // Summary
        if (serie.getOverview() != null && !TextUtils.isEmpty(serie.getOverview())) {
            tv_overview.setText(serie.getOverview());
        } else {
            tv_overview.setVisibility(View.GONE);
        }

        // Genre
        String genre = "[";
        if (serie.getGenre() != null && serie.getGenre().size() > 0) {
            for (int i = 0; i < serie.getGenre().size(); i++) {
                if (i == serie.getGenre().size() - 1) {
                    genre += serie.getGenre().get(i) + "]";
                } else {
                    genre += serie.getGenre().get(i) + ", ";
                }
            }
            tv_genre_content.setText(genre);
        } else {
            genre_container.setVisibility(View.GONE);
        }

        // Rating
        float rating = 0;
        if (serie.getSiteRating() != 0) {
            rating = serie.getSiteRating() / 2;
            mRating.setRating(rating);
        } else {
            rating_container.setVisibility(View.GONE);
        }

        initListeners();
    }

    /**
     * Method to initialize listeners on current screen
     */
    private void initListeners() {
        actors_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // todo : launch actors activity or fragment
            }
        });

        episodes_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // todo : launch episodes activity or fragment
            }
        });
    }

    /**
     * Method to build headers send to the web service
     *
     * @return map of key and value headers
     */
    private HashMap<String, String> buildHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        try {
            headers.put("Authorization", Session.get().getSessionToken().getToken());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return headers;
    }

    @Override
    public String getTitleBarTitle() {
        return getResources().getString(R.string.serie_detailed);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
    }
}
