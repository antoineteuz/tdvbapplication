package com.android.mytdvbapp.mytdvbapplication.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.mytdvbapp.mytdvbapplication.R;
import com.android.mytdvbapp.mytdvbapplication.base.AbstractActivity;
import com.android.mytdvbapp.mytdvbapplication.base.NavigationManager;
import com.android.mytdvbapp.mytdvbapplication.fragments.SeriesDetailedActorsFragment;
import com.android.mytdvbapp.mytdvbapplication.fragments.SeriesDetailedEpisodesFragment;
import com.android.mytdvbapp.mytdvbapplication.fragments.SeriesDetailedMainInfosFragment;
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

    @BindView(R.id.tv_serie_id)
    TextView tv_serie_id;

    public SerieDetailsInfo serie;
    public ServiceManager serviceManager;
    private Bundle args;
    public String id;
    private NavigationManager mNavigationManager;

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

        FragmentManager fragmentManager = getSupportFragmentManager();
        mNavigationManager = new NavigationManager();
        mNavigationManager.init(fragmentManager, R.id.series_detailed_container);


        args = getIntent().getExtras();
        if (args != null) {
            id = args.getString(SERIE_NUMBER);
            tv_serie_id.setText("Serie n° " + id);
            //
            serviceManager = new ServiceManager();
            //
            serviceManager.getDetailsSerie(buildHeaders(), id, new Subscriber<Response<SerieDetailedResponse>>() {
                @Override
                public void onCompleted() {
                    Log.d(TAG, "series details - onCompleted");
                }

                @Override
                public void onError(Throwable e) {
                    Log.d(TAG, e.getMessage());
                }

                @Override
                public void onNext(Response<SerieDetailedResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            serie = response.body().getData();
                            launchMainInformationsFragment();
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
     * Method to launch the Main informations Fragment about a specific series
     */
    private void launchMainInformationsFragment() {
        SeriesDetailedMainInfosFragment seriesDetailedMainInfosFragment = new SeriesDetailedMainInfosFragment();
        mNavigationManager.openNoStack(seriesDetailedMainInfosFragment);
    }

    /**
     * Method to launch the Main informations Fragment about a specific series
     */
    public void launchActorsFragment() {
        SeriesDetailedActorsFragment actorsFragment = new SeriesDetailedActorsFragment();
        mNavigationManager.open(actorsFragment);
    }

    /**
     * Method to launch the Main informations Fragment about a specific series
     */
    public void launchEpisodesFragment() {
        SeriesDetailedEpisodesFragment episodesFragment = new SeriesDetailedEpisodesFragment();
        mNavigationManager.open(episodesFragment);
    }

    /**
     * Method to build headers send to the web service
     *
     * @return map of key and value headers
     */
    public HashMap<String, String> buildHeaders() {
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
