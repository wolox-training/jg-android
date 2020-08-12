package ar.com.wolox.android.example.ui.signup;

import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

/**
 * SignUpFragment class
 * **/
public class SignUpFragment extends WolmoFragment<SignUpPresenter> implements ISignUpView {
    @Override
    public void init() {

    }

    @Override
    public int layout() {
        return R.layout.fragment_signup;
    }
}
