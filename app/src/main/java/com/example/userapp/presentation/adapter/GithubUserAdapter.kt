package com.example.userapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.userapp.R
import com.example.userapp.core.adapter.BaseRecyclerAdapter
import com.example.userapp.domain.model.BaseListItemOfGithubUser
import com.example.userapp.domain.model.GithubUser
import com.example.userapp.domain.model.GithubUserItemClickListener
import com.example.userapp.domain.model.NoItemOfGithubUser
import com.example.userapp.domain.model.ProgressItemOfGithubUser
import com.example.userapp.databinding.ListItemOfGithubUserBinding
import com.example.userapp.databinding.NoItemOfGithubUserBinding
import com.example.userapp.databinding.ProgressItemOfGithubUserBinding

class GithubUserAdapter(var githubUsers: ArrayList<BaseListItemOfGithubUser>, private val listener: GithubUserItemClickListener?) :
    BaseRecyclerAdapter<List<BaseListItemOfGithubUser>>(githubUsers)  {
    companion object {
        private const val VIEW_TYPE_LIST_ITEM = 2
        private const val VIEW_TYPE_PROGRESS_ITEM = 3
        private const val VIEW_TYPE_NO_ITEM = 4
    }

    inner class ListItemViewHolder(private val binding: ListItemOfGithubUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(githubUser: GithubUser, listener: GithubUserItemClickListener?) {
            binding.data = githubUser
            binding.listener = listener
            binding.executePendingBindings()
        }
    }

    inner class ProgressItemViewHolder(private val binding: ProgressItemOfGithubUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(progressItem: ProgressItemOfGithubUser) {
            binding.data = progressItem
            binding.executePendingBindings()
        }
    }

    inner class NoItemViewHolder(private val binding: NoItemOfGithubUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(noItem: NoItemOfGithubUser) {
            binding.data = noItem
            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_LIST_ITEM -> {
                val binding: ListItemOfGithubUserBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.list_item_of_github_user,
                    parent,
                    false
                )
                ListItemViewHolder(binding)
            }
            VIEW_TYPE_NO_ITEM -> {
                val binding: NoItemOfGithubUserBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.no_item_of_github_user,
                    parent,
                    false
                )
                NoItemViewHolder(binding)
            }
            else -> {
                val binding: ProgressItemOfGithubUserBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.progress_item_of_github_user,
                    parent,
                    false
                )
                ProgressItemViewHolder(binding)
            }
        }
    }

    override fun getItemCount() = githubUsers.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = githubUsers[position]
        when (holder.itemViewType) {
            VIEW_TYPE_LIST_ITEM -> {
                val listItemViewHolder = holder as ListItemViewHolder
                val listItem = item as GithubUser
                listItemViewHolder.bind(listItem, listener)
            }

            VIEW_TYPE_NO_ITEM -> {
                val noItemViewHolder = holder as NoItemViewHolder
                val noItem = item as NoItemOfGithubUser
                noItemViewHolder.bind(noItem)
            }

            VIEW_TYPE_PROGRESS_ITEM -> {
                val progressItemViewHolder = holder as ProgressItemViewHolder
                val progressItem = item as ProgressItemOfGithubUser
                progressItemViewHolder.bind(progressItem)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = githubUsers[position]
        return when (item) {
            is GithubUser -> VIEW_TYPE_LIST_ITEM
            is NoItemOfGithubUser -> VIEW_TYPE_NO_ITEM
            is ProgressItemOfGithubUser -> VIEW_TYPE_PROGRESS_ITEM
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }

    fun clear() {
        githubUsers = arrayListOf()
        updateDataNotify(githubUsers)
    }
}