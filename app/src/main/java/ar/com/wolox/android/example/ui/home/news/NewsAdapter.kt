package ar.com.wolox.android.example.ui.home.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.utils.CalculateTimeInterval
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_news_item.view.*

/**
 * Recycler Adapter class
 * **/
class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {
    private var news: MutableList<News> = mutableListOf()
    private lateinit var context: Context
    private var userId: Int = 0

    fun newsAdapter(news: List<News>, userId: Int, context: Context) {
        this.news.addAll(news)
        this.context = context
        this.userId = userId
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
    }

    fun addData(newsList: List<News>) {
        val size = itemCount
        this.news.addAll(newsList)
        val sizeNew = this.news.size
        notifyItemRangeChanged(size, sizeNew)
    }

    class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(news: News, userId: Int) {
            with(itemView) {
                vNewsTitle.text = news.title
                vNewsDescription.text = news.text
                vNewsImage.loadUrl(news.picture)
                vNewsTime.text = CalculateTimeInterval.calculate(news.createdAt).toString() + " d"
                vNewsLike.setImageResource(getNewsLikeImage(news.likes, userId))
            }
        }

        private fun String.toHttps(): String = this.replace("http://", "https://")

        private fun ImageView.loadUrl(url: String) {
            Glide.with(context)
                    .load(url.toHttps())
                    .placeholder(R.drawable.login_cover)
                    .into(this)
        }

        private fun getNewsLikeImage(newsLike: List<Int>, userId: Int): Int {
            if (newsLike.contains(userId)) {
                return R.drawable.ic_like_on
            }

            return R.drawable.ic_like_off
        }
    }
}