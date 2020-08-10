package ar.com.wolox.android.example.ui.login;

import android.content.Intent;

import javax.inject.Inject;

import ar.com.wolox.android.example.utils.UserSession;
import ar.com.wolox.wolmo.core.activity.WolmoActivity;
import ar.com.wolox.android.R;
/**
 * WelcomeActivity class
 * **/
public class WelcomeActivity extends WolmoActivity {
    @Inject
    public UserSession userSession;

    @Override
    protected int layout() {
        return R.layout.activity_base;
    }

    @Override
    protected void init() {
        Intent intent;

        if (userSession.isUserLogged()) {
            intent = new Intent(this, HomeActivity.class);
        } else {
            intent = new Intent(this, LoginActivity.class);
        }

        startActivity(intent);
    }
}
