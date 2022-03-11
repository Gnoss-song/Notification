package com.example.practicenotifications

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks

data class ItemData(
    val id : Long = 1,
    val name : String = "STS"
)

object DynamicLinkUtils {
    val TAG = DynamicLinkUtils::class.java.name

    private fun getDeepLink(scheme: String, key: String?,key2 : String?, pheedId: String?, data : ItemData?): Uri {
        return if (key == null) {
            Uri.parse("https://example.com/${scheme}")
        } else {
            Uri.parse("https://example.com/${scheme}/?${key}=$pheedId&${key2}=$data")
        }
    }

    fun onDynamicLinkClick(
        activity: Activity,
        scheme: String,
        key: String? = null,
        key2 : String? = null,
        data : ItemData? = null,
        pheedId: String? = null
    ) {
        FirebaseDynamicLinks.getInstance().createDynamicLink()
            .setLink(getDeepLink(scheme, key,key2, pheedId,data))
            .setDynamicLinkDomain("practicenotifications.page.link")
            .setAndroidParameters(
                DynamicLink.AndroidParameters.Builder(activity.packageName)
                    .setMinimumVersion(1)
                    .build()
            )
            .buildShortDynamicLink()
            .addOnCompleteListener(
                activity
            ) { task ->
                if (task.isSuccessful) {
                    val shortLink: Uri = task.result.shortLink!!
                    try {
                        val sendIntent = Intent()
                        sendIntent.action = Intent.ACTION_SEND
                        sendIntent.putExtra(Intent.EXTRA_TEXT, shortLink.toString())
                        sendIntent.type = "text/plain"
                        activity.startActivity(Intent.createChooser(sendIntent, "Share"))
                    } catch (ignored: ActivityNotFoundException) {
                    }
                } else {
                    Log.i(TAG, task.toString())
                }
            }
    }


}