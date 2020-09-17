package ar.com.wolox.android.example.ui.login;

/**
 * LoginView interface
 **/
public interface LoginView {

    void showEmailEmptyError();

    void showPasswordEmptyError();

    void showEmailFormatError();

    void goToHome();

    void goToSignUp();

    void showInvalidCredentialsMessage();

    void showNetworkErrorMessage();

    void showProgressBar();

    void hideProgressBar();
}
