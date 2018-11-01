package com.deligence.omdbmovierating.dataobjects

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class DataMovie(@SerializedName("imdbID") var imdbId: String="",
                     @SerializedName("Title") var name: String="",
                     @SerializedName("Year") var year: String,
                     @SerializedName("Type") var type: String = "",
                     @SerializedName("Poster") var posterUrl: String = "") : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imdbId)
        parcel.writeString(name)
        parcel.writeString(year)
        parcel.writeString(type)
        parcel.writeString(posterUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataMovie> {
        override fun createFromParcel(parcel: Parcel): DataMovie {
            return DataMovie(parcel)
        }

        override fun newArray(size: Int): Array<DataMovie?> {
            return arrayOfNulls(size)
        }
    }
}