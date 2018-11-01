package com.deligence.omdbmovierating.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.deligence.omdbmovierating.dataobjects.DataMovie
import com.deligence.omdbmovierating.R
import com.deligence.omdbmovierating.dataobjects.DataMovieDetail
import com.deligence.omdbmovierating.models.ModelMovieDetail
import com.deligence.omdbmovierating.viewmodels.VMActivityDetail

class ActivityDetail: AppCompatActivity() {

    companion object {
        const val EXTRA_DATAMOVIE= "EXTRA_DATAMOVIE"
    }
    lateinit var dataMovie: DataMovie
    lateinit var vmDetail: VMActivityDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(findViewById(R.id.homeToolBar))

        dataMovie = intent.extras.getParcelable<DataMovie>(EXTRA_DATAMOVIE)

        Log.d("WASTE","dataMovie.name:: "+dataMovie.name)

        var vmDetail = ViewModelProviders.of(this).get(VMActivityDetail::class.java)
        vmDetail.getMovie(dataMovie.imdbId).observe(this, Observer<DataMovieDetail> {
            dataDetail ->
            Log.d("WASTE","imdbRating:: "+dataDetail?.imdbRating)
        })
    }


}