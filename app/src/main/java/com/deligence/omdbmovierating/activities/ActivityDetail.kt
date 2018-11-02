package com.deligence.omdbmovierating.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.deligence.omdbmovierating.dataobjects.DataMovie
import com.deligence.omdbmovierating.R
import com.deligence.omdbmovierating.dataobjects.DataMovieDetail
import com.deligence.omdbmovierating.fragments.DialogFragmentRate
import com.deligence.omdbmovierating.models.ModelMovieDetail
import com.deligence.omdbmovierating.viewmodels.VMActivityDetail

class ActivityDetail: AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_DATAMOVIE= "EXTRA_DATAMOVIE"
    }
    lateinit var dataMovie: DataMovie
    lateinit var vmDetail: VMActivityDetail
    lateinit var imagePoster: ImageView

    lateinit var textName: TextView
    lateinit var textYear: TextView
    lateinit var textRated: TextView
    lateinit var textRunTime: TextView
    lateinit var textGener: TextView
    lateinit var textWriterValue: TextView
    lateinit var textDirectorValue: TextView
    lateinit var textPlotData: TextView
    lateinit var txtImdbRating: TextView
    lateinit var txtMetaScoreValue: TextView
    lateinit var txtImdbVotes: TextView
    lateinit var relativeRateThis: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(findViewById(R.id.homeToolBar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        dataMovie = intent.extras.getParcelable<DataMovie>(EXTRA_DATAMOVIE)

        imagePoster = findViewById(R.id.imgPoster)
        textName = findViewById(R.id.textName)
        textYear = findViewById(R.id.textYear)
        textRated = findViewById(R.id.textRated)
        textRunTime = findViewById(R.id.textRuntime)
        textGener = findViewById(R.id.textGener)
        textWriterValue = findViewById(R.id.textWriterValue)
        textDirectorValue = findViewById(R.id.textDirectorValue)
        textPlotData = findViewById(R.id.textPlotData)
        txtImdbRating = (findViewById<LinearLayout>(R.id.linearRatingBar))
                .findViewById<RelativeLayout>(R.id.relativeImdbRating).findViewById(R.id.txtImdbRating)
        txtImdbVotes = (findViewById<LinearLayout>(R.id.linearRatingBar))
                .findViewById<RelativeLayout>(R.id.relativeImdbRating).findViewById(R.id.txtImdbVotes)
        txtMetaScoreValue = (findViewById<LinearLayout>(R.id.linearRatingBar))
                .findViewById<RelativeLayout>(R.id.relativeMetascore).findViewById(R.id.txtMetaScoreValue)
        relativeRateThis = (findViewById<LinearLayout>(R.id.linearRatingBar)).findViewById(R.id.relativeRateThis)
        relativeRateThis.setOnClickListener(this)



        var vmDetail = ViewModelProviders.of(this).get(VMActivityDetail::class.java)
        vmDetail.getMovie(dataMovie.imdbId).observe(this, Observer<DataMovieDetail> {
            dataDetail ->
            Log.d("WASTE","poster:: "+dataDetail?.poster)

            Glide.with(this)
                    .load(dataDetail?.poster)
                    .into(imagePoster)

            textName.text = dataDetail?.title
            textYear.text = dataDetail?.year
            textRated.text = dataDetail?.rated
            textRunTime.text = dataDetail?.runTime
            textGener.text = dataDetail?.gener
            textWriterValue.text =dataDetail?.writer
            textDirectorValue.text =dataDetail?.director
            textPlotData.text = dataDetail?.plot
            txtImdbRating.text = dataDetail?.imdbRating
            txtMetaScoreValue.text = dataDetail?.metascore
            txtImdbVotes.text = dataDetail?.imdbVotes

        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return true
    }

    override fun onClick(view: View?) {

        when(view?.id){
            R.id.relativeRateThis -> {

                val dialog = DialogFragmentRate()
                dialog.show(supportFragmentManager,"")
            }
        }
    }
}