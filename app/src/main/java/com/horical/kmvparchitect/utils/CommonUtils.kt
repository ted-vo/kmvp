package com.horical.kmvparchitect.utils

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.res.AssetManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.provider.Settings
import com.horical.kmvparchitect.R
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class CommonUtils private constructor() {

    companion object {

        @SuppressLint("HardwareIds")
        fun getDeviceId(context: Context): String {
            return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        }

        fun showLoadingDialog(context: Context): ProgressDialog {
            var pD = ProgressDialog(context)
            pD.show()
            if (pD.window != null) {
                pD.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
            pD.setContentView(R.layout.progress_dialog)
            pD.isIndeterminate = true
            pD.setCancelable(false)
            pD.setCanceledOnTouchOutside(false)
            return pD
        }

        @Throws(IOException::class)
        fun loadJSONFromAsset(context: Context, jsonFileName: String): String {
            val manager: AssetManager = context.assets
            val isInputStream: InputStream = manager.open(jsonFileName)
            val size: Int = isInputStream.available()
            val buffer = ByteArray(size)
            isInputStream.read(buffer)
            isInputStream.close()

            return String(buffer, Charset.forName("UTF-8"))
        }
    }
}