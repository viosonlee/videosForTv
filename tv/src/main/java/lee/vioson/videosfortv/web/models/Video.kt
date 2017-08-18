package lee.vioson.videosfortv.web.models

import com.google.gson.annotations.Expose

/**
 * Created by viosonlee
 * on 2017/8/18.
 * todo
 */
class Video(@Expose var publishTime: Int,
            @Expose var score: Double,
            @Expose var doubanId: String,
            @Expose var img: String,
            @Expose var movieTypeName: String,
            @Expose var album: Boolean,
            @Expose var name: String,
            @Expose var movieId: Long,
            @Expose var status: String,
            @Expose var lastUpdateTime: String
)