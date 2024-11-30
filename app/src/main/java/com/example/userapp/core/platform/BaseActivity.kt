package com.example.userapp.core.platform

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.userapp.core.extensions.negativeButton
import com.example.userapp.core.extensions.observe
import com.example.userapp.core.extensions.observeEvent
import com.example.userapp.core.extensions.positiveButton
import com.example.userapp.core.extensions.showDialog
import com.example.userapp.core.extensions.toastMessage
import com.example.userapp.core.platform.viewmodel.BaseAction
import com.example.userapp.core.platform.viewmodel.BaseViewModel
import com.example.userapp.core.platform.viewmodel.BaseViewState
import com.example.userapp.core.views.ProgressDialog
import timber.log.Timber

abstract class BaseActivity<DB : ViewDataBinding>
    (@LayoutRes private val layoutId: Int) : AppCompatActivity() {

    private val TAG = BaseActivity::class.java.simpleName

    val binding by lazy {
        DataBindingUtil.setContentView(this, layoutId) as DB
    }

    protected abstract val viewModel: BaseViewModel<out BaseViewState, out BaseAction>

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            // Permission is granted
        } else {
            // Permission is rejected
            toastMessage("This application can't post or receive notifications without Notification permission")
        }
    }


    protected val activityLauncher= registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            Timber.tag(TAG).d(TAG, "intent received")
        } else {
            Timber.tag(TAG).e(TAG, "error on activity result!")
        }
    }

    lateinit var progressDialog: ProgressDialog
    abstract fun onDataBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        observeEvent(viewModel.baseEvent, ::onViewEvent)

        progressDialog = ProgressDialog(this, ProgressDialog.THEME_LIGHT)
        onDataBinding()
        checkNotificationPermission()

        observe(viewModel.loading) {
            if (it)
                showProgressView()
            else
                hideProgressView()
        }
    }

    private fun onViewEvent(event: BaseViewEvent) {
        when (event) {
            is BaseViewEvent.ShowCustomError -> showError(event.message)
            is BaseViewEvent.ShowInfoMessage -> showInfoPopup(event.message)
        }
    }

    private fun checkNotificationPermission() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // Do your task on permission granted
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                this.baseContext.showDialog(
                    cancelable = true
                ) {
                    setMessage("notification_permission_warning")
                    positiveButton {
                        permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }
                    negativeButton {
                        //dismiss dialog
                    }
                }
            } else {
                // Directly ask for the permission
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    fun showCommonError() {
        Toast.makeText(baseContext, "something_went_wrong", Toast.LENGTH_LONG).show()
    }

    fun showError(@StringRes error: Int) {
        Toast.makeText(baseContext, getString(error), Toast.LENGTH_LONG).show()
    }

    fun showError(error: String) {
        this.showDialog(cancelable = true) {
            setMessage(error)
            positiveButton {}
        }
    }

    fun showInfoPopup(message: String) {
        this.showDialog(cancelable = true) {
            setMessage(message)
            positiveButton {}
        }
    }

    fun showProgressView() {
        progressDialog.show()
    }

    fun hideProgressView() {
        progressDialog.dismiss()
    }

    open fun finishApp() {
        finish()
        val finishIntent =
            Intent(Intent.ACTION_MAIN).apply {
                addCategory(Intent.CATEGORY_HOME)
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }

        startActivity(finishIntent)
    }
}
