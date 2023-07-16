package pl.kozlowski.moviedb.shows

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
class ShowsController(
    private val showsService: ShowsService
) {
    @PutMapping("/shows")
    @PreAuthorize("hasRole('OWNER')")
    @Operation(security = [SecurityRequirement(name = "basicAuth")])
    fun addShow(@RequestBody show: Show) {
        showsService.addShow(show)
    }

    @GetMapping("/shows")
    fun getShows(
        @RequestParam(name = "dateFrom", defaultValue = "#{T(java.time.LocalDateTime).now()}")
        dateFrom: LocalDateTime,
        @RequestParam(name = "dateTo", defaultValue = "#{T(java.time.LocalDateTime).now().plusWeeks(1)}")
        dateTo: LocalDateTime,
        movieId: Long?
    ): List<Show> {
        return showsService.findByDateBetweenAndMovieId(dateFrom, dateTo, movieId)
    }
}