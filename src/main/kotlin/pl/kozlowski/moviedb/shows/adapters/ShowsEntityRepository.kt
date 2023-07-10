package pl.kozlowski.moviedb.shows.adapters

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface ShowsEntityRepository : JpaRepository<ShowEntity, Long> {
    @Query("SELECT s FROM ShowEntity s WHERE s.date BETWEEN :dateFrom AND :dateTo AND (:movieId IS NULL OR s.movieId = :movieId)")
    fun findByDateBetweenAndMovieId(dateFrom: LocalDateTime, dateTo: LocalDateTime, movieId: Long?): List<ShowEntity>
}
