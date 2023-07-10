package pl.kozlowski.moviedb.shows.adapters

import org.springframework.stereotype.Component
import pl.kozlowski.moviedb.shows.Show
import pl.kozlowski.moviedb.shows.ShowsRepository
import java.time.LocalDateTime

@Component
class DbShowsRepository(
    private val showsEntityRepository: ShowsEntityRepository
) : ShowsRepository {
    override fun save(show: Show) = showsEntityRepository.save(show.toDbEntity()).toDomainEntity()

    override fun findByDateBetweenAndMovieId(
        dateFrom: LocalDateTime,
        dateTo: LocalDateTime,
        movieId: Long?
    ): List<Show> =
        showsEntityRepository.findByDateBetweenAndMovieId(dateFrom, dateTo, movieId).map { it.toDomainEntity() }
}

private fun ShowEntity.toDomainEntity() = Show(
    movieId = this.movieId,
    price = this.price,
    date = this.date,
    id = this.id!!
)

private fun Show.toDbEntity() = ShowEntity(
    movieId = this.movieId,
    price = this.price,
    date = this.date,
    id = if (id > 0L) id else null
)
