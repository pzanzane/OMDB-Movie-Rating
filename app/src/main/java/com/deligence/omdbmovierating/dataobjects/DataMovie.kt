package com.deligence.omdbmovierating.dataobjects

import com.google.gson.annotations.SerializedName

data class DataMovie(@SerializedName("imdbID") var imdbId: String="",
                     @SerializedName("Title") var name: String="",
                     @SerializedName("Year") var year: String,
                     @SerializedName("Type") var type: String = "")