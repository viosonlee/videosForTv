package lee.vioson.videosfortv

import android.util.Log

/**
 * Created by viosonlee
 * on 2017/8/18.
 * todo
 */
object DebugLog {
    private val TAG = "videosForTv"
    fun d(tag: String, value: String) {
        if (BuildConfig.DEBUG)
            Log.d(tag, value)
    }

    fun v(tag: String, value: String) {
        if (BuildConfig.DEBUG)
            Log.d(tag, value)
    }

    fun i(tag: String, value: String) {
        if (BuildConfig.DEBUG)
            Log.d(tag, value)
    }

    fun w(tag: String, value: String) {
        if (BuildConfig.DEBUG)
            Log.d(tag, value)
    }

    fun e(tag: String, value: String) {
        if (BuildConfig.DEBUG)
            Log.d(tag, value)
    }

    fun d(value: String) {
        if (BuildConfig.DEBUG)
            Log.d(TAG, value)
    }

    fun v(value: String) {
        if (BuildConfig.DEBUG)
            Log.d(TAG, value)
    }

    fun i(value: String) {
        if (BuildConfig.DEBUG)
            Log.d(TAG, value)
    }

    fun w(value: String) {
        if (BuildConfig.DEBUG)
            Log.d(TAG, value)
    }

    fun e(value: String) {
        if (BuildConfig.DEBUG)
            Log.d(TAG, value)
    }


}