package pl.kozlowski.moviedb.movieCatalogue

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MovieCatalogueController(
    private val movieCatalogueService: MovieCatalogueService
) {
    @GetMapping("/movies")
    fun getMovie(
        @RequestParam(name = "imdbID", required = false) imdbID: String?,
        @RequestParam(name = "title", required = false) title: String?,
    ): List<Movie> {
        return movieCatalogueService.getMovie(imdbID, title)
    }
}