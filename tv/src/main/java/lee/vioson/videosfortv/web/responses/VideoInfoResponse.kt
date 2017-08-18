package lee.vioson.videosfortv.web.responses

import lee.vioson.videosfortv.web.models.VideoInfo

/**
 * Created by viosonlee
 * on 2017/8/18.
 * todo
 */
class VideoInfoResponse(message: String, body: VideoInfo, code: Long) : BaseResponse<VideoInfo>(code, message, body)