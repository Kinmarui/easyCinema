package pl.kozlowski.moviedb.shows.adapters

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class ShowEntity(
    val movieId: Long,
    val price: Long, // price in cents (1/100)
    val date: LocalDateTime,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)