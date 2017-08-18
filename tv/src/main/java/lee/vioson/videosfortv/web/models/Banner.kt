package lee.vioson.videosfortv.web.models

import com.google.gson.annotations.Expose

/**
 * Created by viosonlee
 * on 2017/8/18.
 * todo
 */
data class Banner(@Expose
                  var vid: Long,
                  @Expose
                  var img: String,
                  @Expose
                  var type: Int,
                  @Expose
                  var id: Long,
                  @Expose
                  var desc: String)