package com.deligence.omdbmovierating.retrofit

import android.arch.lifecycle.LiveData
import com.deligence.omdbmovierating.dataobjects.DataMovie
import com.google.gson.annotations.SerializedName

class ResponseGetMovies {

    @SerializedName("Response")
    var isSuccess: Boolean = false

    @SerializedName("Search")
    var data: List<DataMovie>? = null

    @SerializedName("Error")
    var errorMessage: String? = null
}