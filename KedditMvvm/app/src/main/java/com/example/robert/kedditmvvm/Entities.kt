package com.example.robert.kedditmvvm

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Created by robert on 12.10.2017.
 */
//data class RedditNews(val after: String,
//                      val before: String,
//                      val news: List<RedditNewsItem>) : Parcelable {
//    constructor(source: Parcel) : this(
//            source.readString(),
//            source.readString(),
//            source.createTypedArrayList(RedditNewsItem.CREATOR)
//    )
//
//    override fun describeContents() = 0
//
//    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
//        writeString(after)
//        writeString(before)
//        writeTypedList(news)
//    }
//
//    companion object {
//        @JvmField
//        val CREATOR: Parcelable.Creator<RedditNews> = object : Parcelable.Creator<RedditNews> {
//            override fun createFromParcel(source: Parcel): RedditNews = RedditNews(source)
//            override fun newArray(size: Int): Array<RedditNews?> = arrayOfNulls(size)
//        }
//    }
//}

data class RedditNewsItem(val author: String,
                          val title: String,
                          val numComments: Int,
                          val created: Long,
                          val thumbnail: String,
                          val url: String,
                          val id: String) : /*ViewType,*/ Parcelable {

//    override fun getViewType() = AdapterConstants.NEWS

    fun getFriendlyTime(): String {
        val dateTime = Date(1000 * created)
        val sb = StringBuffer()
        val current = Calendar.getInstance().time
        var diffInSeconds = ((current.time - dateTime.time) / 1000).toInt()

        val sec = if (diffInSeconds >= 60) diffInSeconds % 60 else diffInSeconds
        diffInSeconds /= 60
        val min = if (diffInSeconds >= 60) diffInSeconds % 60 else diffInSeconds
        diffInSeconds /= 60
        val hrs = if (diffInSeconds >= 24) diffInSeconds % 24 else diffInSeconds
        diffInSeconds /= 24
        val days = if (diffInSeconds >= 30) diffInSeconds % 30 else diffInSeconds
        diffInSeconds /= 30
        val months = if (diffInSeconds >= 12) diffInSeconds % 12 else diffInSeconds
        diffInSeconds /= 12
        val years = diffInSeconds

        if (years > 0) {
            if (years == 1) {
                sb.append("a year")
            } else {
                sb.append("$years years")
            }
            if (years <= 6 && months > 0) {
                if (months == 1) {
                    sb.append(" and a month")
                } else {
                    sb.append(" and $months months")
                }
            }
        } else if (months > 0) {
            if (months == 1) {
                sb.append("a month")
            } else {
                sb.append("$months months")
            }
            if (months <= 6 && days > 0) {
                if (days == 1) {
                    sb.append(" and a day")
                } else {
                    sb.append(" and $days days")
                }
            }
        } else if (days > 0) {
            if (days == 1) {
                sb.append("a day")
            } else {
                sb.append("$days days")
            }
            if (days <= 3 && hrs > 0) {
                if (hrs == 1) {
                    sb.append(" and an hour")
                } else {
                    sb.append(" and $hrs hours")
                }
            }
        } else if (hrs > 0) {
            if (hrs == 1) {
                sb.append("an hour")
            } else {
                sb.append("$hrs hours")
            }
            if (min > 1) {
                sb.append(" and $min minutes")
            }
        } else if (min > 0) {
            if (min == 1) {
                sb.append("a minute")
            } else {
                sb.append("$min minutes")
            }
            if (sec > 1) {
                sb.append(" and $sec seconds")
            }
        } else {
            if (sec <= 1) {
                sb.append("about a second")
            } else {
                sb.append("about $sec seconds")
            }
        }

        sb.append(" ago")

        return sb.toString()
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readLong(),
            source.readString(),
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
        writeString(id)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<RedditNewsItem> = object : Parcelable.Creator<RedditNewsItem> {
            override fun createFromParcel(source: Parcel): RedditNewsItem = RedditNewsItem(source)
            override fun newArray(size: Int): Array<RedditNewsItem?> = arrayOfNulls(size)
        }
    }
}