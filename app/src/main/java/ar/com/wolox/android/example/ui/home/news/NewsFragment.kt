package ar.com.wolox.android.example.ui.home.news

import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import javax.inject.Inject

/**
 * New Fragment class
 */
class NewsFragment @Inject constructor() : WolmoFragment<NewsPresenter>() {

    override fun init() {}

    override fun layout() = R.layout.fragment_news

    companion object {
        fun newInstance() = NewsFragment()
    }
}