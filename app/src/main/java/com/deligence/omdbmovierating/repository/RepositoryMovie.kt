package com.deligence.omdbmovierating.repository

import android.arch.lifecycle.LiveData
import com.clevertrap.basicsetupkotlin.retrofit.ErrorMessage
import com.clevertrap.basicsetupkotlin.retrofit.RestClient
import com.deligence.omdbmovierating.constants.ServerConstants
import com.deligence.omdbmovierating.dataobjects.DataMovie
import com.deligence.omdbmovierating.retrofit.ClientMovie
import com.deligence.omdbmovierating.retrofit.ResponseGetMovies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryMovie{

    fun search(movieName: String, year: Int, closure: (List<DataMovie>?, ErrorMessage?) -> Unit){

        val clientMovie = RestClient.createService(ClientMovie::class.java)
        clientMovie.getAllMovies(ServerConstants.key, movieName, year).enqueue(object : Callback<ResponseGetMovies>{

            override fun onResponse(call: Call<ResponseGetMovies>,
                                    response: Response<ResponseGetMovies>) {

                if(response.isSuccessful){


                    val responseObject: ResponseGetMovies = response.body()!!

                    if(responseObject.isSuccess){

                        val listData = responseObject.data
                        closure(listData, null)

                    }else{

                        val error = ErrorMessage()
                        error.message = responseObject.errorMessage
                        closure(null, error)
                    }

                }else{

                    val error = RestClient.convertError(response.errorBody())
                    closure(null, error)
                }

            }

            override fun onFailure(call: Call<ResponseGetMovies>, t: Throwable) {

                val error = ErrorMessage()
                error.message = t.message
                closure(null, error)
            }
        })
    }
}