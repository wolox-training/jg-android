package ar.com.wolox.android.example.network.services;

import java.util.List;

import ar.com.wolox.android.example.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * User Service interface
 * **/
public interface UserService {
    @GET("users")
    Call<List<User>> getLogin(
            @Query("email") String email,
            @Query("password") String password
    );
}
