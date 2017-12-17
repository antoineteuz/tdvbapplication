package com.android.mytdvbapp.mytdvbapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.mytdvbapp.mytdvbapplication.R;
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


    private Credentials mCredentials;
    private Session session;
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

        try {
            session = Session.get();
        } catch (ServiceException e) {
            Log.d(TAG, "Catch when getting session");
        }

        if (session.getSessionToken() != null) {
            if (session.getSessionToken().getToken() != null) {
                launchMainActivity();
            }
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
                    serviceManager.login(mCredentials, new Subscriber<Response<LoginResponse>>() {
                        @Override
                        public void onCompleted() {
                            Log.d(TAG, "login - onCompleted");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(TAG, "login - onError");
                        }

                        @Override
                        public void onNext(Response<LoginResponse> response) {
                            Log.d(TAG, "login - onNext");
                            if (response.isSuccessful()) {
                                if (response.body().getToken() != null && !TextUtils.isEmpty(response.body().getToken())) {
                                    session.setSessionToken(new SessionToken(response.body().getToken()));
                                    launchMainActivity();
                                }
                            }
                        }
                    });
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
