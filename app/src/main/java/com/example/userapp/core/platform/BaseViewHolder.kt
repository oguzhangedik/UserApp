package com.example.userapp.core.platform

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T : ViewDataBinding>(
    open val binding: T
) : RecyclerView.ViewHolder(binding.root)
