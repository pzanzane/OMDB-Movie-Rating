package com.deligence.omdbmovierating.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.deligence.omdbmovierating.dataobjects.DataMovie
import com.deligence.omdbmovierating.models.ModelHome
import com.deligence.omdbmovierating.repository.RepositoryMovie

class VMActivityHome: ViewModel(){

    private  var liveDatamodelHome: MutableLiveData<ModelHome> = MutableLiveData<ModelHome>()

    fun getMovies(): LiveData<ModelHome> {

        if(liveDatamodelHome.value == null || liveDatamodelHome.value!!.listDataMovies == null){
            loadMovies()
        }
        return  liveDatamodelHome
    }

    private fun loadMovies(){

        val repositoryMovie = RepositoryMovie()


        repositoryMovie.search("dunkirk",2017) { list, error ->

            Log.d("WASTE","Listsize: "+list?.size)
            Log.d("WASTE","Error: "+ error?.message)

            val modelHome = ModelHome()
            modelHome.listDataMovies = list
            modelHome.errorMessage = error?.message

            liveDatamodelHome.postValue(modelHome)
        }
    }


}
