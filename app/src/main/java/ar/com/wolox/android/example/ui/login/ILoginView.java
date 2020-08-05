package ar.com.wolox.android.example.ui.login;

/**
 * ILoginView interface
 **/
public interface ILoginView {

    void openBrowser(String url);

    void showEmailEmptyError();

    void showPasswordEmptyError();

    void showEmailFormatError();

    void goToHome();
}
