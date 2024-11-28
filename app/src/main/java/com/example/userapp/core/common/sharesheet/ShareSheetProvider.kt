package com.example.userapp.core.common.sharesheet

import android.app.Activity
import android.content.Intent
import java.net.URL

object ShareSheetProvider {

    fun shareSheetText(activity: Activity, text: String) {
        val sendTextIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        activity.startActivity(
            Intent.createChooser(sendTextIntent, null)
        )
    }

    fun shareSheetImage(activity: Activity, uriToImage: URL) {
        val sendImageIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, uriToImage)
            type = "image/jpeg"
        }
    }
}
