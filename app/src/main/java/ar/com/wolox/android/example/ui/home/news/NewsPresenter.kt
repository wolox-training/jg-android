package ar.com.wolox.android.example.ui.home.news

import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.network.repository.NewsRepository
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * News Presenter class
 * **/
class NewsPresenter @Inject constructor(private val userSession: UserSession, private val newsRepository: NewsRepository) : BasePresenter<NewsView>() {

    override fun onViewAttached() {
        getNews()
    }

    fun onPullRefresh() {
        view?.hideNoDataMessage()
        getNews()
        view?.stopRefresh()
    }

    private fun getNews() {
        val call: Call<List<News>> = newsRepository.service().getNews()
        call.enqueue(object : Callback<List<News>> {
            override fun onFailure(call: Call<List<News>>, t: Throwable) {
                view?.showNetworkErrorMessage()
            }

            override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                if (response.body()!!.isEmpty()) {
                    view?.showNoDataMessage()
                } else {
                    var newsList: List<News> = response.body()!!
                    newsList = newsList.plus(newsRepository.news[0])
                    newsList = newsList.plus(newsRepository.news[1])
                    newsList = newsList.plus(newsRepository.news[2])
                    view?.showNewsList(userSession.userid, newsList)
                }
            }
        })
    }

    fun onScrollList() {
        view?.showMoreNews(newsRepository.news)
    }
}