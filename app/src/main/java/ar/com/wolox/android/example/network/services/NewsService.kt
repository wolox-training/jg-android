package ar.com.wolox.android.example.network.services

import ar.com.wolox.android.example.model.News
import retrofit2.Call
import retrofit2.http.GET

/**
 * News Service interface
 */
interface NewsService {
    @GET("news")
    fun getNews(): Call<List<News>>
}