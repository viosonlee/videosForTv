package lee.vioson.videosfortv.web

import lee.vioson.videosfortv.web.responses.HotPlayResponse
import lee.vioson.videosfortv.web.responses.SearchResponse
import lee.vioson.videosfortv.web.responses.SuggestResponse
import lee.vioson.videosfortv.web.responses.VideoInfoResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import rx.Single

/**
 * Created by viosonlee
 * on 2017/8/18.
 * todo
 */
interface IApi {
    @GET("/newmovie/api/suggest")
    fun suggest(@Query("q") q: String): Single<SuggestResponse>

    @GET("/newmovie/api/hotPlay")
    fun hotPlay(@Query("type") type: Int): Single<HotPlayResponse>

    @GET("/newmovie/api/videos")
    fun search(@Query("keywords") keywords: String, @Query("page") page: Int, @Query("pageSize") pageSize: Int): Single<SearchResponse>

//    @Headers(
//            "userId:WXQfDSuF0LoDAMaiwe5UhCj7",
//            "loginid:''",
//            "token:''",
//            "platform:android",
//            "xigua:true",
//            "thunder:true",
//            "appVersion:5.4.0",
//            "platVersion:7.1.1",
//            "package:com.ghost.movieheaven",
//            "Host:120.55.16.187",
//            "Connection:Keep-Alive",
//            "Accept-Encoding:gzip",
//            "User-Agent:okhttp/3.6.0"
//    )
    @GET("/newmovie/api/video")
    fun videoInfo(@Query("videoId") videoId: Long): Single<VideoInfoResponse>
}