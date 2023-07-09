package pl.kozlowski.moviedb.movieCatalogue.inMemoryImpl

import pl.kozlowski.moviedb.movieCatalogue.Movie
import pl.kozlowski.moviedb.movieCatalogue.MovieClient

class InMemoryMovieClient : MovieClient {

    private val inMemoryStore = InMemoryMovieCatalogueRepository()

    override fun getMovie(imdbID: String?, title: String?): Movie? =
        when {
            imdbID != null -> inMemoryStore.findByImdbIdIgnoreCase(imdbID).firstOrNull()
            title != null -> inMemoryStore.findByTitleContainingIgnoreCase(title).firstOrNull()
            else -> throw IllegalArgumentException("imdbID or title must be provided")
        }

    internal fun save(movie: Movie): Movie = inMemoryStore.save(movie)
}

