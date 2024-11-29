package com.example.userapp.ui.userdetail.presenter

import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.userapp.R
import com.example.userapp.core.extensions.observe
import com.example.userapp.core.platform.BaseFragment
import com.example.userapp.databinding.FragmentUserDetailBinding
import com.example.userapp.ui.userdetail.domain.UserDetailActionState
import com.example.userapp.ui.userdetail.domain.UserDetailViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : BaseFragment<FragmentUserDetailBinding>(
    layoutId = R.layout.fragment_user_detail
) {
    override val viewModel: UserDetailViewModel by viewModels()


    override fun initView() {
        setStatusBarColor(R.color.statusBarColor)
        observeData()

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
            with(viewState as UserDetailViewState) {
                when (userDetailActionState) {
                    UserDetailActionState.NULL -> Unit

                }
            }
        }
    }
}
