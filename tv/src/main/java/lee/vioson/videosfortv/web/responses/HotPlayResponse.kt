package lee.vioson.videosfortv.web.responses

import lee.vioson.videosfortv.web.models.HotPlay

/**
 * Created by viosonlee
 * on 2017/8/18.
 * todo
 */
class HotPlayResponse(code: Long, message: String, body: HotPlay) : BaseResponse<HotPlay>(code, message, body)