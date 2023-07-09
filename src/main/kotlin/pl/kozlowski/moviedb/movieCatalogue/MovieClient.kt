package pl.kozlowski.moviedb.movieCatalogue

interface MovieClient {
    fun getMovie(
        imdbID: String? = null,
        title: String? = null,
    ): Movie?
}
