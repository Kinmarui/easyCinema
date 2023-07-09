package pl.kozlowski.moviedb.movieCatalogue

import org.springframework.stereotype.Service

@Service
class MovieCatalogueService(
    private val movieClient: MovieClient,
    private val movieCatalogueRepository: MovieCatalogueRepository
) {
    fun getMovie(
        imdbID: String? = null,
        title: String? = null,
    ): List<Movie> {
        return when {
            imdbID != null -> movieCatalogueRepository.findByImdbIdIgnoreCase(imdbID).ifEmpty {
                movieClient.getMovie(imdbID = imdbID)
                    ?.let(movieCatalogueRepository::save)
                    ?.let { listOf(it) } ?: emptyList()
            }

            title != null -> movieCatalogueRepository.findByTitleContainingIgnoreCase(title).ifEmpty {
                movieClient.getMovie(title = title)
                    ?.let(movieCatalogueRepository::save)
                    ?.let { listOf(it) } ?: emptyList()
            }

            else -> movieCatalogueRepository.findAll()
        }
    }
}