package ar.com.wolox.android.example.ui.home.news

import ar.com.wolox.android.example.model.News

interface NewsView {
    fun showNewsList(userId: Int, news: List<News>)

    fun showNetworkErrorMessage()

    fun showNoDataMessage()

    fun stopRefresh()
}