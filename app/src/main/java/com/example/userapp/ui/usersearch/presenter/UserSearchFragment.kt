package com.example.userapp.ui.usersearch.presenter

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userapp.R
import com.example.userapp.adapter.GithubUserAdapter
import com.example.userapp.core.data.dto.user.BaseListItemOfGithubUser
import com.example.userapp.core.data.dto.user.GithubUser
import com.example.userapp.core.data.dto.user.GithubUserItemClickListener
import com.example.userapp.core.extensions.RegexUtils
import com.example.userapp.core.extensions.observe
import com.example.userapp.core.extensions.safeLet
import com.example.userapp.core.platform.BaseFragment
import com.example.userapp.core.utils.delayClick
import com.example.userapp.databinding.FragmentUserSearchBinding
import com.example.userapp.ui.usersearch.domain.UserSearchActionState
import com.example.userapp.ui.usersearch.domain.UserSearchViewState
import com.example.userapp.core.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserSearchFragment : BaseFragment<FragmentUserSearchBinding>(
    layoutId = R.layout.fragment_user_search
) {
    override val viewModel: UserSearchViewModel by viewModels()

    private var githubUserAdapter: GithubUserAdapter? = null

    override fun initView() {
        setStatusBarColor(R.color.statusBarColor)
        observeData()
        binding.viewModel = viewModel
        binding.fragment = this
        setupRecyclerView()
        setupSearchEditText()

        if(githubUserAdapter != null) {
            binding.githubUserRecyclerView.adapter = githubUserAdapter
        }
        findNavController().currentBackStackEntry?.savedStateHandle
            ?.get<GithubUser>(FragmentDataTransferKeyword.GITHUB_USER)?.let { githubUser ->
            updateRecyclerViewCurrentGuestUserFavoriteStatue(
                viewModel.userSearchViewState.githubUsers, githubUser)
        }
    }

    fun onRefreshButtonClicked(view : View?) {
        view?.delayClick()
        view?.visibility = View.GONE
        viewModel.searchGithubUsers()
    }

    private fun observeData() {
        observe(viewModel.uiStateFlow) { viewState ->
            with(viewState as UserSearchViewState) {
                when (userSearchActionState) {
                    UserSearchActionState.NULL -> Unit
                    UserSearchActionState.GET_GITHUB_USERS -> {
                        githubUsers?.let { mappedUserList ->
                            githubUserAdapter = GithubUserAdapter(mappedUserList,
                            object : GithubUserItemClickListener {
                                override fun onUserClicked(itemView: View?, favoriteView: View?, githubUser: GithubUser?) {
                                    itemView?.delayClick()
                                    favoriteView?.delayClick()
                                    safeLet(githubUser, githubUser?.login) { _, _ ->
                                        viewModel.clearActionState()
                                        val action = UserSearchFragmentDirections
                                                .actionSearchUserFragmentToUserDetailFragment()
                                                .setGithubUser(githubUser)
                                        findNavController().navigate(action)
                                    }
                                }

                                override fun onUserFavoriteButtonClicked(itemView: View?, favoriteView: View?, githubUser: GithubUser?) {
                                    itemView?.delayClick()
                                    favoriteView?.delayClick()
                                    viewModel.updateGithubUserFavoriteState(githubUser)
                                }
                            })
                            binding.githubUserRecyclerView.adapter = githubUserAdapter
                        }
                    }
                    UserSearchActionState.LOAD_MORE_GITHUB_USERS -> {
                        updateRecyclerView(githubUsers)
                    }
                    UserSearchActionState.SEARCH_NEW_GITHUB_USERS -> {
                        viewModel.searchGithubUsers()
                    }
                    UserSearchActionState.UPDATE_GITHUB_USER_TO_FAVORITE_STATE,
                    UserSearchActionState.UPDATE_GITHUB_USER_TO_UNFAVORITE_STATE-> {
                        updateRecyclerViewCurrentGuestUserFavoriteStatue(
                            githubUsers, lastFavoriteUpdateGithubUser)
                    }
                    UserSearchActionState.ENABLE_REFRESH_BUTTON_AND_CLEAR_RECYCLER_VIEW -> {
                        updateRecyclerView(githubUsers)
                        binding.refreshImageView.visibility = View.VISIBLE
                    }
                    UserSearchActionState.ENABLE_LOAD_MORE_ACTION -> {
                        enableLoadMore()
                    }
                    UserSearchActionState.DISABLE_LOAD_MORE_ACTION -> {
                        disableLoadMore()
                    }
                }
            }
        }
    }

    private fun updateRecyclerView(githubUsers : ArrayList<BaseListItemOfGithubUser>?) {
        githubUsers?.let { mappedUserList ->
            githubUserAdapter?.updateData(mappedUserList)
        } ?: run {
            githubUserAdapter?.clear()
        }
    }

    private fun updateRecyclerViewCurrentGuestUserFavoriteStatue(
        githubUsers: ArrayList<BaseListItemOfGithubUser>?,
        lastFavoriteUpdateGithubUser: GithubUser?) {
        safeLet(githubUsers, lastFavoriteUpdateGithubUser)
        { mappedUserList, updatedGithubUser ->
            val positionOfUpdatedGithubUser =
                mappedUserList.indexOf(updatedGithubUser)
            githubUserAdapter?.notifyItemChanged(positionOfUpdatedGithubUser)
        }
    }

    private fun enableLoadMore() {
        isLoading = false
    }
    private fun disableLoadMore() {
        isLoading = true
    }


    private var isLoading = false

    private fun setupRecyclerView() {
        binding.githubUserRecyclerView.setHasFixedSize(true)
        binding.githubUserRecyclerView.setItemViewCacheSize(UserSearch.RECYCLER_VIEW_CACHE_SIZE)

        binding.githubUserRecyclerView.clearOnScrollListeners()
        binding.githubUserRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                if (!isLoading && totalItemCount <=
                    (lastVisibleItem + UserSearch.LOAD_MORE_TRIGGER_THRESHOLD)) {
                    disableLoadMore()
                    loadMoreData()
                }
            }
        })
    }

    private fun loadMoreData() {
        viewModel.loadMoreData()
    }

    private fun setupSearchEditText() {

        binding.searchEditText.removeTextChangedListener(null)
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val text = s?.toString()?.trim() ?: EMPTY
                val filteredText = text.replace(RegexUtils.SEARCH_TEXT_REGEX.toRegex(), "")
                if (viewModel.userSearchViewState.searchText != filteredText) {
                    viewModel.updateSearchText(filteredText)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}
