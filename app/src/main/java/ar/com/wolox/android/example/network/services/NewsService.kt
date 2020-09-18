package ar.com.wolox.android.example.network.services

import ar.com.wolox.android.example.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Body
import retrofit2.http.Query

/**
 * News Service interface
 */
interface NewsService {
    @GET("news")
    fun getNews(): Call<List<News>>

    @PUT("news/{id}")
    fun updateNewSelected(
        @Path("id") id: Int,
        @Body news: News
    ): Call<News>

    @GET("news")
    fun getNewsById(
        @Query("id") id: Int
    ): Call<List<News>>
}