package ar.com.wolox.android.example.network.repository;

import javax.inject.Inject;

import ar.com.wolox.android.example.network.services.UserService;
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices;

/**
 * User Repositroy class
 * **/
public class UserRepository {
    private RetrofitServices retrofitServices;

    @Inject
    UserRepository(RetrofitServices retrofitServices) {
        this.retrofitServices = retrofitServices;
    }

    public UserService service() {
        return retrofitServices.getService(UserService.class);
    }
}
