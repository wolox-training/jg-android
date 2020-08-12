package ar.com.wolox.android.example.ui.signup;

import android.content.Context;
import android.content.Intent;

import ar.com.wolox.android.R;
import ar.com.wolox.android.example.ui.home.HomeActivity;
import ar.com.wolox.wolmo.core.activity.WolmoActivity;

/**
 * SignUpActivity class
 * **/
public class SignUpActivity extends WolmoActivity {
    @Override
    protected int layout() {
        return R.layout.activity_base;
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }
}
