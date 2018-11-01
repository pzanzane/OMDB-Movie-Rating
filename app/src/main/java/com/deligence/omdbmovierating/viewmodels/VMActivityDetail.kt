package com.deligence.omdbmovierating.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.deligence.omdbmovierating.R
import com.deligence.omdbmovierating.application.ApplicationOmdbMovieRating
import com.deligence.omdbmovierating.dataobjects.DataMovieDetail
import com.deligence.omdbmovierating.models.ModelMovieDetail
import com.deligence.omdbmovierating.repository.RepositoryMovie
import com.deligence.omdbmovierating.utility.NetworkHelper
import com.deligence.omdbmovierating.utility.ToastUtils

class VMActivityDetail: ViewModel (){

    private  var liveDataModelDetail: MutableLiveData<DataMovieDetail> = MutableLiveData<DataMovieDetail>()

    fun getMovie(imdbId: String): LiveData<DataMovieDetail> {

        if(liveDataModelDetail.value == null){
            loadMovies(imdbId)
        }
        return  liveDataModelDetail
    }

    private fun loadMovies(imdbId: String){

        if(!NetworkHelper.isOnline(ApplicationOmdbMovieRating.applicationContext())){
            ToastUtils.showToast(ApplicationOmdbMovieRating.applicationContext().getString(R.string.no_network))
            return
        }

        val repositoryMovie = RepositoryMovie()
        repositoryMovie.getMovieDetail(imdbId) { dataDetail, error ->

            val modelMovieDetail = ModelMovieDetail()
            modelMovieDetail.dataMovieDetail = dataDetail
            modelMovieDetail.errorMessage = error?.message

            liveDataModelDetail.postValue(dataDetail)
        }
    }
}