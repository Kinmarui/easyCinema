package pl.kozlowski.moviedb.shows

import java.time.LocalDateTime

interface ShowsRepository {
    fun save(show: Show): Show
    fun findByDateBetweenAndMovieId(dateFrom: LocalDateTime, dateTo: LocalDateTime, movieId: Long?): List<Show>
}
