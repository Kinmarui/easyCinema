package pl.kozlowski.moviedb.movieCatalogue

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pl.kozlowski.moviedb.omdbClient.OmdbMovieClient

@RestController
 class MovieController(
    private val movieClient: OmdbMovieClient

) {
    @GetMapping("/movies")
    fun getMovie(
        @RequestParam(name = "i", required = false) imdbID: String?,
        @RequestParam(name = "t", required = false) title: String?,
    ): List<Movie> {
        if(imdbID == null && title == null)
            return listOf()
        return listOf(movieClient.getMovie(imdbID, title).toDomainMovie())
    }
}