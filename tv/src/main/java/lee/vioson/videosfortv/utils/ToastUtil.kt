package lee.vioson.videosfortv.utils


import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast

import lee.vioson.videosfortv.BuildConfig


object ToastUtil {
    private var toast: Toast? = null

    fun showToast(context: Context, content: String) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_SHORT)
        } else {
            toast!!.setText(content)
        }
        toast!!.show()
    }

    fun showDebugToast(context: Context, content: String) {
        if (!BuildConfig.DEBUG) return
        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_SHORT)
        } else {
            toast!!.setText(content)
        }
        toast!!.show()
    }

    fun showToast(context: Context, contentId: Int) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    contentId,
                    Toast.LENGTH_SHORT)
        } else {
            toast!!.setText(contentId)
        }
        toast!!.show()
    }

    fun showDebugToast(context: Context, contentId: Int) {
        if (!BuildConfig.DEBUG) return
        if (toast == null) {
            toast = Toast.makeText(context,
                    contentId,
                    Toast.LENGTH_SHORT)
        } else {
            toast!!.setText(contentId)
        }
        toast!!.show()
    }

    fun showToastInsideThread(context: Context, msg: String) {
        val mainLooper = context.mainLooper
        val handler = Handler(mainLooper)
        handler.post { showToast(context, msg) }
    }

    fun showToastInsideThread(context: Context, share_failure: Int) {
        val mainLooper = context.mainLooper
        val handler = Handler(mainLooper)
        handler.post { showToast(context, share_failure) }
    }
}
