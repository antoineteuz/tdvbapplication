package com.android.mytdvbapp.mytdvbapplication.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.mytdvbapp.mytdvbapplication.activities.SerieDetailedActivity;

import butterknife.ButterKnife;

/**
 * Created by antoinepelletier on 24/12/2017.
 */

public abstract class AbstractFragment extends Fragment {

    private static String ID = "";
    protected SerieDetailedActivity mHost;
    private View mRootView;

    /**
     * @return Fragment layout
     */
    @LayoutRes
    protected int getLayout() {
        return -1;
    }

    /**
     * initialize view
     */
    protected abstract void initViews();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHost = (SerieDetailedActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mHost = (SerieDetailedActivity) getActivity();
        mRootView = inflater.inflate(getLayout(), container, false);
        //
        ButterKnife.bind(this, mRootView);
        //
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    public abstract String getID();
}
