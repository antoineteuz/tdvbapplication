package com.android.mytdvbapp.mytdvbapplication.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.android.mytdvbapp.mytdvbapplication.R;
import com.android.mytdvbapp.mytdvbapplication.base.AbstractActivity;
import com.android.mytdvbapp.mytdvbapplication.models.Session;
import com.android.mytdvbapp.mytdvbapplication.models.response.SerieDetailedResponse;
import com.android.mytdvbapp.mytdvbapplication.network.ServiceException;
import com.android.mytdvbapp.mytdvbapplication.network.ServiceManager;

import java.util.HashMap;

import retrofit2.Response;
import rx.Subscriber;

import static com.android.mytdvbapp.mytdvbapplication.helper.Constants.SERIE_NUMBER;

public class SerieDetailedActivity extends AbstractActivity {

    private static String TAG = "SerieDetailedActivity";

    private SerieDetailedResponse serie;
    private ServiceManager serviceManager;
    private Bundle args;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie_detailed);
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
                            serie = response.body();
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
