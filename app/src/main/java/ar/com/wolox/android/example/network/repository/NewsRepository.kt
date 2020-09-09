package ar.com.wolox.android.example.network.repository

import ar.com.wolox.android.example.network.services.NewsService
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import javax.inject.Inject

/**
 * User Repositroy class
 */
class NewsRepository @Inject internal constructor(private val retrofitServices: RetrofitServices) {
    fun service(): NewsService {
        return retrofitServices.getService(NewsService::class.java)
    }
}