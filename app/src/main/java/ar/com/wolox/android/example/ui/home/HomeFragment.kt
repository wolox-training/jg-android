package ar.com.wolox.android.example.ui.home

import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.home.news.NewsFragment
import ar.com.wolox.android.example.ui.home.profile.ProfileFragment
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

/**
 * Home Fragment class
 */
class HomeFragment private constructor() : WolmoFragment<HomePresenter>(), IHomeView {
    @Inject
    internal lateinit var newsFragment: NewsFragment

    @Inject
    lateinit var profileFragment: ProfileFragment

    override fun layout(): Int {
        return R.layout.fragment_home
    }

    override fun init() {
        vHomeViewPager.adapter = SimpleFragmentPagerAdapter(childFragmentManager).apply {
            addFragments(
                    newsFragment to NEWS_TAB_TITLE,
                    profileFragment to PROFILE_TAB_TITLE
            )
        }
        with(vTabLayout) {
            setupWithViewPager(vHomeViewPager)
            getTabAt(NEWS_TAB_INDEX)!!.setIcon(R.drawable.news_tab_icon_foreground)
            getTabAt(PROFILE_TAB_INDEX)!!.setIcon(R.drawable.profile_tab_icon_foreground)
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
        private const val NEWS_TAB_INDEX = 0
        private const val NEWS_TAB_TITLE = "NEWS"
        private const val PROFILE_TAB_INDEX = 1
        private const val PROFILE_TAB_TITLE = "PROFILE"
    }
}