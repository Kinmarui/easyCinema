package pl.kozlowski.moviedb.shows

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ShowsService(
    private val showsRepository: ShowsRepository
) {
    fun addShow(show: Show) {
        showsRepository.save(show)
    }

    fun findByDateBetweenAndMovieId(dateFrom: LocalDateTime, dateTo: LocalDateTime, movieId: Long?): List<Show> {
        return showsRepository.findByDateBetweenAndMovieId(dateFrom, dateTo, movieId)
    }
}
