package com.deligence.omdbmovierating.application

import android.app.Application
import android.content.Context
import android.util.DisplayMetrics
import android.util.Log

class ApplicationOmdbMovieRating: Application() {


    override fun onCreate() {
        super.onCreate()

        instance = this
        toastDeviceConfig(true)
    }

    companion object {
        private var instance: ApplicationOmdbMovieRating? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }

    }



    private fun toastDeviceConfig(showDialog: Boolean) {


        var displayMetrics: DisplayMetrics = resources.displayMetrics
        var dpHeight: Float = displayMetrics.heightPixels / displayMetrics.density
        var dpWidth: Float = displayMetrics.widthPixels / displayMetrics.density

        if(showDialog) {


            var msg: StringBuilder = StringBuilder()
            msg.append("Pixels:           ")
            msg.append(displayMetrics.widthPixels)
            msg.append("\n")
            msg.append(" X ")
            msg.append(displayMetrics.heightPixels)
            msg.append("\n")
            msg.append("dpHeight:         ")
            msg.append(dpHeight)
            msg.append("\n")
            msg.append("dpWidth:         ")
            msg.append(dpWidth)
            msg.append("\n")
            msg.append("density:         ")
            msg.append(displayMetrics.density)
            msg.append("\n")
            msg.append("densityDpi:         ")
            msg.append(displayMetrics.densityDpi)
            msg.append("\n")
            Log.d("WASTE",msg.toString())


        }
        }

}