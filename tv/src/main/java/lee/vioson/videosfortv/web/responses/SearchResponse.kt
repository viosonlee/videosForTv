package lee.vioson.videosfortv.web.responses

import lee.vioson.videosfortv.web.models.Video

/**
 * Created by viosonlee
 * on 2017/8/18.
 * todo
 */
class SearchResponse(code: Long, message: String, body: List<Video>) : BaseResponse<List<Video>>(code, message, body)