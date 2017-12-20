package com.android.mytdvbapp.mytdvbapplication.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.mytdvbapp.mytdvbapplication.LastSeriesUpdatedAdapter;
import com.android.mytdvbapp.mytdvbapplication.R;
import com.android.mytdvbapp.mytdvbapplication.base.AbstractActivity;
import com.android.mytdvbapp.mytdvbapplication.models.SeriesInfo;
import com.android.mytdvbapp.mytdvbapplication.models.Session;
import com.android.mytdvbapp.mytdvbapplication.models.response.SeriesUpdatedResponse;
import com.android.mytdvbapp.mytdvbapplication.network.ServiceException;
import com.android.mytdvbapp.mytdvbapplication.network.ServiceManager;

import org.joda.time.DateTime;

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

    @BindView(R.id.tv_list_empty)
    TextView list_empty;

    private ServiceManager serviceManager;
    private Session session;

    private List<SeriesInfo> series;
    private LastSeriesUpdatedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        ButterKnife.bind(this);
        //
        initDatas();
        //
        //initListeners();

    }

    private void initDatas() {
        serviceManager = new ServiceManager();

        try {
            session = Session.get();
        } catch (ServiceException e) {
            Log.d(TAG, "Catch when getting session");
        }

        //String fromTime = String.valueOf(getFromTime());
        // TODO : Valeurs pour tester -> de 23H59 à 00H00 pas de séries update
        String fromTime = "1513382455";
        String toTime = String.valueOf(getToTime());

        // TODO : launch progress dialog

        progressDialog.show();
        serviceManager.getLastSeriesUpdated(buildHeaders(), fromTime, toTime, new Subscriber<Response<SeriesUpdatedResponse>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "last series updated - onCompleted");
                progressDialog.dismiss();
            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                //TODO : Gérer erreur
            }

            @Override
            public void onNext(Response<SeriesUpdatedResponse> response) {
                progressDialog.dismiss();
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
                    title.setVisibility(View.GONE);
                    rv_series.setVisibility(View.GONE);
                    list_empty.setVisibility(View.VISIBLE);
                }
            }
        });

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
        if (session != null) {
            if (session.getSessionToken().getToken() != null && !TextUtils.isEmpty(session.getSessionToken().getToken())) {
                headers.put("Authorization", session.getSessionToken().getToken());
            }
        }
        return headers;
    }

    /**
     * Function to get the date at the start of the day 00:00:00 UTC based
     *
     * @return DateTime format
     */
    private Long getFromTime() {
        return new DateTime().withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).getMillis();
    }

    /**
     * Function to get the date at the moment UTC based
     *
     * @return DateTime format
     */
    private Long getToTime() {
        return new DateTime().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).getMillis();
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
