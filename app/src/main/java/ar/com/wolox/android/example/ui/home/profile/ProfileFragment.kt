package ar.com.wolox.android.example.ui.home.profile

import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import javax.inject.Inject

/**
 * Profile Fragment class
 */
class ProfileFragment @Inject constructor() : WolmoFragment<ProfilePresenter>() {

    override fun init() {}

    override fun layout() = R.layout.fragment_profile

    companion object {
        fun newInstance() = ProfileFragment()
    }
}