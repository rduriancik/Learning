package com.example.robert.kedditmvvm

import android.os.Parcel
import android.os.Parcelable
import com.example.robert.kedditmvvm.common.adapter.AdapterConstants
import com.example.robert.kedditmvvm.common.adapter.ViewType
import com.example.robert.kedditmvvm.common.extensions.createParcel

/**
 * Created by robert on 12.10.2017.
 */
data class RedditNews(val after: String,
                      val before: String,
                      val news: List<RedditNewsItem>) : Parcelable {

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<RedditNews> = createParcel { RedditNews(it) }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            mutableListOf<RedditNewsItem>().apply {
                source.readTypedList(this, RedditNewsItem.CREATOR)
            }
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(after)
        writeString(before)
        writeTypedList(news)
    }
}

data class RedditNewsItem(val author: String,
                          val title: String,
                          val numComments: Int,
                          val created: Long,
                          val thumbnail: String,
                          val url: String) : ViewType, Parcelable {

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<RedditNewsItem> = createParcel { RedditNewsItem(it) }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readLong(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(author)
        writeString(title)
        writeInt(numComments)
        writeLong(created)
        writeString(thumbnail)
        writeString(url)
    }

    override fun getViewType() = AdapterConstants.NEWS

}