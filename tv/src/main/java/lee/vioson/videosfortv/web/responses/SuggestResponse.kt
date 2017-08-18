package lee.vioson.videosfortv.web.responses

/**
 * Created by viosonlee
 * on 2017/8/18.
 * todo
 */
class SuggestResponse(code: Long, message: String, body: List<String>) : BaseResponse<List<String>>(code, message, body)