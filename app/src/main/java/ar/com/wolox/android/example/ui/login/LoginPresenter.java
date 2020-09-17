package ar.com.wolox.android.example.ui.login;

import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import ar.com.wolox.android.example.model.User;
import ar.com.wolox.android.example.network.repository.UserRepository;
import ar.com.wolox.android.example.utils.UserSession;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class LoginPresenter
 **/
public class LoginPresenter extends BasePresenter<LoginView> {
    @Inject
    public UserSession userSession;

    @Inject
    public UserRepository userRepository;

    @Inject
    public LoginPresenter(UserSession userSession, UserRepository userRepository) {
        this.userSession = userSession;
        this.userRepository = userRepository;
    }

    public void onLoginButtonClicked(String email, String password) {
        if (existFieldErrors(email, password)) {
            return;
        }

        getView().showProgressBar();
        callUserService(email, password);
        getView().hideProgressBar();
    }

    private void callUserService(String email, String password) {
        Call<List<User>> call = userRepository.service().getLogin(email, password);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NotNull Call<List<User>> call, @NotNull Response<List<User>> response) {
                if (response.isSuccessful()) {
                    if (response.body().isEmpty()) {
                        getView().showInvalidCredentialsMessage();
                    } else {
                        userSession.setEmail(email);
                        userSession.setUserid(response.body().get(0).getId());
                        getView().goToHome();
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<User>> call, @NotNull Throwable t) {
                getView().showNetworkErrorMessage();
            }
        });
    }

    public void onSignUpButtonClicked() {
        getView().goToSignUp();
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
