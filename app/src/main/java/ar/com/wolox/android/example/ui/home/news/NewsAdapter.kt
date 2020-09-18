package ar.com.wolox.android.example.ui.home.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.utils.CalculateTimeInterval
import ar.com.wolox.android.example.utils.ImageUtils
import kotlinx.android.synthetic.main.fragment_news_item.view.*

/**
 * Recycler Adapter class
 * **/
class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {
    private var news: MutableList<News> = mutableListOf()
    private var userId: Int = 0
    private lateinit var newsFragment: NewsFragment

    fun newsAdapter(news: List<News>, userId: Int, newsFragment: NewsFragment) {
        this.news.addAll(news)
        this.userId = userId
        this.newsFragment = newsFragment
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NewsHolder(layoutInflater.inflate(R.layout.fragment_news_item, parent, false))
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(newsHolder: NewsHolder, position: Int) {
        val item = news[position]
        newsHolder.bindItems(item, userId)
        newsHolder.itemView.setOnClickListener {
            this.newsFragment.onClickListener(item)
        }
    }

    fun addData(newsList: List<News>) {
        val size = itemCount
        this.news.addAll(newsList)
        val sizeNew = this.news.size
        notifyItemRangeChanged(size, sizeNew)
    }

    fun clearData() {
        val size: Int = this.news.size
        this.news.clear()
        notifyItemRangeRemoved(0, size)
    }

    class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(news: News, userId: Int) {
            with(itemView) {
                vNewsTitle.text = news.title
                vNewsDescription.text = news.text
                news.picture?.let { ImageUtils.loadImage(vNewsImage, it, R.drawable.login_cover, context) }
                vNewsTime.text = CalculateTimeInterval.calculate(news.createdAt!!).toString() + " d"
                vNewsLike.isSelected = news.likes!!.contains(userId)
            }
        }
    }
}