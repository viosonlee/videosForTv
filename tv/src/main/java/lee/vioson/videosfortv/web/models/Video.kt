package lee.vioson.videosfortv.web.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose

/**
 * Created by viosonlee
 * on 2017/8/18.
 * todo
 */
class Video(@Expose var publishTime: Int,
            @Expose var score: Double,
            @Expose var doubanId: String,
            @Expose var img: String,
            @Expose var movieTypeName: String,
            @Expose var album: Boolean,
            @Expose var name: String,
            @Expose var movieId: Long,
            @Expose var status: String,
            @Expose var lastUpdateTime: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readDouble(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readString(),
            parcel.readLong(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(publishTime)
        parcel.writeDouble(score)
        parcel.writeString(doubanId)
        parcel.writeString(img)
        parcel.writeString(movieTypeName)
        parcel.writeByte(if (album) 1 else 0)
        parcel.writeString(name)
        parcel.writeLong(movieId)
        parcel.writeString(status)
        parcel.writeString(lastUpdateTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Video> {
        override fun createFromParcel(parcel: Parcel): Video {
            return Video(parcel)
        }

        override fun newArray(size: Int): Array<Video?> {
            return arrayOfNulls(size)
        }
    }
}