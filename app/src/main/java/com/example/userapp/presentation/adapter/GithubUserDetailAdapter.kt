package com.example.userapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.userapp.R
import com.example.userapp.core.adapter.BaseRecyclerAdapter
import com.example.userapp.domain.model.BaseListItemOfGithubUserDetail
import com.example.userapp.domain.model.GithubUserDetailItemClickListener
import com.example.userapp.domain.model.UserDetailHeaderItem
import com.example.userapp.domain.model.UserDetailListItem
import com.example.userapp.databinding.HeaderItemOfUserDetailBinding
import com.example.userapp.databinding.ListItemOfGithubUserDetailBinding

class GithubUserDetailAdapter(private var githubUserDetails: ArrayList<BaseListItemOfGithubUserDetail>, private val listener: GithubUserDetailItemClickListener?) :
    BaseRecyclerAdapter<List<BaseListItemOfGithubUserDetail>>(githubUserDetails) {
    companion object {
        private const val VIEW_TYPE_HEADER_ITEM = 2
        private const val VIEW_TYPE_LIST_ITEM = 3
    }

    inner class HeaderItemViewHolder(private val binding: HeaderItemOfUserDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(headerItem: UserDetailHeaderItem, listener: GithubUserDetailItemClickListener?) {
            binding.data = headerItem
            binding.listener = listener
            binding.executePendingBindings()
        }
    }

    inner class ListItemViewHolder(private val binding: ListItemOfGithubUserDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listItem: UserDetailListItem) {
            binding.data = listItem
            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER_ITEM -> {
                val binding: HeaderItemOfUserDetailBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.header_item_of_user_detail,
                    parent,
                    false
                )
                HeaderItemViewHolder(binding)
            }
            else -> {
                val binding: ListItemOfGithubUserDetailBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.list_item_of_github_user_detail,
                    parent,
                    false
                )
                ListItemViewHolder(binding)
            }
        }
    }

    override fun getItemCount() = githubUserDetails.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = githubUserDetails[position]
        when (holder.itemViewType) {
            VIEW_TYPE_HEADER_ITEM -> {
                val headerItemViewHolder = holder as HeaderItemViewHolder
                val headerItem = item as UserDetailHeaderItem
                headerItemViewHolder.bind(headerItem, listener)
            }

            VIEW_TYPE_LIST_ITEM -> {
                val listItemViewHolder = holder as ListItemViewHolder
                val listItem = item as UserDetailListItem
                listItemViewHolder.bind(listItem)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = githubUserDetails[position]
        return when (item) {
            is UserDetailHeaderItem -> VIEW_TYPE_HEADER_ITEM
            is UserDetailListItem -> VIEW_TYPE_LIST_ITEM
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }

    fun clear() {
        githubUserDetails = arrayListOf()
        updateDataNotify(githubUserDetails)
    }
}