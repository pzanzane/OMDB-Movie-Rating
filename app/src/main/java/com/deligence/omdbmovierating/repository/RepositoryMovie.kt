package com.deligence.omdbmovierating.repository

import com.deligence.omdbmovierating.constants.ServerConstants
import com.deligence.omdbmovierating.dataobjects.DataMovie
import com.deligence.omdbmovierating.dataobjects.DataMovieDetail
import com.deligence.omdbmovierating.retrofit.*
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

    fun search(movieName: String, closure: (List<DataMovie>?, ErrorMessage?) -> Unit){

        val clientMovie = RestClient.createService(ClientMovie::class.java)
        clientMovie.getAllMovies(ServerConstants.key, movieName).enqueue(object : Callback<ResponseGetMovies>{

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

    fun getMovieDetail(imdbId: String,closure: (DataMovieDetail?, ErrorMessage?) -> Unit){

        val clientMovie = RestClient.createService(ClientMovie::class.java)
        clientMovie.getMovie(ServerConstants.key, imdbId).enqueue(object : Callback<ResponseMovieDetail>{

            override fun onResponse(call: Call<ResponseMovieDetail>,
                                    response: Response<ResponseMovieDetail>) {

                if(response.isSuccessful){


                    val responseObject: ResponseMovieDetail = response.body()!!

                    if(responseObject.isSuccess){

                        val data = getData(responseObject)
                        closure(data, null)

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

            override fun onFailure(call: Call<ResponseMovieDetail>, t: Throwable) {

                val error = ErrorMessage()
                error.message = t.message
                closure(null, error)
            }
        })
    }

    fun getData(responseDetail: ResponseMovieDetail): DataMovieDetail{

        val dataDetail = DataMovieDetail()

        dataDetail.title        = responseDetail.title
        dataDetail.year         = responseDetail.year      
        dataDetail.rated        = responseDetail.rated
        dataDetail.released     = responseDetail.released
        dataDetail.runTime      = responseDetail.runTime
        dataDetail.gener        = responseDetail.gener
        dataDetail.director     = responseDetail.director
        dataDetail.writer       = responseDetail.writer
        dataDetail.actors       = responseDetail.actors
        dataDetail.plot         = responseDetail.plot
        dataDetail.country      = responseDetail.country
        dataDetail.awards       = responseDetail.awards
        dataDetail.poster       = responseDetail.poster
        dataDetail.metascore    = responseDetail.metascore
        dataDetail.imdbRating   = responseDetail.imdbRating
        dataDetail.imdbVotes    = responseDetail.imdbVotes
        dataDetail.imdbID       = responseDetail.imdbID
        dataDetail.type         = responseDetail.type

        return dataDetail


    }
}