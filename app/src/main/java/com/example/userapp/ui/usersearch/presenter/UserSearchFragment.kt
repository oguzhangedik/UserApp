package com.example.userapp.ui.usersearch.presenter

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userapp.R
import com.example.userapp.adapter.GithubUserAdapter
import com.example.userapp.core.data.dto.user.GithubUser
import com.example.userapp.core.data.dto.user.GithubUserItemClickListener
import com.example.userapp.core.extensions.observe
import com.example.userapp.core.platform.BaseFragment
import com.example.userapp.databinding.FragmentUserSearchBinding
import com.example.userapp.ui.usersearch.domain.UserSearchActionState
import com.example.userapp.ui.usersearch.domain.UserSearchViewState
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
        setupRecyclerView()

        // Geri butonuna basıldığında
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            goBack()
        }

        viewModel.searchGithubUsers("A")

    }

    private fun goBack() {
        findNavController().navigateUp()
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
                                override fun onUserClicked(view: View?, githubUser: GithubUser?) {
                                }
                            })
                            binding.githubUserRecyclerView.adapter = githubUserAdapter
                        }
                    }
                    UserSearchActionState.LOAD_MORE_GITHUB_USERS -> {
                        githubUsers?.let { mappedUserList ->
                            githubUserAdapter?.updateData(mappedUserList)
                            isLoading = false
                        }
                    }
                }
            }
        }
    }

    private var isLoading = false
    private var isLastPage = false
    private val visibleThreshold = 5  // Son 5 elemandan önce yükleme yapılacak

    private fun setupRecyclerView() {
        // Scroll listener
        binding.githubUserRecyclerView.clearOnScrollListeners()
        binding.githubUserRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // Sonraki elemanlara ulaşılma kontrolü
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (!isLoading && !isLastPage && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    // Son 5 itemdan önce veri yükle
                    loadMoreData()
                    isLoading = true
                }
            }
        })
    }

    private fun loadMoreData() {
        Log.d("loadMoreData", "loadMoreData: ")
        viewModel.searchGithubUsers("A")
    }
}
