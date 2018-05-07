package com.kev.karchitect.utils.images

import android.content.Context
import android.widget.ImageView

class ImageLoader private constructor() {

    companion object {

        fun load(context: Context, url: String?, imageView: ImageView?) {
            if (url != null && imageView != null) {
                GlideApp.with(context)
                        .load(url)
                        .into(imageView)
            }
        }
    }
}