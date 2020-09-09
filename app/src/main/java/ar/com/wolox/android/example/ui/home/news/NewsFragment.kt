package ar.com.wolox.android.example.ui.home.news

import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject

/**
 * New Fragment class
 */
class NewsFragment @Inject constructor() : WolmoFragment<NewsPresenter>(), NewsView {

    private val newsAdapter: NewsAdapter = NewsAdapter()

    override fun init() {
        with(vRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            vRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }
    }

    override fun setListeners() {
        vSwipeRefreshLayout.setOnRefreshListener { presenter.onPullRefresh() }
    }

    override fun layout() = R.layout.fragment_news

    override fun stopRefresh() {
        if (vSwipeRefreshLayout.isRefreshing) vSwipeRefreshLayout.isRefreshing = false
    }

    override fun showNewsList(userId: Int, news: List<News>) {
        newsAdapter.newsAdapter(news, userId, requireContext())
        vRecyclerView.adapter = newsAdapter
    }

    override fun showNetworkErrorMessage() {
        val message: CharSequence = requireContext().getString(R.string.login_network_error)
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun showNoDataMessage() {
        TODO("Not yet implemented")
    }

    companion object {
        fun newInstance() = NewsFragment()
    }
}