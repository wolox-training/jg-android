package ar.com.wolox.android.example.ui.login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import ar.com.wolox.android.example.utils.UserSession;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;

/**
 * Class LoginPresenter
 **/
public class LoginPresenter extends BasePresenter<ILoginView> {
    @Inject
    public UserSession userSession;

    @Inject
    public LoginPresenter(UserSession userSession) {
        this.userSession = userSession;
    }

    public void onLoginButtonClicked(String email, String password) {
        if (existFieldErrors(email, password)) {
            return;
        }

        userSession.setEmail(email);
        getView().goToHome();
    }

    /**
     * Check if exists error fields
     *
     * @param email    type EditText
     * @param password type EditText
     * @return boolean
     **/
    protected boolean existFieldErrors(String email, String password) {
        boolean error = false;

        if (password.isEmpty()) {
            getView().showPasswordEmptyError();
            error = true;
        }

        if (email.isEmpty()) {
            getView().showEmailEmptyError();
            error = true;
        } else {
            if (!isValidFormatEmail(email)) {
                getView().showEmailFormatError();
                error = true;
            }
        }

        return error;
    }

    /**
     * Check format email
     *
     * @param email type String
     * @return boolean
     **/
    protected boolean isValidFormatEmail(String email) {
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
}
