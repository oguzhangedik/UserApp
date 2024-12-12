package com.example.userapp.core.adapter

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.userapp.core.common.DimensionUtil
import com.google.android.material.floatingactionbutton.FloatingActionButton


@BindingAdapter("app:image")
fun ImageView.imageURL(image: Any) {
    Glide.with(this.context).load(image).into(this)
}
@BindingAdapter("app:imageUrl")
fun ImageView.imageURL(url: String) {
    Glide.with(this.context).load(url).into(this)
}

@BindingAdapter("app:roundImageUrl")
fun ImageView.roundImageUrl(url: String) {
    Glide.with(this.context)
        .load(url)
        .circleCrop()
        .into(this)
}

@BindingAdapter("app:imageUrlRadius")
fun ImageView.imageURLRadius(url: String?) {
    url?.let { imageUrl ->
        Glide.with(this.context).load(imageUrl)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(DimensionUtil.dp2px(this.context,32))))
            .into(this)
    }
}
@BindingAdapter("app:imageUrlSmallRadius")
fun ImageView.imageUrlSmallRadius(url: String?) {
    url?.let { imageUrl ->
        Glide.with(this.context).load(imageUrl)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(DimensionUtil.dp2px(this.context,8))))
            .into(this)
    }
}
@BindingAdapter("app:image")
fun ImageView.imageURL(@DrawableRes image: Int) {
    Glide.with(this.context).load(image).into(this)
}

@BindingAdapter("app:floatImage")
fun FloatingActionButton.imageSrc(@DrawableRes image: Int) {
    this.setImageDrawable(ContextCompat.getDrawable(this.context, image))
}
