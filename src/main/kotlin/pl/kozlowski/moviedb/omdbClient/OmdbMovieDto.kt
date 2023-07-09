package pl.kozlowski.moviedb.omdbClient

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import pl.kozlowski.moviedb.movieCatalogue.Movie

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes(
    JsonSubTypes.Type(OmdbErrorResponse::class),
    JsonSubTypes.Type(OmdbMovieDto::class)
)
sealed class OmdbResponse {
    fun toDomainMovie() =
        when (this) {
            is OmdbMovieDto -> Movie(
                this.title, this.year, this.rated, this.released, this.runtime, this.genre, this.director, this.writer,
                this.actors, this.plot, this.language, this.country, this.awards, this.poster,
                this.ratings.toDomainRatings(),
                this.metascore, this.imdbRating, this.imdbVotes, this.imdbId, this.type, this.dvd,
                this.boxOffice, this.production, this.website
            )

            is OmdbErrorResponse -> // TODO use exceptions to handle errors, or use Either, quietly failing works for POC
                when (this.error) {
                    "Incorrect IMDb ID." -> null
                    "Movie not found!" -> null
                    else -> throw IllegalArgumentException(this.error)
                }
        }

    private fun List<Rating>.toDomainRatings() = this.map {
        pl.kozlowski.moviedb.movieCatalogue.Rating(
            it.source,
            it.value
        )
    }
}

class OmdbErrorResponse(
    @JsonProperty("Error")
    val error: String,
) : OmdbResponse()

data class OmdbMovieDto(
    @JsonProperty("Title")
    val title: String,
    @JsonProperty("Year")
    val year: String,
    @JsonProperty("Rated")
    val rated: String,
    @JsonProperty("Released")
    val released: String,
    @JsonProperty("Runtime")
    val runtime: String,
    @JsonProperty("Genre")
    val genre: String,
    @JsonProperty("Director")
    val director: String,
    @JsonProperty("Writer")
    val writer: String,
    @JsonProperty("Actors")
    val actors: String,
    @JsonProperty("Plot")
    val plot: String,
    @JsonProperty("Language")
    val language: String,
    @JsonProperty("Country")
    val country: String,
    @JsonProperty("Awards")
    val awards: String,
    @JsonProperty("Poster")
    val poster: String,
    @JsonProperty("Ratings")
    val ratings: List<Rating>,
    @JsonProperty("Metascore")
    val metascore: String,
    val imdbRating: String,
    val imdbVotes: String,
    @JsonProperty("imdbID")
    val imdbId: String,
    @JsonProperty("Type")
    val type: String,
    @JsonProperty("DVD")
    val dvd: String,
    @JsonProperty("BoxOffice")
    val boxOffice: String,
    @JsonProperty("Production")
    val production: String,
    @JsonProperty("Website")
    val website: String,
) : OmdbResponse()

data class Rating(
    @JsonProperty("Source")
    val source: String,
    @JsonProperty("Value")
    val value: String,
)
