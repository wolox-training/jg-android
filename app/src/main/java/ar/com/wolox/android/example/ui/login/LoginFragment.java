package ar.com.wolox.android.example.ui.login;

import android.widget.Button;
import android.widget.EditText;

import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

/**
 * Class Login Fragment
 */
public class LoginFragment extends WolmoFragment<LoginPresenter> implements ILoginView {

    private Button loginButton;
    private EditText email;
    private EditText password;

    @Override
    public void init() {
        loginButton = (Button) getView().findViewById(R.id.vLoginButton);
        email = (EditText) getView().findViewById(R.id.vLoginEmail);
        password = (EditText) getView().findViewById(R.id.vLoginPassword);
    }

    @Override
    public int layout() {
        return R.layout.fragment_login;
    }

    @Override
    public void openBrowser(String url) {
    }

    @Override
    public void showEmailEmptyError() {
        email.setError(requireContext().getString(R.string.login_email_empty));
    }

    @Override
    public void showPasswordEmptyError() {
        password.setError(requireContext().getString(R.string.login_password_empty));
    }

    @Override
    public void showEmailFormatError() {
        email.setError(requireContext().getString(R.string.login_email_format));
    }

    @Override
    public void goToHome() {
        HomeActivity.start(requireContext());
    }

    @Override
    public void setListeners() {
        loginButton.setOnClickListener((view) -> getPresenter().onLoginButtonClicked(
                email.getText().toString().trim(), password.getText().toString().trim()));
    }
}
