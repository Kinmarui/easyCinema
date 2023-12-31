package pl.kozlowski.moviedb.movieCatalogue

data class Movie(
    val title: String,
    val releaseYear: String,
    val rated: String,
    val released: String,
    val runtime: String,
    val genre: String,
    val director: String,
    val writer: String,
    val actors: String,
    val plot: String,
    val language: String,
    val country: String,
    val awards: String,
    val poster: String,
    val ratings: List<Rating>,
    val metascore: String,
    val imdbRating: String,
    val imdbVotes: String,
    val imdbId: String,
    val type: String,
    val dvd: String,
    val boxOffice: String,
    val production: String,
    val website: String,
    val id: Long = 0L
)

data class Rating(
    val source: String,
    val value: String,
)