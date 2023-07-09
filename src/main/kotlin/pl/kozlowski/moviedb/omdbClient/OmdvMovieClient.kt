package pl.kozlowski.moviedb.omdbClient

import org.springframework.stereotype.Component
import pl.kozlowski.moviedb.movieCatalogue.MovieClient

@Component
class OmdbMovieClient(
    private val omdbMovieHttpClient: OmdbMovieHttpClient
) : MovieClient {
    override fun getMovie(
        imdbID: String?,
        title: String?,
    ) = omdbMovieHttpClient.getMovie(imdbID, title)
        .toDomainMovie()
}
