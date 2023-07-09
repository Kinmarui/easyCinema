package pl.kozlowski.moviedb.movieCatalogue.adapters

import io.hypersistence.utils.hibernate.type.json.JsonType
import jakarta.persistence.*
import org.hibernate.annotations.Type

@Entity
class MovieEntity(
    val title: String,
    val releaseYear: String,
    val rated: String,
    val released: String,
    val runtime: String,
    val genre: String,
    val director: String,
    val writer: String,
    val actors: String,
    val plot: String,
    val language: String,
    val country: String,
    val awards: String,
    val poster: String,
    @Type(JsonType::class)
    @Column(columnDefinition = "json")
    val ratings: List<RatingEntity>,
    val metascore: String,
    val imdbRating: String,
    val imdbVotes: String,
    val imdbId: String,
    val type: String,
    val dvd: String,
    val boxOffice: String,
    val production: String,
    val website: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
)

data class RatingEntity(
    val source: String,
    val value: String,
)
