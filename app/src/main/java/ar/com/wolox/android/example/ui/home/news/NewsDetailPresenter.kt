package ar.com.wolox.android.example.ui.home.news

import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.network.repository.NewsRepository
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/*
* NewsDetailPresenter class
* */
class NewsDetailPresenter @Inject constructor(private val newsRepository: NewsRepository, private val userSession: UserSession) : BasePresenter<NewsDetailView>() {
    private var news: News? = null

    override fun onViewAttached() {
        news = view?.getArgumentsData()
        view?.populateFields(userSession.userid, news)
    }

    fun onPullRefresh() {
        val call: Call<List<News>> = newsRepository.service().getNewsById(news?.id!!)
        call.enqueue(object : Callback<List<News>> {
            override fun onFailure(call: Call<List<News>>, t: Throwable) {
                view?.showNetworkErrorMessage()
                view?.stopRefresh()
            }

            override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                news = response.body()!![0]
                view?.populateFields(userSession.userid, news)
                view?.stopRefresh()
            }
        })
    }

    fun toggleNewsLike() {
        view?.disableLikeButton()
        news?.likes?.remove(userSession.userid)

        val call: Call<News> = newsRepository.service().updateNewSelected(news?.id!!, news!!)
        call.enqueue(object : Callback<News> {
            override fun onFailure(call: Call<News>, t: Throwable) {
                view?.showNetworkErrorMessage()
                view?.enableLikeButton()
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                news!!.likes = response.body()!!.likes
                view?.switchLikeIcon(userSession.userid, news!!.likes!!)
                view?.enableLikeButton()
                view?.showToggleLikeSuccessfully()
            }
        })
    }
}