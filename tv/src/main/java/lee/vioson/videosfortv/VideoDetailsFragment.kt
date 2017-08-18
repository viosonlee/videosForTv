/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package lee.vioson.videosfortv

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v17.leanback.app.DetailsFragment
import android.support.v17.leanback.app.DetailsFragmentBackgroundController
import android.support.v17.leanback.widget.*
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import lee.vioson.videosfortv.utils.ToastUtil
import lee.vioson.videosfortv.web.Requester
import lee.vioson.videosfortv.web.models.Video
import lee.vioson.videosfortv.web.models.VideoInfo
import lee.vioson.videosfortv.web.responses.VideoInfoResponse
import rx.SingleSubscriber

/**
 * A wrapper fragment for leanback details screens.
 * It shows a detailed view of video and its metadata plus related videos.
 */
class VideoDetailsFragment : DetailsFragment() {

    private var mSelectedMovie: Video? = null
    private lateinit var mVideoInfo: VideoInfo
    private lateinit var mDetailsBackground: DetailsFragmentBackgroundController
    private lateinit var mPresenterSelector: ClassPresenterSelector
    private lateinit var mAdapter: ArrayObjectAdapter

    private inner class DataHandler : SingleSubscriber<VideoInfoResponse>() {
        override fun onSuccess(value: VideoInfoResponse?) {
            mVideoInfo = value!!.body
            setupDetailsOverviewRow()
        }

        override fun onError(error: Throwable?) {
            DebugLog.e("error:" + error.toString())
            ToastUtil.showToast(activity, R.string.video_info_error)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate DetailsFragment")
        super.onCreate(savedInstanceState)
        mDetailsBackground = DetailsFragmentBackgroundController(this)
        mSelectedMovie = activity.intent.getParcelableExtra(DetailsActivity.MOVIE)
        loadVideoInfo()
        if (mSelectedMovie != null) {
            mPresenterSelector = ClassPresenterSelector()
            mAdapter = ArrayObjectAdapter(mPresenterSelector)

            setupDetailsOverviewRowPresenter()
//            setupRelatedMovieListRow()
            adapter = mAdapter
            initializeBackground(mSelectedMovie)
            onItemViewClickedListener = ItemViewClickedListener()
        } else {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadVideoInfo() {
        Requester.videoInfo(mSelectedMovie!!.movieId, DataHandler())
    }

    private fun initializeBackground(movie: Video?) {
        mDetailsBackground.enableParallax()
        Glide.with(activity)
                .load(movie?.img)
                .asBitmap()
                .centerCrop()
                .error(R.drawable.default_background)
                .into<SimpleTarget<Bitmap>>(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(bitmap: Bitmap,
                                                 glideAnimation: GlideAnimation<in Bitmap>) {
                        mDetailsBackground.coverBitmap = bitmap
                        mAdapter.notifyArrayItemRangeChanged(0, mAdapter.size())
                    }
                })
    }

    private fun setupDetailsOverviewRow() {
        Log.d(TAG, "doInBackground: " + mSelectedMovie?.toString())
        val row = DetailsOverviewRow(mSelectedMovie)
        row.imageDrawable = ContextCompat.getDrawable(activity, R.drawable.default_background)
        val width = convertDpToPixel(activity, DETAIL_THUMB_WIDTH)
        val height = convertDpToPixel(activity, DETAIL_THUMB_HEIGHT)
        Glide.with(activity)
                .load(mSelectedMovie?.img)
                .centerCrop()
                .error(R.drawable.default_background)
                .into<SimpleTarget<GlideDrawable>>(object : SimpleTarget<GlideDrawable>(width, height) {
                    override fun onResourceReady(resource: GlideDrawable,
                                                 glideAnimation: GlideAnimation<in GlideDrawable>) {
                        Log.d(TAG, "details overview card image url ready: " + resource)
                        row.imageDrawable = resource
                        mAdapter.notifyArrayItemRangeChanged(0, mAdapter.size())
                    }
                })

        val actionAdapter = ArrayObjectAdapter()
        val list = mVideoInfo.sources
        for (i in 0 until list.size) {
            actionAdapter.add(Action(i.toLong(), list[i].name))
        }
        row.actionsAdapter = actionAdapter

        mAdapter.add(row)
    }

    private fun setupDetailsOverviewRowPresenter() {
        // Set detail background.
        val detailsPresenter = FullWidthDetailsOverviewRowPresenter(DetailsDescriptionPresenter())
        detailsPresenter.backgroundColor =
                ContextCompat.getColor(activity, R.color.selected_background)

        // Hook up transition element.
        val sharedElementHelper = FullWidthDetailsOverviewSharedElementHelper()
        sharedElementHelper.setSharedElementEnterTransition(
                activity, DetailsActivity.SHARED_ELEMENT_NAME)
        detailsPresenter.setListener(sharedElementHelper)
        detailsPresenter.isParticipatingEntranceTransition = true

        detailsPresenter.onActionClickedListener = OnActionClickedListener { action ->
            //            val intent = Intent(activity, PlaybackActivity::class.java)
//            intent.putExtra(PlaybackActivity.MOVIE, mSelectedMovie)
//            intent.putExtra(PlaybackActivity.PLAY_URL, mVideoInfo.sources[action.id.toInt()].playUrl)
//            intent.putExtra(PlaybackActivity.DESC, mVideoInfo.desc)
//            startActivity(intent)
            val intent = Intent(activity, PlayActivity::class.java)
            intent.putExtra(PlayActivity.TITLE, mSelectedMovie?.name)
            intent.putExtra(PlayActivity.VIDEO_URL, mVideoInfo.sources[action.id.toInt()].playUrl)
            startActivity(intent)
        }
        mPresenterSelector.addClassPresenter(DetailsOverviewRow::class.java, detailsPresenter)
    }

//    private fun setupRelatedMovieListRow() {
//        val subcategories = arrayOf(getString(R.string.related_movies))
//        val list = MovieList.list
//
//        Collections.shuffle(list)
//        val listRowAdapter = ArrayObjectAdapter(CardPresenter())
//        for (j in 0 until NUM_COLS) {
//            listRowAdapter.add(list[j % 5])
//        }
//
//        val header = HeaderItem(0, subcategories[0])
//        mAdapter.add(ListRow(header, listRowAdapter))
//        mPresenterSelector.addClassPresenter(ListRow::class.java, ListRowPresenter())
//    }

    fun convertDpToPixel(context: Context, dp: Int): Int {
        val density = context.applicationContext.resources.displayMetrics.density
        return Math.round(dp.toFloat() * density)
    }

    private inner class ItemViewClickedListener : OnItemViewClickedListener {
        override fun onItemClicked(itemViewHolder: Presenter.ViewHolder?, item: Any?,
                                   rowViewHolder: RowPresenter.ViewHolder, row: Row) {
            if (item is Video) {
                Log.d(TAG, "Item: " + item.toString())
                val intent = Intent(activity, DetailsActivity::class.java)
                intent.putExtra(resources.getString(R.string.movie), mSelectedMovie)

                val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity,
                        (itemViewHolder?.view as ImageCardView).mainImageView,
                        DetailsActivity.SHARED_ELEMENT_NAME).toBundle()
                activity.startActivity(intent, bundle)
            }
        }
    }

    companion object {
        private val TAG = "VideoDetailsFragment";

        private val ACTION_WATCH_TRAILER = 1L
        private val ACTION_RENT = 2L
        private val ACTION_BUY = 3L

        private val DETAIL_THUMB_WIDTH = 274
        private val DETAIL_THUMB_HEIGHT = 274

        private val NUM_COLS = 10
    }
}