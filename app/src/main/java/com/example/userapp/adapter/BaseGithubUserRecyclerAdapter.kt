package com.example.userapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.userapp.core.data.dto.user.BaseListItemOfGithubUser
import com.example.userapp.core.platform.BaseDiffCallBack

abstract class BaseGithubUserRecyclerAdapter<T : ArrayList<BaseListItemOfGithubUser>>(
    var items: T
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateData(newItems: T) {
        val diffCallBack = BaseDiffCallBack(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }
    fun updateDataNotify(newItems: T) {
        items = newItems
        notifyDataSetChanged()
    }
    abstract fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        onCreateViewHolder(
            parent = parent,
            inflater = LayoutInflater.from(parent.context),
            viewType = viewType
        )
}
