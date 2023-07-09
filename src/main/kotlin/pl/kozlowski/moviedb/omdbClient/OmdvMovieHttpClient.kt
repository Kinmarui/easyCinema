package pl.kozlowski.moviedb.omdbClient

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "omdbMovieClient",
    url = "https://www.omdbapi.com",
)
interface OmdbMovieHttpClient {
    @GetMapping(
        value = ["/"],
        consumes = [APPLICATION_JSON_VALUE],
    )
    fun getMovie(
        @RequestParam(name = "i", required = false) imdbID: String? = null,
        @RequestParam(name = "t", required = false) title: String? = null,
    ): OmdbResponse
}
