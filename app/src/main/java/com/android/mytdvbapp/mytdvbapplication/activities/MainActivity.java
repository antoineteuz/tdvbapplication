package com.android.mytdvbapp.mytdvbapplication.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.mytdvbapp.mytdvbapplication.adapters.LastSeriesUpdatedAdapter;
import com.android.mytdvbapp.mytdvbapplication.R;
import com.android.mytdvbapp.mytdvbapplication.base.AbstractActivity;
import com.android.mytdvbapp.mytdvbapplication.helper.GeneralUtils;
import com.android.mytdvbapp.mytdvbapplication.models.SeriesInfo;
import com.android.mytdvbapp.mytdvbapplication.models.Session;
import com.android.mytdvbapp.mytdvbapplication.models.SessionToken;
import com.android.mytdvbapp.mytdvbapplication.models.response.LoginResponse;
import com.android.mytdvbapp.mytdvbapplication.models.response.SeriesUpdatedResponse;
import com.android.mytdvbapp.mytdvbapplication.network.ServiceException;
import com.android.mytdvbapp.mytdvbapplication.network.ServiceManager;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;
import rx.Subscriber;

public class MainActivity extends AbstractActivity {

    private static String TAG = "MainActivity";

    @BindView(R.id.tv_list_title)
    TextView title;

    @BindView(R.id.rv_last_series)
    RecyclerView rv_series;

    @BindView(R.id.activity_main_progress)
    ProgressBar progressBar;

    @BindView(R.id.tv_list_empty)
    TextView list_empty;

    private ServiceManager serviceManager;

    private List<SeriesInfo> series;
    private LastSeriesUpdatedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        ButterKnife.bind(this);
        //
        serviceManager = new ServiceManager();
        initDatas();
        //

    }

    private void initDatas() {

        String fromTime = getFromTime();
        String toTime = getToTime();

        if (!GeneralUtils.isConnectInternet(this)) {

            GeneralUtils.showAlertDialog(this,
                    getResources().getString(R.string.title_unavailable_connection),
                    getResources().getString(R.string.mess_unavailable_connection));
            return;
        } else {
            progressBar.setVisibility(View.VISIBLE);
            serviceManager.getLastSeriesUpdated(buildHeaders(), fromTime, toTime, new Subscriber<Response<SeriesUpdatedResponse>>() {
                @Override
                public void onCompleted() {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError(Throwable e) {
                    progressBar.setVisibility(View.GONE);
                    Log.e(TAG, e.getMessage());
                }

                @Override
                public void onNext(Response<SeriesUpdatedResponse> response) {
                    progressBar.setVisibility(View.GONE);
                    if (response.isSuccessful()) {
                        if (response.body().getData() != null && response.body().getData().size() > 0) {
                            series = response.body().getData();
                            initSeriesRV();
                        } else {
                            title.setVisibility(View.GONE);
                            rv_series.setVisibility(View.GONE);
                            list_empty.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (response.code() == 401) {
                            refreshToken();
                        } else {
                            title.setVisibility(View.GONE);
                            rv_series.setVisibility(View.GONE);
                            list_empty.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });
        }
    }

    /**
     * Method to refresh current token of the user
     */
    private void refreshToken() {
        try {
            if (!GeneralUtils.isConnectInternet(this)) {

                GeneralUtils.showAlertDialog(this,
                        getResources().getString(R.string.title_unavailable_connection),
                        getResources().getString(R.string.mess_unavailable_connection));
                return;
            } else {
                progressBar.setVisibility(View.VISIBLE);
                serviceManager.refreshToken(Session.get().getSessionToken().getToken(), new Subscriber<Response<LoginResponse>>() {
                    @Override
                    public void onCompleted() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(Response<LoginResponse> response) {
                        progressBar.setVisibility(View.GONE);
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                try {
                                    Session.get().setSessionToken(new SessionToken(response.body().getToken()));
                                    initDatas();

                                } catch (ServiceException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                // todo: something
                            }
                        }
                    }
                });
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to initialize recycler view with his data and his custom adapter
     */
    private void initSeriesRV() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_series.setLayoutManager(linearLayoutManager);
        adapter = new LastSeriesUpdatedAdapter(series, this);
        rv_series.setAdapter(adapter);
    }

    private HashMap<String, String> buildHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        try {
            if (Session.get() != null) {
                if (Session.get().getSessionToken().getToken() != null && !TextUtils.isEmpty(Session.get().getSessionToken().getToken())) {
                    headers.put("Authorization", Session.get().getSessionToken().getToken());
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return headers;
    }

    /**
     * Function to get the date from yesterday
     *
     * @return
     */
    private Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * Function to get the date at the start of the day 00:00:00 UTC based
     *
     * @return String time
     */
    private String getFromTime() {
        return String.valueOf(yesterday().getTime() / 1000);
    }

    /**
     * Function to get the date at the moment UTC based
     *
     * @return String time
     */
    private String getToTime() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    @Override
    public String getTitleBarTitle() {
        return getResources().getString(R.string.app_name);
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
