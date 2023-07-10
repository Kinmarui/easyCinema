package pl.kozlowski.moviedb.shows

import java.time.LocalDateTime

data class Show(
    val movieId: Long,
    val price: Long, // price in cents (1/100)
    val date: LocalDateTime,
    val id: Long = 0L
)