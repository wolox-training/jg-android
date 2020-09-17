package ar.com.wolox.android.example.ui.home.news

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.utils.RecyclerViewScrollListener
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject

/**
 * New Fragment class
 */
class NewsFragment @Inject constructor() : WolmoFragment<NewsPresenter>(), NewsView {

    private val newsAdapter: NewsAdapter = NewsAdapter()
    var isRecyclerViewLoading: Boolean = false

    override fun init() {
        with(vRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }
    }

    override fun setListeners() {
        vSwipeRefreshLayout.setOnRefreshListener { presenter.onPullRefresh() }
        vRecyclerView.addOnScrollListener(object : RecyclerViewScrollListener(vRecyclerView.layoutManager as LinearLayoutManager) {

            override fun isLoading(): Boolean {
                return isRecyclerViewLoading
            }

            override fun loadMoreItems() {
                isRecyclerViewLoading = true
                presenter.onScrollList()
            }
        })
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
        val toast = Toast.makeText(requireContext(),
                resources.getString(R.string.login_network_error),
                Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun showNoDataMessage() {
        vNoDataMessage.visibility = View.VISIBLE
    }

    override fun hideNoDataMessage() {
        vNoDataMessage.visibility = View.GONE
    }

    override fun showMoreNews(news: List<News>) {
        isRecyclerViewLoading = false
        newsAdapter.addData(news)
    }

    companion object {
        fun newInstance() = NewsFragment()
    }
}