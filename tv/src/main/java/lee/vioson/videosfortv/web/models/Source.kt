package lee.vioson.videosfortv.web.models

import com.google.gson.annotations.Expose

/**
 * Created by viosonlee
 * on 2017/8/18.
 * todo
 */
class Source(@Expose var vid: Long,
             @Expose var image: String,
             @Expose var name: String,
             @Expose var detail: String,
             @Expose var aid: Long,
             @Expose var playUrl: String)