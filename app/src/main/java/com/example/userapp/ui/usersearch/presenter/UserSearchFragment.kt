package com.example.userapp.ui.usersearch.presenter

import android.os.Bundle
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.userapp.R
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

    override fun initView() {
        setStatusBarColor(R.color.statusBarColor)
        observeData()

        // Geri butonuna basıldığında
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            goBack()
        }

        viewModel.searchGithubUsers()

    }

    private fun goBack() {
        findNavController().navigateUp()
    }

    private fun observeData() {
        observe(viewModel.uiStateFlow) { viewState ->
            with(viewState as UserSearchViewState) {
                when(userSearchActionState) {
                    UserSearchActionState.NULL -> Unit
                }
            }
        }
    }
}
