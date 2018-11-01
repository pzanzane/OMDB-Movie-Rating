package com.deligence.omdbmovierating.retrofit

import android.arch.lifecycle.LiveData
import com.deligence.omdbmovierating.constants.ServerConstants
import com.deligence.omdbmovierating.dataobjects.DataMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ClientMovie {


    @GET(ServerConstants.BASE_URL)
    fun getAllMovies(
                    @Query(ServerConstants.APIKEY) apiKey: String,
                  @Query(ServerConstants.SEARCH_KEY) search: String,
                  @Query(ServerConstants.YEAR_KEY) year: Int): Call<ResponseGetMovies>


    @GET(ServerConstants.BASE_URL)
    fun getMovie(@Query(ServerConstants.APIKEY) apiKey: String,
                 @Query(ServerConstants.ID_KEY) imdbId: String): Call<LiveData<DataMovie>>
}