package ar.com.wolox.android.example.ui.home

import android.content.Context
import android.content.Intent
import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity

/**
 * HomeActivity class
 */
class HomeActivity : WolmoActivity() {
    override fun init() {
        replaceFragment(R.id.vActivityBaseContent, HomeFragment.newInstance())
    }

    override fun layout(): Int = R.layout.activity_base

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
        }
    }
}