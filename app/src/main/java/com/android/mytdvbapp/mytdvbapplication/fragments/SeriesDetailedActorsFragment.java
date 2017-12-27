package com.android.mytdvbapp.mytdvbapplication.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.mytdvbapp.mytdvbapplication.R;
import com.android.mytdvbapp.mytdvbapplication.adapters.ActorAdapter;
import com.android.mytdvbapp.mytdvbapplication.base.AbstractFragment;
import com.android.mytdvbapp.mytdvbapplication.models.ActorInfo;
import com.android.mytdvbapp.mytdvbapplication.models.response.SerieDetailedActorsResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Response;
import rx.Subscriber;

/**
 * Created by antoinepelletier on 24/12/2017.
 */

public class SeriesDetailedActorsFragment extends AbstractFragment {

    private static String ID = "SeriesDetailedActorsFragment";

    @BindView(R.id.rv_actors)
    RecyclerView rv_actors;

    private List<ActorInfo> actors = new ArrayList<>();
    private ActorAdapter adapter;

    /**
     * initialize view
     */
    @Override
    protected void initViews() {
        initDatas();
    }

    private void initDatas() {
        mHost.serviceManager.getActorsSerie(mHost.buildHeaders(), mHost.id, new Subscriber<Response<SerieDetailedActorsResponse>>() {
            @Override
            public void onCompleted() {
                //todo : loader
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Response<SerieDetailedActorsResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getData() != null && response.body().getData().size() > 0) {
                        actors = response.body().getData();
                        initActorsRV();
                    }
                }
            }
        });
    }

    /**
     * Method to initialize the recycler view
     */
    private void initActorsRV() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv_actors.setLayoutManager(linearLayoutManager);
        adapter = new ActorAdapter(actors);
        rv_actors.setAdapter(adapter);
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
        return R.layout.actors_fragment;
    }
}
