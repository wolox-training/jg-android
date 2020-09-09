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
        getNews()
        view?.stopRefresh()
    }

    /*fun getNews(): ArrayList<News> {
        val news: ArrayList<News> = ArrayList()
        val likesList: List<Int> = listOf(1, 5)
        val emptylikesList: List<Int> = emptyList()

        news.add(News("2020-07-18T14:00:29.985Z",
                "¿Famosos y sólo amigos?",
                "https://bucket1.glanacion.com/anexos/fotos/70/dia-del-amigo-2236070w620.jpg",
                "Ser súper estrellas e íntimos amigos tiene sus desventajas, al menos para " +
                        "George. Su esposa, Amal, es muy celosa de Julia e irrumpió varias veces " +
                        "en las grabaciones de su última peli juntos, aunque nunca pescó nada raro.",
                likesList))
        news.add(News("2020-08-18T14:01:38.673Z",
                "Hipnosis: la nueva vedette de las neurociencias",
                "https://bucket1.glanacion.com/anexos/fotos/50/2082050.jpg",
                "Ser súper estrellas e íntimos amigos tiene sus desventajas, al menos para " +
                        "George. Su esposa, Amal, es muy celosa de Julia e irrumpió varias veces en " +
                        "las grabaciones de su última peli juntos, aunque nunca pescó nada raro.",
                likesList))
        news.add(News("2020-09-05T13:53:13.735Z",
                "Como cuidar los muebles de cuero",
                "https://bucket3.glanacion.com/anexos/fotos/28/soluciones-2231528w620.jpg",
                "Ser súper estrellas e íntimos amigos tiene sus desventajas, al menos para " +
                        "George. Su esposa, Amal, es muy celosa de Julia e irrumpió varias veces en " +
                        "las grabaciones de su última peli juntos, aunque nunca pescó nada raro.",
                emptylikesList))

        return news
    }*/

    private fun getNews() {
        val call: Call<List<News>> = newsRepository.service().getNews()
        call.enqueue(object : Callback<List<News>> {
            override fun onFailure(call: Call<List<News>>, t: Throwable) {
                view?.showNetworkErrorMessage()
            }

            override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                if (response.body()!!.isEmpty()) {
                    // view?.showNoDataMessage()
                } else {
                    view?.showNewsList(userSession.userid, response.body()!!)
                }
            }
        })
    }
}