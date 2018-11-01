package com.deligence.omdbmovierating.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.deligence.omdbmovierating.ApplicationOmdbMovieRating
import com.deligence.omdbmovierating.dataobjects.DataMovie
import com.deligence.omdbmovierating.models.ModelHome
import com.deligence.omdbmovierating.repository.RepositoryMovie
import com.deligence.omdbmovierating.utility.NetworkHelper
import com.deligence.omdbmovierating.utility.ToastUtils
import com.deligence.omdbmovierating.R
class VMActivityHome: ViewModel(){

    private  var liveDatamodelHome: MutableLiveData<ModelHome> = MutableLiveData<ModelHome>()

    fun getMovies(): LiveData<ModelHome> {

        if(liveDatamodelHome.value == null || liveDatamodelHome.value!!.listDataMovies == null){
            loadMovies("2018",2018)
        }
        return  liveDatamodelHome
    }

    private fun loadMovies(searchtext: String, year: Int){

        if(!NetworkHelper.isOnline(ApplicationOmdbMovieRating.applicationContext())){
            ToastUtils.showToast(ApplicationOmdbMovieRating.applicationContext().getString(R.string.no_network))
            noNetworkError()
            return
        }

        val repositoryMovie = RepositoryMovie()
        repositoryMovie.search(searchtext,year) { list, error ->

            val modelHome = ModelHome()
            modelHome.listDataMovies = list
            modelHome.errorMessage = error?.message

            liveDatamodelHome.postValue(modelHome)
        }
    }

    fun loadMovies(searchtext: String){

        if(!NetworkHelper.isOnline(ApplicationOmdbMovieRating.applicationContext())){
            ToastUtils.showToast(ApplicationOmdbMovieRating.applicationContext().getString(R.string.no_network))
            noNetworkError()
            return
        }

        val repositoryMovie = RepositoryMovie()
        repositoryMovie.search(searchtext) { list, error ->

            val modelHome = ModelHome()
            modelHome.listDataMovies = list
            modelHome.errorMessage = error?.message

            liveDatamodelHome.postValue(modelHome)
        }
    }

    fun noNetworkError(){
        val modelHome = ModelHome()
        modelHome.listDataMovies = null
        modelHome.errorMessage = ApplicationOmdbMovieRating.applicationContext().getString(R.string.no_network)

        liveDatamodelHome.postValue(modelHome)
    }

}
