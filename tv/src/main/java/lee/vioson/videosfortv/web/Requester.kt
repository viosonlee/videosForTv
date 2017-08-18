package lee.vioson.videosfortv.web

import android.text.TextUtils
import lee.vioson.videosfortv.DebugLog
import lee.vioson.videosfortv.web.responses.HotPlayResponse
import lee.vioson.videosfortv.web.responses.SearchResponse
import lee.vioson.videosfortv.web.responses.SuggestResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Single
import rx.SingleSubscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by viosonlee
 * on 2017/8/18.
 * todo
 */
object Requester {
    var iApi: IApi? = null
    private val DEFAULT_TIME_OUT: Long = 10 * 1000
    private val BASE_URL = "120.55.16.187"

    private fun getApi(): IApi {
        if (iApi == null) {
            val logging: HttpLoggingInterceptor = getLoggingInterceptor()
            val client: OkHttpClient = getHttpClient(logging)
            val retrofit: Retrofit = getRetrofit(client)
            iApi = retrofit.create(IApi::class.java)
        }
        return iApi!!
    }

    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val logging: HttpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            message ->
            run {
                if (TextUtils.isEmpty(message)) {
                    val substring = message.substring(0, 1)
                    //如果收到想响应是json才打印
                    if ("{" == substring || "[" == substring) {
                        DebugLog.e("收到响应: " + message)
                    }
                }

            }
        })
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    private fun getHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(LoggingInterceptor())
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(logging)
                .build()
        return client
    }

    private fun getRetrofit(client: OkHttpClient): Retrofit {
        val retrofit: Retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build()
        return retrofit
    }

    private fun <T> request(single: Single<T>, subscriber: SingleSubscriber<T>) {
        single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber)
    }

    fun suggest(q: String, subscriber: SingleSubscriber<SuggestResponse>) {
        request(getApi().suggest(q), subscriber)
    }

    fun search(keyWords: String, page: Int, subscriber: SingleSubscriber<SearchResponse>) {
        request(getApi().search(keyWords, page, 30), subscriber)
    }

    fun hotPlay(type: Int, subscriber: SingleSubscriber<HotPlayResponse>) {
        request(getApi().hotPlay(type), subscriber)
    }
}