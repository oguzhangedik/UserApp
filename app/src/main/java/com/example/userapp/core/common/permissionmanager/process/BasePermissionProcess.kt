package com.example.userapp.core.common.permissionmanager.process

import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.fragment.app.Fragment
import com.example.userapp.core.platform.BetterActivityResult

abstract class BasePermissionProcess(fragment: Fragment) {
    var requestCode: RequestCode = RequestCode.None
    protected val activityLauncher: BetterActivityResult<Intent, ActivityResult> =
        BetterActivityResult.registerActivityForResult(fragment)
}
enum class RequestCode {
    None,
    MainLocationPermission
}
