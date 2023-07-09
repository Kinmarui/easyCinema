package pl.kozlowski.moviedb.movieCatalogue.adapters

import org.springframework.stereotype.Component
import pl.kozlowski.moviedb.movieCatalogue.Movie
import pl.kozlowski.moviedb.movieCatalogue.MovieCatalogueRepository

@Component
class DbMovieCatalogueRepository(
    private val movieCatalogueEntityRepository: MovieCatalogueEntityRepository
) : MovieCatalogueRepository {
    override fun findByImdbIdIgnoreCase(imdbId: String): List<Movie> =
        movieCatalogueEntityRepository.findByImdbIdIgnoreCase(imdbId).map(MovieEntity::toDomainEntity)

    override fun findByTitleContainingIgnoreCase(title: String): List<Movie> =
        movieCatalogueEntityRepository.findByTitleContainsIgnoreCaseOrderByTitleAsc(title).map(MovieEntity::toDomainEntity)

    override fun save(movie: Movie): Movie = movieCatalogueEntityRepository
        .save(movie.toDbEntity()).toDomainEntity()

    override fun findAll(): List<Movie> = movieCatalogueEntityRepository.findAll().map(MovieEntity::toDomainEntity)
}

private fun Movie.toDbEntity(): MovieEntity = MovieEntity(
    title = title,
    releaseYear = releaseYear,
    rated = rated,
    released = released,
    runtime = runtime,
    genre = genre,
    director = director,
    writer = writer,
    actors = actors,
    plot = plot,
    language = language,
    country = country,
    awards = awards,
    poster = poster,
    ratings = ratings.map { it.toDbEntity() },
    metascore = metascore,
    imdbRating = imdbRating,
    imdbVotes = imdbVotes,
    imdbId = imdbId,
    type = type,
    dvd = dvd,
    boxOffice = boxOffice,
    production = production,
    website = website,
    id = if (id > 0L) id else null
)

private fun pl.kozlowski.moviedb.movieCatalogue.Rating.toDbEntity() = RatingEntity(
    source = source,
    value = value,
)

private fun MovieEntity.toDomainEntity(): Movie = Movie(
    title = title,
    releaseYear = releaseYear,
    rated = rated,
    released = released,
    runtime = runtime,
    genre = genre,
    director = director,
    writer = writer,
    actors = actors,
    plot = plot,
    language = language,
    country = country,
    awards = awards,
    poster = poster,
    ratings = ratings.map { it.toDomainEntity() },
    metascore = metascore,
    imdbRating = imdbRating,
    imdbVotes = imdbVotes,
    imdbId = imdbId,
    type = type,
    dvd = dvd,
    boxOffice = boxOffice,
    production = production,
    website = website,
    id = id!!
)

private fun RatingEntity.toDomainEntity() = pl.kozlowski.moviedb.movieCatalogue.Rating(
    source = source,
    value = value,
)
