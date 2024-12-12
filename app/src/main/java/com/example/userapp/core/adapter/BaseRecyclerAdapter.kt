package com.example.userapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.userapp.core.base.BaseDiffCallBack

abstract class BaseRecyclerAdapter<T : List<Any>>(
    var items: T
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateData(newItems: T) {
        val diffCallBack = BaseDiffCallBack(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        (items as? ArrayList<Any>)?.let {
            it.clear()
            it.addAll(newItems)
            diffResult.dispatchUpdatesTo(this)
        }
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
