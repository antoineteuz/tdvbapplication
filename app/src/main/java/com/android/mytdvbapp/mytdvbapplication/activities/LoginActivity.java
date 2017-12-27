package com.android.mytdvbapp.mytdvbapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.mytdvbapp.mytdvbapplication.R;
import com.android.mytdvbapp.mytdvbapplication.helper.GeneralUtils;
import com.android.mytdvbapp.mytdvbapplication.models.Credentials;
import com.android.mytdvbapp.mytdvbapplication.models.Session;
import com.android.mytdvbapp.mytdvbapplication.models.SessionToken;
import com.android.mytdvbapp.mytdvbapplication.models.response.LoginResponse;
import com.android.mytdvbapp.mytdvbapplication.network.ServiceException;
import com.android.mytdvbapp.mytdvbapplication.network.ServiceManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;
import rx.Subscriber;

public class LoginActivity extends AppCompatActivity {

    private static String TAG = "LoginActivity";

    @BindView(R.id.api_key)
    EditText mApikey;

    @BindView(R.id.userkey)
    EditText mUserkey;

    @BindView(R.id.username)
    EditText mUsername;

    @BindView(R.id.btn_connexion)
    Button mBtnConnexion;

    @BindView(R.id.progress)
    ProgressBar progress;

    private Credentials mCredentials;
    private ServiceManager serviceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //
        ButterKnife.bind(this);
        //
        initDatas();
        //
        initListeners();
    }

    private void initDatas() {
        serviceManager = new ServiceManager();

        // First, we check if we already have a valid token save into the phone
        try {
            if (Session.get().getSessionToken() != null) {
                if (Session.get().getSessionToken().getToken() != null) {
                    // If it's this case, we launch the main activity
                    launchMainActivity();
                }
            }
            // Else, user needs to connect himself
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }

    private void launchMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Method to initialize the different listeners in this activity
     */
    private void initListeners() {
        mBtnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (verifyEditText(mApikey)) {
                    mCredentials = new Credentials(mApikey.getText().toString(), mUsername.getText().toString(), mUserkey.getText().toString());
                    if (!GeneralUtils.isConnectInternet(view.getContext())) {

                        GeneralUtils.showAlertDialog(view.getContext(),
                                getResources().getString(R.string.title_unavailable_connection),
                                getResources().getString(R.string.mess_unavailable_connection));
                        return;
                    } else {
                        progress.setVisibility(View.VISIBLE);
                        serviceManager.login(mCredentials, new Subscriber<Response<LoginResponse>>() {
                            @Override
                            public void onCompleted() {
                                progress.setVisibility(View.GONE);
                                Log.d(TAG, "login - onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                progress.setVisibility(View.GONE);
                                Log.d(TAG, "login - onError");
                            }

                            @Override
                            public void onNext(Response<LoginResponse> response) {
                                //progressDialog.dismiss();
                                progress.setVisibility(View.GONE);
                                Log.d(TAG, "login - onNext");
                                if (response.isSuccessful()) {
                                    if (response.body().getToken() != null && !TextUtils.isEmpty(response.body().getToken())) {
                                        try {
                                            Session.get().setSessionToken(new SessionToken(response.body().getToken()));
                                            Session.get().setApi_key(mApikey.getText().toString());
                                        } catch (ServiceException e) {
                                            e.printStackTrace();
                                        }
                                        launchMainActivity();
                                    }
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    /**
     * Function to test if the text inside the edit text is ok
     *
     * @param editText
     * @return true if it's ok, false if it's null or empty
     */
    private boolean verifyEditText(EditText editText) {
        return editText != null && !TextUtils.isEmpty(editText.getText().toString());
    }
}
