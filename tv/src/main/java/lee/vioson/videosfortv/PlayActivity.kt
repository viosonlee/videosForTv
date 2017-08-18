package lee.vioson.videosfortv

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import lee.vioson.videosfortv.utils.ToastUtil

/**
 * Created by viosonlee
 * on 2017/8/18.
 * todo
 */
class PlayActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        val player: StandardGSYVideoPlayer = findViewById(R.id.player)
        val url = intent.getStringExtra(VIDEO_URL)
        val title = intent.getStringExtra(TITLE)
        if (url.isEmpty()) ToastUtil.showToast(this, R.string.error_url)
        else
            player.setUp(url, false, title)
    }

    companion object {
        const val VIDEO_URL = "video_url"
        const val TITLE = "title"
    }
}