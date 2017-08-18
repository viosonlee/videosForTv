package lee.vioson.videosfortv.web

import lee.vioson.videosfortv.DebugLog
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by viosonlee
 * on 2017/8/18.
 * todo
 */
class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        val request: Request = chain!!.request()
        val body: FormBody = request.body() as FormBody
        val sb = StringBuilder()
        for (i in 0 until body.size()) {
            sb.append(body.encodedName(i))
            sb.append("=")
            sb.append(body.encodedValue(i))
            sb.append(";")
        }
        DebugLog.e(String.format("发送请求%s with %s on %s", request.url(), sb.toString(),
                request.headers().toString()))
        return chain.proceed(request)
    }
}