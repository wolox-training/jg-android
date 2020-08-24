package ar.com.wolox.android.example.model;

import com.google.gson.annotations.SerializedName;

/**
 * User model class
 * **/
public class User {
    @SerializedName("id")
    private Integer id;

    @SerializedName("username")
    private String userName;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public User(Integer id, String userName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
