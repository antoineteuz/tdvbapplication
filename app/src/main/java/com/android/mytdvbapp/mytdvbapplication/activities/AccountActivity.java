package com.android.mytdvbapp.mytdvbapplication.activities;

import android.content.Intent;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.mytdvbapp.mytdvbapplication.R;
import com.android.mytdvbapp.mytdvbapplication.base.AbstractActivity;
import com.android.mytdvbapp.mytdvbapplication.models.Credentials;
import com.android.mytdvbapp.mytdvbapplication.models.Session;
import com.android.mytdvbapp.mytdvbapplication.models.SessionToken;
import com.android.mytdvbapp.mytdvbapplication.models.response.LoginResponse;
import com.android.mytdvbapp.mytdvbapplication.models.response.UserResponse;
import com.android.mytdvbapp.mytdvbapplication.network.ServiceException;
import com.android.mytdvbapp.mytdvbapplication.network.ServiceManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;
import rx.Subscriber;

public class AccountActivity extends AbstractActivity {

    private static String TAG = "AccountActivity";

    @BindView(R.id.tv_hello)
    TextView tv_hello;

    @BindView(R.id.tv_name)
    TextView tv_name;

    @BindView(R.id.tv_language)
    TextView tv_language;

    @BindView(R.id.tv_favorites_display_mode)
    TextView tv_favorites_display_mode;

    @BindView(R.id.btn_deconnexion)
    RelativeLayout btn_deconnexion;

    private ServiceManager serviceManager;

    private String hello = "Hello ", username = "", langugage = "", displaymode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        //
        ButterKnife.bind(this);
        //
        serviceManager = new ServiceManager();
        initDatas();
        //
        initListeners();
    }

    private void initDatas() {
        try {
            serviceManager.getUser(Session.get().getSessionToken().getToken(), new Subscriber<Response<UserResponse>>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onNext(Response<UserResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getData() != null) {
                            if (response.body().getData().getUserName() != null && !TextUtils.isEmpty(response.body().getData().getUserName())) {
                                hello += response.body().getData().getUserName();
                                username = response.body().getData().getUserName();
                            } else {
                                tv_hello.setVisibility(View.GONE);
                            }

                            if (response.body().getData().getLanguage() != null && !TextUtils.isEmpty(response.body().getData().getLanguage())) {
                                langugage += response.body().getData().getLanguage();
                            } else {
                                tv_language.setVisibility(View.GONE);
                            }

                            if (response.body().getData().getFavoritesDisplaymode() != null && !TextUtils.isEmpty(response.body().getData().getFavoritesDisplaymode())) {
                                displaymode += response.body().getData().getFavoritesDisplaymode();
                            } else {
                                tv_favorites_display_mode.setVisibility(View.GONE);
                            }

                            initViews();
                        }
                    } else {
                        if (response.code() == 401) {
                            // TODO : Need to refresh token
                            refreshToken();
                        }
                    }
                }
            });
        } catch (ServiceException e) {
            e.printStackTrace();
            // TODO : show toast or dialog
        }
    }

    /**
     * Method to refresh current token of the user
     */
    private void refreshToken() {
        try {
            serviceManager.refreshToken(Session.get().getSessionToken().getToken(), new Subscriber<Response<LoginResponse>>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onNext(Response<LoginResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            try {
                                Session.get().setSessionToken(new SessionToken(response.body().getToken()));

                                // Then recall initDatas with good token
                                initDatas();

                            } catch (ServiceException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function to initialize the different views present in the activity
     */
    private void initViews() {
        tv_hello.setText(hello);
        tv_name.setText(username);
        tv_language.setText(langugage);
        tv_favorites_display_mode.setText(displaymode);
    }

    /**
     * Method to initialize the different listeners present in the activity
     */
    private void initListeners() {
        btn_deconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo : dialog Are you sure to disconnect ?
                try {
                    Session.get().reset();
                    closeAllactivities();

                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void closeAllactivities() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    @Override
    public String getTitleBarTitle() {
        return getResources().getString(R.string.account_title);
    }
}
