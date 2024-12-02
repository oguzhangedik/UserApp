package com.example.userapp.presentation.fragment.userdetail.presenter

import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.userapp.R
import com.example.userapp.presentation.adapter.GithubUserDetailAdapter
import com.example.userapp.domain.model.GithubUserDetailItemClickListener
import com.example.userapp.domain.model.UserDetailHeaderItem
import com.example.userapp.core.extensions.observe
import com.example.userapp.core.extensions.safeLet
import com.example.userapp.core.base.BaseFragment
import com.example.userapp.core.extensions.delayClick
import com.example.userapp.databinding.FragmentUserDetailBinding
import com.example.userapp.presentation.fragment.userdetail.domain.UserDetailActionState
import com.example.userapp.presentation.fragment.userdetail.domain.UserDetailViewState
import com.example.userapp.core.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : BaseFragment<FragmentUserDetailBinding>(
    layoutId = R.layout.fragment_user_detail
) {
    override val viewModel: UserDetailViewModel by viewModels()

    private var githubUserDetailAdapter: GithubUserDetailAdapter? = null

    override fun initView() {
        setStatusBarColor(R.color.statusBarColor)
        observeData()
        binding.fragment = this
        val userDetailArgs = UserDetailFragmentArgs.fromBundle(requireArguments())
        viewModel.githubUserDetail(userDetailArgs.githubUser)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            goBack()
        }
    }

    fun onBackButtonClicked(view : View?) {
        view?.delayClick()
        goBack()
    }

    private fun goBack() {
        findNavController().apply {
            previousBackStackEntry?.savedStateHandle?.set(
                FragmentDataTransferKeyword.GITHUB_USER,
                viewModel.userDetailViewState.githubUser)
            navigateUp()
        }
    }

    private fun observeData() {
        observe(viewModel.uiStateFlow) { viewState ->
            with(viewState as UserDetailViewState) {
                when (userDetailActionState) {
                    UserDetailActionState.NULL -> Unit
                    UserDetailActionState.GET_GITHUB_USER_DETAIL -> {
                        safeLet(githubUser, githubUserDetail, userDetails) { _,_, userDetails->
                            githubUserDetailAdapter = GithubUserDetailAdapter(userDetails,
                                object : GithubUserDetailItemClickListener {
                                    override fun onUserFavoriteButtonClicked(
                                        favoriteView: View?,
                                        userDetailHeaderItem: UserDetailHeaderItem?) {
                                        favoriteView?.delayClick()
                                        viewModel.updateGithubUserFavoriteState()
                                    }
                                })
                            binding.githubUserDetailRecyclerView.adapter = githubUserDetailAdapter

                        }
                    }
                    UserDetailActionState.FAVORITE_STATE_CHANGED_TO_FAVORITE,
                    UserDetailActionState.FAVORITE_STATE_CHANGED_TO_UNFAVORITE -> {
                        userDetails?.filterIsInstance<UserDetailHeaderItem>()?.firstOrNull()?.let{
                            userDetailHeaderItem ->
                            val indexOfUserDetailHeaderItem =
                                githubUserDetailAdapter?.items?.
                                indexOf(userDetailHeaderItem) ?: -1
                            if (indexOfUserDetailHeaderItem >= 0) {
                                githubUserDetailAdapter?.
                                notifyItemChanged(indexOfUserDetailHeaderItem)
                            }
                        }
                    }
                }
            }
        }
    }
}
