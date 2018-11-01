package com.deligence.omdbmovierating.dataobjects

import com.google.gson.annotations.SerializedName

data class DataMovieDetail(@SerializedName("Title")     var title: String ="",
                           @SerializedName("Year")      var year: String ="",
                           @SerializedName("Rated")     var rated: String ="",
                           @SerializedName("Released")  var released: String ="",
                           @SerializedName("Runtime")   var runTime: String ="",
                           @SerializedName("Genre")     var gener: String ="",
                           @SerializedName("Director")  var director: String ="",
                           @SerializedName("Writer")    var writer: String ="",
                           @SerializedName("Actors")    var actors: String ="",
                           @SerializedName("Plot")      var plot: String ="",
                           @SerializedName("Country")   var country: String ="",
                           @SerializedName("Country")   var awards: String ="",
                           @SerializedName("Poster")    var poster: String ="",
                           @SerializedName("Metascore") var metascore: String ="",
                           @SerializedName("imdbRating") var imdbRating: String ="",
                           @SerializedName("imdbVotes") var imdbVotes: String ="",
                           @SerializedName("imdbID")    var imdbID: String ="",
                           @SerializedName("Type")      var type: String ="") {


}