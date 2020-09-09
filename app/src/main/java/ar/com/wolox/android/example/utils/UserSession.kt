package ar.com.wolox.android.example.utils

import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope
import ar.com.wolox.wolmo.core.util.SharedPreferencesManager

import javax.inject.Inject

@ApplicationScope
class UserSession @Inject constructor(private val sharedPreferencesManager: SharedPreferencesManager) {

    // Really, we don't need to query the username because this instance live as long as the
    // application, but we should add a check in case Android decides to kill the application
    // and return to a state where this isn't initialized.
    var username: String? = null
        get() = field ?: sharedPreferencesManager[Extras.UserLogin.USERNAME, null].also {
            field = it
        }
        set(username) {
            field = username
            sharedPreferencesManager.store(Extras.UserLogin.USERNAME, username)
        }

    var email: String? = null
        get() = sharedPreferencesManager[Extras.UserLogin.USEREMAIL, null]

        set(email) {
            field = email
            sharedPreferencesManager.store(Extras.UserLogin.USEREMAIL, email)
        }

    var userid: Int = 0
        get() = sharedPreferencesManager[Extras.UserLogin.USERID, 0]

        set(userid) {
            field = userid
            sharedPreferencesManager.store(Extras.UserLogin.USERID, userid)
        }

    val isUserLogged
            get() = email != null
}
