package com.example.userapp.core.common.permissionmanager.process

interface IBaseProcess<T> {
    fun successPermission(successEvent: T? = null, requestCode: RequestCode)
    fun unsuccessfulPermission(errorString: String? = null, requestCode: RequestCode)
    fun loopPermission(loopEvent: T? = null, requestCode: RequestCode)
}
