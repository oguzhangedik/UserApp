package com.example.userapp.core.base

import androidx.recyclerview.widget.DiffUtil

open class BaseDiffCallBack<T : List<Any>>(
    private val oldNumbers: T,
    private val newNumbers: T
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldNumbers.size

    override fun getNewListSize(): Int = newNumbers.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNumbers[oldItemPosition] == newNumbers[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNumbers[oldItemPosition] == newNumbers[newItemPosition]
    }
}
