package lee.vioson.videosfortv.web.models

import com.google.gson.annotations.Expose

/**
 * Created by viosonlee
 * on 2017/8/18.
 * todo
 */
class SourceType(@Expose var name: String,
                 @Expose var logo: String,
                 @Expose var type: String,
                 @Expose var desc: String)