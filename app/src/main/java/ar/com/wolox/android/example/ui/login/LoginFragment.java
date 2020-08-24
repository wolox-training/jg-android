package ar.com.wolox.android.example.ui.login;

import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import ar.com.wolox.android.R;
import ar.com.wolox.android.example.ui.home.HomeActivity;
import ar.com.wolox.android.example.ui.signup.SignUpActivity;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

/**
 * Class Login Fragment
 */
public class LoginFragment extends WolmoFragment<LoginPresenter> implements ILoginView {

    private Button loginButton;
    private Button signUpButton;
    private EditText email;
    private EditText password;
    private TextView termsAndConditionsLink;
    private ProgressBar progressBar;

    @Override
    public void init() {
        loginButton = (Button) getView().findViewById(R.id.vLoginButton);
        signUpButton = (Button) getView().findViewById(R.id.vSignUpButton);
        email = (EditText) getView().findViewById(R.id.vLoginEmail);
        password = (EditText) getView().findViewById(R.id.vLoginPassword);
        termsAndConditionsLink = (TextView) getView().findViewById(R.id.vLoginTermsAndConditionsLink);
        termsAndConditionsLink.setMovementMethod(LinkMovementMethod.getInstance());
        progressBar = (ProgressBar) getView().findViewById(R.id.vProgressBar);
    }

    @Override
    public int layout() {
        return R.layout.fragment_login;
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
    public void goToSignUp() {
        SignUpActivity.start(requireContext());
    }

    @Override
    public void showInvalidCredentialsMessage() {
        CharSequence message = "Invalid email or password";
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void showNetworkErrorMessage() {
        CharSequence message = "Network error, please try again later";
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setListeners() {
        loginButton.setOnClickListener((view) -> getPresenter().onLoginButtonClicked(
                email.getText().toString().trim(), password.getText().toString().trim()));
        signUpButton.setOnClickListener((view -> getPresenter().onSignUpButtonClicked()));
    }
}
