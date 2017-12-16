package com.android.mytdvbapp.mytdvbapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.mytdvbapp.mytdvbapplication.R;
import com.android.mytdvbapp.mytdvbapplication.models.Credentials;
import com.android.mytdvbapp.mytdvbapplication.models.Session;
import com.android.mytdvbapp.mytdvbapplication.models.SessionToken;
import com.android.mytdvbapp.mytdvbapplication.models.response.LoginResponse;
import com.android.mytdvbapp.mytdvbapplication.network.ServiceException;
import com.android.mytdvbapp.mytdvbapplication.network.auth.AuthService;

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


    private AuthService mAuthSerice;
    private Credentials mCredentials;
    private Session session;

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
        mAuthSerice = new AuthService();

        try {
            session = Session.get();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if (session.getSessionToken() != null) {
            if (session.getSessionToken().validateToken()) {
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
                    mAuthSerice.login(mCredentials, new Subscriber<Response<LoginResponse>>() {
                        @Override
                        public void onCompleted() {
                            Log.d(TAG, "auth service onCompleted");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(TAG, "auth service onError");
                            Toast.makeText(view.getContext(), "Could not authenticate you, check your credentials !", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onNext(Response<LoginResponse> response) {
                            Log.d(TAG, "auth service onNext");
                            if (response.isSuccessful()) {
                                session.setSessionToken(new SessionToken(response.body().getToken()));
                                if (session.getSessionToken() != null) {
                                    if (session.getSessionToken().validateToken()) {
                                        launchMainActivity();
                                    }
                                } else {
                                    Toast.makeText(view.getContext(), "Could not authenticate you, check your credentials !", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    });
                }
            }
        });
    }

    private boolean verifyEditText(EditText editText) {
        return editText != null && !TextUtils.isEmpty(editText.getText().toString());
    }

}
