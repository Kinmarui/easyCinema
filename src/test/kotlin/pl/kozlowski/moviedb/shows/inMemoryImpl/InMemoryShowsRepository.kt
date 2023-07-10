package pl.kozlowski.moviedb.shows.inMemoryImpl

import pl.kozlowski.moviedb.shows.Show
import pl.kozlowski.moviedb.shows.ShowsRepository
import java.time.LocalDateTime

class InMemoryShowsRepository : ShowsRepository {

    private val data: MutableMap<Long, Show> = mutableMapOf()

    override fun save(show: Show): Show {
        val toPersist = if (show.id <= 0L) show.copy(id = data.size.toLong()+1L) else show
        data[toPersist.id] = toPersist
        return toPersist
    }

    override fun findByDateBetweenAndMovieId(
        dateFrom: LocalDateTime,
        dateTo: LocalDateTime,
        movieId: Long?
    ): List<Show> {
        // if provided filter by movieId first
        val movies =
            if (movieId == null)
                data.values
            else
                data.values.filter { it.movieId == movieId }

        return movies.filter {
            it.date.isAfter(dateFrom)
                && it.date.isBefore(dateTo)
        }
    }
}