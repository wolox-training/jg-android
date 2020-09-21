package ar.com.wolox.android.example.ui.home.news

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
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
class NewsFragment @Inject constructor() : WolmoFragment<NewsPresenter>(), NewsView, NewsOnClickListener {

    private val newsAdapter: NewsAdapter = NewsAdapter()
    var isRecyclerViewLoading: Boolean = false

    override fun init() {
        newsAdapter.clearData()
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
        newsAdapter.newsAdapter(news, userId, this)
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

    override fun clearNewsList() {
        newsAdapter.clearData()
    }

    override fun onClickListener(news: News) {
        val dataToSend = Bundle()
        val newsDetailFragment: NewsDetailFragment = NewsDetailFragment.newInstance()
        val fragmentTransaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()

        dataToSend.putInt("id", news.id!!)
        dataToSend.putString("title", news.title)
        dataToSend.putString("text", news.text)
        dataToSend.putString("picture", news.picture)
        dataToSend.putString("createdAt", news.createdAt)
        dataToSend.putIntArray("likes", news.likes?.toIntArray())
        newsDetailFragment.arguments = dataToSend
        fragmentTransaction.replace(R.id.vActivityBaseContent, newsDetailFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    companion object {
        fun newInstance() = NewsFragment()
    }
}