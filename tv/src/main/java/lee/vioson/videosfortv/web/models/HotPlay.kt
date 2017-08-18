package lee.vioson.videosfortv.web.models

import com.google.gson.annotations.Expose

/**
 * Created by viosonlee
 * on 2017/8/18.
 * todo
 */
class HotPlay(@Expose var viewItemModels: List<ViewItemModels>,
              @Expose var banner: List<Banner>)