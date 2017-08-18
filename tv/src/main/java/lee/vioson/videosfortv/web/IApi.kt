package lee.vioson.videosfortv.web

import lee.vioson.videosfortv.web.responses.HotPlayResponse
import lee.vioson.videosfortv.web.responses.SearchResponse
import lee.vioson.videosfortv.web.responses.SuggestResponse
import retrofit2.http.GET
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

    @GET("/newmovie/api/HotPlay")
    fun hotPlay(@Query("type") type: Int): Single<HotPlayResponse>

    @GET("/newmovie/api/videos")
    fun search(@Query("keywords") keywords: String, @Query("page") page: Int, @Query("pageSize") pageSize: Int): Single<SearchResponse>
}