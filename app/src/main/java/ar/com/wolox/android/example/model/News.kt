package ar.com.wolox.android.example.model

import com.google.gson.annotations.SerializedName

/**
 * News entity class
 * **/
data class News(
    @SerializedName("createdAt")
    val createdAt: String?,
    val id: Int?,
    val title: String?,
    val picture: String?,
    val text: String?,
    var likes: MutableList<Int>?
)
