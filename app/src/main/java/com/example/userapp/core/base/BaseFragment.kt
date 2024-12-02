package com.example.userapp.core.base

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.userapp.core.extensions.observe
import com.example.userapp.core.extensions.observeEvent
import com.example.userapp.core.extensions.positiveButton
import com.example.userapp.core.extensions.showDialog
import com.example.userapp.core.extensions.toastMessage
import com.example.userapp.core.base.viewmodel.BaseAction
import com.example.userapp.core.base.viewmodel.BaseViewModel
import com.example.userapp.core.base.viewmodel.BaseViewState

abstract class BaseFragment<DB : ViewDataBinding>(
    @LayoutRes private val layoutId: Int) : Fragment() {

    protected abstract val viewModel: BaseViewModel<out BaseViewState, out BaseAction>

    protected lateinit var binding: DB

    //private lateinit var progressDialog: ProgressDialog

    abstract fun initView()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
/*        progressDialog = ProgressDialog(requireContext(), ProgressDialog.THEME_LIGHT)
        progressDialog.isCancelable = true*/
        binding.lifecycleOwner = viewLifecycleOwner
        initView()
        observeEvent(viewModel.baseEvent, ::onViewEvent)
        observe(viewModel.loading) {
            if (it) {
                showProgressView()
            } else {
                hideProgressView()
            }
        }
    }

    /**
     * This function will make statusBar background transparent and fit into whole window.
     */
    @RequiresApi(Build.VERSION_CODES.R)
    private fun adjustWindow() {
        if (activity != null) {
            val window = activity?.window
            if (window != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    window.setDecorFitsSystemWindows(false)
                } else {
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
                }
                window.statusBarColor = Color.TRANSPARENT
            }
        }
    }

    private fun onViewEvent(event: BaseViewEvent) {
        when (event) {
            is BaseViewEvent.ShowCustomError -> showError(event.message)
            is BaseViewEvent.ShowInfoMessage -> showInfoPopup(event.message)
        }
    }

    fun showError(message: String) {
        context?.showDialog(
            cancelable = true
        ) {
            setMessage(message)
            positiveButton {

            }
        }
    }

    fun showInfoPopup(message: String) {
        context?.showDialog(
            cancelable = true
        ) {
            setMessage(message)
            positiveButton {

            }
        }
    }

    fun showSuccess(message: String) {
        toastMessage(message)
    }

    fun doubleClickBlocked(view: View) {
        view.apply {
            isEnabled = false
            postDelayed({ isEnabled = true }, 400)
        }
    }

    internal fun hideSoftInput() {
        activity?.let {
            (it.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
                it.currentFocus
                hideSoftInputFromWindow(
                    (it.currentFocus ?: View(requireContext())).windowToken,
                    0
                )
            }
        }
    }

    internal fun showSoftInput() {
        val inputManager =
            requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
    }

    fun showProgressView() {
        hideSoftInput()
        (activity as? BaseActivity<*>)?.showProgressView()
    }

    fun hideProgressView() {
        (activity as? BaseActivity<*>)?.hideProgressView()
    }

    open fun finishApp() {
        requireActivity().finish()
        val finishIntent =
            Intent(Intent.ACTION_MAIN).apply {
                addCategory(Intent.CATEGORY_HOME)
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }

        startActivity(finishIntent)
    }

    protected fun setStatusBarColor(@ColorRes colorRes: Int) {
        activity?.window?.statusBarColor =
            ContextCompat.getColor(requireContext(), colorRes)
    }
}
