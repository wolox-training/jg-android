package ar.com.wolox.android.example.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageUtils {
    companion object {
        private fun urlToHttps(url: String): String = url.replace("http://", "https://")

        fun loadImage(imageView: ImageView, url: String, resourceId: Int, context: Context) {
            Glide.with(context)
                    .load(urlToHttps(url))
                    .centerCrop()
                    .placeholder(resourceId)
                    .into(imageView)
        }
    }
}