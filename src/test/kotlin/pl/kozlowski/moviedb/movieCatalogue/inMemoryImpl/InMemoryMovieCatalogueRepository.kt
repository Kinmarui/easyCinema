package pl.kozlowski.moviedb.movieCatalogue.inMemoryImpl

import pl.kozlowski.moviedb.movieCatalogue.Movie
import pl.kozlowski.moviedb.movieCatalogue.MovieCatalogueRepository

class InMemoryMovieCatalogueRepository : MovieCatalogueRepository {

    private val data: MutableMap<Long, Movie> = mutableMapOf()

    override fun findByImdbIdIgnoreCase(imdbId: String): List<Movie> =
        data.values.filter { it.imdbId.equals(imdbId, ignoreCase = true) }

    override fun findByTitleContainingIgnoreCase(title: String): List<Movie> =
        data.values.filter { it.title.contains(title, ignoreCase = true) }

    override fun save(movie: Movie): Movie {
        val toPersist = if (movie.id <= 0L) movie.copy(id = data.size.toLong()) else movie
        data[toPersist.id] = toPersist
        return toPersist
    }

    override fun findAll(): List<Movie> = data.values.toList()
}