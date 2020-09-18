package ar.com.wolox.android.example.ui.home.news

import ar.com.wolox.android.example.model.News

interface NewsDetailView {
    fun populateFields(userId: Int, news: News?)

    fun getArgumentsData(): News

    fun showNetworkErrorMessage()

    fun disableLikeButton()

    fun enableLikeButton()

    fun showToggleLikeSuccessfully()

    fun stopRefresh()

    fun switchLikeIcon(userId: Int, likes: List<Int>)
}