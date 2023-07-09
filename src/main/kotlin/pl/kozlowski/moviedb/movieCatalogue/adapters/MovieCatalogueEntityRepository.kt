package pl.kozlowski.moviedb.movieCatalogue.adapters

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MovieCatalogueEntityRepository : JpaRepository<MovieEntity, Long> {
    fun findByImdbIdIgnoreCase(imdbId: String): List<MovieEntity>

    @Query("SELECT m FROM MovieEntity m WHERE lower(m.title) LIKE lower(concat('%', :title, '%')) ORDER BY m.title ASC")
    fun findByTitleContainsIgnoreCaseOrderByTitleAsc(title: String): List<MovieEntity>
}