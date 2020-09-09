package ar.com.wolox.android.example.model

import com.google.gson.annotations.SerializedName

/**
 * News entity class
 * **/
data class News(
    @SerializedName("createdAt")
val createdAt: String,
    val title: String,
    val picture: String,
    val text: String,
    val likes: List<Int>
)
