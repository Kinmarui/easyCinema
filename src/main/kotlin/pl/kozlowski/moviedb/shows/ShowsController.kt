package pl.kozlowski.moviedb.shows

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class ShowsController(
    private val showsService: ShowsService
) {
    @PutMapping("/shows")
    fun addShow(show: Show) {
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