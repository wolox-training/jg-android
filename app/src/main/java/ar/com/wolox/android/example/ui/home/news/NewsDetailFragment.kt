package ar.com.wolox.android.example.ui.home.news

import android.widget.Toast
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.utils.CalculateTimeInterval
import ar.com.wolox.android.example.utils.ImageUtils
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_news_detail.*
import kotlinx.android.synthetic.main.fragment_news_detail.vSwipeRefreshLayout

/*
* NewsDetailFragment
* */
class NewsDetailFragment @Inject constructor() : WolmoFragment<NewsDetailPresenter>(), NewsDetailView {

    private var isProcessingLike: Boolean = false

    override fun init() {}

    override fun layout() = R.layout.fragment_news_detail

    override fun populateFields(userId: Int, news: News?) {
        vNewsTitle.text = news?.title
        vNewsText.text = news?.text
        ImageUtils.loadImage(vNewsImage, news?.picture!!, R.drawable.login_cover, context!!)
        vNewsTime.text = CalculateTimeInterval.calculate(news.createdAt!!).toString() + " d"
        vNewsLike.isSelected = news.likes?.contains(userId)!!
    }

    override fun getArgumentsData(): News {
        return News(arguments?.getString("createdAt"),
                arguments?.getInt("id"),
                arguments?.getString("title"),
        arguments?.getString("picture"),
        arguments?.getString("text"),
        arguments?.getIntArray("likes")?.toMutableList())
    }

    override fun showNetworkErrorMessage() {
        val toast = Toast.makeText(requireContext(),
                resources.getString(R.string.login_network_error),
                Toast.LENGTH_LONG)
        toast.show()
    }

    override fun disableLikeButton() {
        this.isProcessingLike = true
    }

    override fun enableLikeButton() {
        this.isProcessingLike = false
    }

    override fun showToggleLikeSuccessfully() {
        val toast = Toast.makeText(requireContext(),
                resources.getString(R.string.news_toggle_like_successfully),
                Toast.LENGTH_LONG)
        toast.show()
    }

    override fun stopRefresh() {
        if (vSwipeRefreshLayout.isRefreshing) vSwipeRefreshLayout.isRefreshing = false
    }

    override fun switchLikeIcon(userId: Int, likes: List<Int>) {
        vNewsLike.isSelected = likes.contains(userId)
    }

    override fun setListeners() {
        vBack.setOnClickListener { requireActivity().onBackPressed() }
        vSwipeRefreshLayout.setOnRefreshListener { presenter.onPullRefresh() }
        vNewsLike.setOnClickListener { if (!this.isProcessingLike) presenter.toggleNewsLike() }
    }

    companion object {
        fun newInstance() = NewsDetailFragment()
    }
}