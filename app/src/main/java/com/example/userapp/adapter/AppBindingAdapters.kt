package com.example.userapp.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.userapp.R
import com.example.userapp.core.binding.imageURL
import com.example.userapp.core.data.dto.user.GithubUser
import com.example.userapp.core.extensions.safeLet

object AppBindingAdapters {

    @BindingAdapter(value = ["setUserListItemFavoriteImage"], requireAll = false )
    @JvmStatic
    fun setUserListItemFavoriteImage(view: ImageView?, data: GithubUser?) {
        safeLet(view, data) { imageView, githubUser ->
            if (githubUser.isFavorite == true) {
                imageView.imageURL(R.drawable.favorite_icon)
            } else {
                imageView.imageURL(R.drawable.unfavorite_icon)
            }
        }
    }
}