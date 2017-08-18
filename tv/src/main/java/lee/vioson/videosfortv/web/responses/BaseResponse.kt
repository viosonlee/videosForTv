package lee.vioson.videosfortv.web.responses

import com.google.gson.annotations.Expose

/**
 * Created by viosonlee
 * on 2017/8/18.
 * todo 返回的数据
 */
open class BaseResponse<T>(
        @Expose
        var code: Long,
        @Expose
        var message: String,
        @Expose
        var body: T
)