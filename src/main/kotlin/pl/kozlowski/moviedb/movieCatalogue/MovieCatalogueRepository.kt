package pl.kozlowski.moviedb.movieCatalogue

interface MovieCatalogueRepository {
    fun findByImdbIdIgnoreCase(imdbId: String): List<Movie>
    fun findByTitleContainingIgnoreCase(title: String): List<Movie>
    fun save(movie: Movie) : Movie
    fun findAll(): List<Movie>
}