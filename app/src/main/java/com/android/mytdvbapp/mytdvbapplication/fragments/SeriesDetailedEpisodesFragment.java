package com.android.mytdvbapp.mytdvbapplication.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.mytdvbapp.mytdvbapplication.adapters.EpisodeAdapter;
import com.android.mytdvbapp.mytdvbapplication.R;
import com.android.mytdvbapp.mytdvbapplication.base.AbstractFragment;
import com.android.mytdvbapp.mytdvbapplication.models.EpisodeInfo;
import com.android.mytdvbapp.mytdvbapplication.models.response.SeriesDetailedEpisodeResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Response;
import rx.Subscriber;


/**
 * Created by antoinepelletier on 24/12/2017.
 */

public class SeriesDetailedEpisodesFragment extends AbstractFragment {

    private static String ID = "SeriesDetailedEpisodesFragment";

    @BindView(R.id.rv_episodes)
    RecyclerView rv_episodes;

    private List<EpisodeInfo> mEpisodes = new ArrayList<>();
    private EpisodeAdapter mAdapter;

    /**
     * initialize view
     */
    @Override
    protected void initViews() {
        initDatas();
    }

    /**
     * Function to initialize datas recycler view
     */
    private void initDatas() {
        mHost.serviceManager.getEpisodesSerie(mHost.buildHeaders(), mHost.id, new Subscriber<Response<SeriesDetailedEpisodeResponse>>() {
            @Override
            public void onCompleted() {
                // todo : loader
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Response<SeriesDetailedEpisodeResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getData() != null && response.body().getData().size() > 0) {
                        mEpisodes = response.body().getData();
                        initEpisodesRV();
                    } else {
                        // todo: handle error cases
                    }
                }
            }
        });
    }

    /**
     * Method to initialize recycler view with his data and his custom adapter
     */
    private void initEpisodesRV() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv_episodes.setLayoutManager(linearLayoutManager);
        mAdapter = new EpisodeAdapter(mEpisodes);
        rv_episodes.setAdapter(mAdapter);
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
        return R.layout.episodes_fragment;
    }
}
