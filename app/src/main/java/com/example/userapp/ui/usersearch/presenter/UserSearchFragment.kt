package com.example.userapp.ui.usersearch.presenter

import android.text.Editable
import android.text.TextWatcher
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
import com.example.userapp.core.extensions.safeLet
import com.example.userapp.core.platform.BaseFragment
import com.example.userapp.core.utils.delayClick
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
        binding.viewModel = viewModel
        setupRecyclerView()
        setupSearchEditText()
        // Geri butonuna basıldığında
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            goBack()
        }
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
                                override fun onUserClicked(itemView: View?, favoriteView: View?, githubUser: GithubUser?) {
                                    itemView?.delayClick()
                                    favoriteView?.delayClick()
                                    Log.d("CLICKTEST","onUserClicked")
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
                        githubUsers?.let { mappedUserList ->
                            githubUserAdapter?.updateData(mappedUserList)
                            isLoading = false
                        }
                    }
                    UserSearchActionState.SEARCH_NEW_GITHUB_USERS -> {
                        viewModel.searchGithubUsers()
                    }
                    UserSearchActionState.UPDATE_GITHUB_USER_TO_FAVORITE_STATE,
                    UserSearchActionState.UPDATE_GITHUB_USER_TO_UNFAVORITE_STATE-> {
                        safeLet(githubUsers, lastFavoriteUpdateGithubUser)
                        { mappedUserList, updatedGithubUser ->
                            val positionOfUpdatedGithubUser =
                                mappedUserList.indexOf(updatedGithubUser)
                            githubUserAdapter?.notifyItemChanged(positionOfUpdatedGithubUser)
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
        binding.githubUserRecyclerView.setHasFixedSize(true)
        binding.githubUserRecyclerView.setItemViewCacheSize(30)
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
        viewModel.searchGithubUsers()
    }

    private fun setupSearchEditText() {
        // Tüm önceki TextWatcher'ları kaldır
        binding.searchEditText.removeTextChangedListener(null)
        // TextWatcher ile EditText'i dinle
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val text = s?.toString()?.trim() ?: ""
                // Harf ve rakam validasyonu yap, sadece A-Z, a-z, 0-9, ve boşluk
                val filteredText = text.replace("[^a-zA-Z0-9\\s]".toRegex(), "")
                // Text gecikmeli olarak ViewModel'e gönder
                viewModel.updateSearchText(filteredText)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}
