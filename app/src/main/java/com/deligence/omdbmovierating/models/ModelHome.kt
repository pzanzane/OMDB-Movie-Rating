package com.deligence.omdbmovierating.models

import android.arch.lifecycle.LiveData
import com.deligence.omdbmovierating.dataobjects.DataMovie

class ModelHome {

    var listDataMovies: List<DataMovie>? = null
    var errorMessage: String? = null

}