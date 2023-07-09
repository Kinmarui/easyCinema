package pl.kozlowski.moviedb.movieCatalogue

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import pl.kozlowski.moviedb.movieCatalogue.inMemoryImpl.InMemoryMovieCatalogueRepository
import pl.kozlowski.moviedb.movieCatalogue.inMemoryImpl.InMemoryMovieClient
import pl.kozlowski.moviedb.movieCatalogue.inMemoryImpl.MovieDataProvider

internal class MovieCatalogueTest : BehaviorSpec({

    val movieCatalogueRepository = InMemoryMovieCatalogueRepository()
    val movieClient = InMemoryMovieClient()
    val movieCatalogue =
        MovieCatalogueService(movieCatalogueRepository = movieCatalogueRepository, movieClient = movieClient)

    given("MovieCatalogue and MovieClient") {

        `when`("searching for movies by ImdbId we don't know") {
            val result =
                movieCatalogue.getMovie(imdbID = MovieDataProvider.FAF1.imdbId)
            then("should return movie") {
                result.size shouldBe 0
            }
        }

        `when`("searching for movies by Title we don't know") {
            val result =
                movieCatalogue.getMovie(title = MovieDataProvider.FAF1.title)
            then("should return movie") {
                result.size shouldBe 0
            }
        }

        `when`("searching for movies by ImdbId existing in Catalogue") {
            movieCatalogueRepository.save(MovieDataProvider.FAF1)

            val FAF1 =
                movieCatalogue.getMovie(imdbID = MovieDataProvider.FAF1.imdbId).first()
            then("should return movie") {
                FAF1.title shouldBe "The Fast and the Furious"
            }
        }

        `when`("searching for movies by partial Title in Catalogue") {
            movieCatalogueRepository.save(MovieDataProvider.FAF2)

            val FAF2ByTitle = movieCatalogue.getMovie(title = "2 Fast 2")
            then("should return movie") {
                FAF2ByTitle.size shouldBe 1
                FAF2ByTitle.first().title shouldBe "2 Fast 2 Furious"
            }
        }

        `when`("searching for movies by partial Title in Client") {
            movieClient.save(MovieDataProvider.FAF3)

            val FAF3ByTitle = movieCatalogue.getMovie(title = "Tokyo Drift")
            then("should return movie") {
                FAF3ByTitle.size shouldBe 1
                FAF3ByTitle.first().title shouldBe "The Fast and the Furious: Tokyo Drift"
            }
        }
        `when`("searching for movies by partial Title matching multiple movies") {
            val MoviesContainingFastInTitle = movieCatalogue.getMovie(title = "Fast")

            then("should all matching movies") {
                MoviesContainingFastInTitle.size shouldBe 3
            }
        }
    }
})