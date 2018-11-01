package com.deligence.omdbmovierating.utility

import android.widget.Toast
import com.deligence.omdbmovierating.ApplicationOmdbMovieRating


class ToastUtils {

    companion object {

        private var toast: Toast? = null

        fun showToast(str: String) {

            if (toast == null){
                toast = Toast.makeText(
                        ApplicationOmdbMovieRating.applicationContext(), str,
                        Toast.LENGTH_SHORT)
            }

            toast!!.setText(str)
            toast!!.show()
        }

    }
}