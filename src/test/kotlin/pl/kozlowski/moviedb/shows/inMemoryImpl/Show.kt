package pl.kozlowski.moviedb.shows.inMemoryImpl

import pl.kozlowski.moviedb.shows.Show
import java.time.LocalDateTime

abstract class ShowDataProvider {
    companion object {
        val SHOW1 = Show(
            movieId = 1L,
            price = 3900L,
            date = LocalDateTime.of(2023, 7, 10, 16, 0),
            id = 0L
        )
        val SHOW2 = Show(
            movieId = 2L,
            price = 3300L,
            date = LocalDateTime.of(2023, 7, 12, 19, 0),
            id = 0L
        )
        val expectedTestTimeRangeMin: LocalDateTime = LocalDateTime.of(2023, 7, 10, 0, 0)
        val expectedTestTimeRangeMax: LocalDateTime = LocalDateTime.of(2023, 9, 10, 23, 59)

        fun createOneWeekOfFirstMovieShows(): List<Show> = (0..6).map { day ->
            SHOW1.copy(
                date = SHOW1.date.plusDays(day.toLong())
            )
        }

        fun createOneWeekOfSecondMovieShows(): List<Show> = (0..6).map { day ->
            SHOW2.copy(
                date = SHOW2.date.plusDays(day.toLong()),
            )
        }
    }
}