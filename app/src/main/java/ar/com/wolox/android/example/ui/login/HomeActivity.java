package ar.com.wolox.android.example.ui.login;

import android.content.Context;
import android.content.Intent;

import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.activity.WolmoActivity;

/**
 * HomeActivity class
 **/
public class HomeActivity extends WolmoActivity {

    @Override
    protected void init() {}

    @Override
    protected int layout() {
        return R.layout.activity_home;
    }

    static void start(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }
}
