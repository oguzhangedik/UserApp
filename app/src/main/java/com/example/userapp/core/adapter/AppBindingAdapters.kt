package com.example.userapp.core.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.userapp.R
import com.example.userapp.domain.model.GithubUser
import com.example.userapp.domain.model.UserDetailHeaderItem
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

    @BindingAdapter(value = ["setUserDetailHeaderItemFavoriteImage"], requireAll = false )
    @JvmStatic
    fun setUserDetailHeaderItemFavoriteImage(view: ImageView?, data: UserDetailHeaderItem?) {
        safeLet(view, data) { imageView, userDetailHeaderItem ->
            if (userDetailHeaderItem.isFavorite == true) {
                imageView.imageURL(R.drawable.favorite_icon)
            } else {
                imageView.imageURL(R.drawable.unfavorite_icon)
            }
        }
    }
}