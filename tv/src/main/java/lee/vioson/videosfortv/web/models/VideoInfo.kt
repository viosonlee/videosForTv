package lee.vioson.videosfortv.web.models

import com.google.gson.annotations.Expose

/**
 * Created by viosonlee
 * on 2017/8/18.
 * todo
 */

class VideoInfo(@Expose var area: String,
                @Expose var img: String,
                @Expose var doubanId: String,
                @Expose var updateDate: String,
                @Expose var sources: List<Source>,
                @Expose var sourceTypes: List<SourceType>,
                @Expose var release: String,
                @Expose var typeName: String,
                @Expose var movieId: Long,
                @Expose var type: String,
                @Expose var actors: String,
                @Expose var score: String,
                @Expose var name: String,
                @Expose var status: String,
                @Expose var desc: String)
