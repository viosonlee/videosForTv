package lee.vioson.videosfortv.web.models

import com.google.gson.annotations.Expose

/**
 * Created by viosonlee
 * on 2017/8/18.
 * todo
 */
class ViewItemModels(@Expose var videos: List<Video>,
                     @Expose var title: String,
                     @Expose var moreUrl: String) {
}