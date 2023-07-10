package pl.kozlowski.moviedb.shows

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import pl.kozlowski.moviedb.shows.inMemoryImpl.InMemoryShowsRepository
import pl.kozlowski.moviedb.commons.endOfDay
import pl.kozlowski.moviedb.commons.startOfDay
import pl.kozlowski.moviedb.shows.inMemoryImpl.ShowDataProvider

internal class ShowsTest : BehaviorSpec({

    val showsRepository = InMemoryShowsRepository()
    val showsService =
        ShowsService(showsRepository = showsRepository)

    given("empty ShowsService") {
        `when`("getting Shows by date range") {
            val result =
                showsService.findByDateBetweenAndMovieId(
                    dateFrom = ShowDataProvider.expectedTestTimeRangeMin,
                    dateTo = ShowDataProvider.expectedTestTimeRangeMax,
                    movieId = null
                )
            then("should return no shows") {
                result.size shouldBe 0
            }
        }
    }

    given("some shows in ShowsService") {
        ShowDataProvider.createOneWeekOfFirstMovieShows().forEach {
            showsRepository.save(it)
        }
        `when`("getting Shows by date range") {
            val result =
                showsService.findByDateBetweenAndMovieId(
                    dateFrom = ShowDataProvider.expectedTestTimeRangeMin,
                    dateTo = ShowDataProvider.expectedTestTimeRangeMax,
                    movieId = null
                )
            then("should return shows") {
                result.size shouldBe 7
            }
        }
        `when`("getting Shows by date range (single day)") {
            val result =
                showsService.findByDateBetweenAndMovieId(
                    dateFrom = ShowDataProvider.SHOW1.date.startOfDay(),
                    dateTo = ShowDataProvider.SHOW1.date.endOfDay(),
                    movieId = null
                )
            then("should return one show") {
                result.size shouldBe 1
            }
        }
        `when`("getting Shows by date range (single day) and existing movieId") {
            val result =
                showsService.findByDateBetweenAndMovieId(
                    dateFrom = ShowDataProvider.SHOW1.date.startOfDay(),
                    dateTo = ShowDataProvider.SHOW1.date.endOfDay(),
                    movieId = 1L
                )
            then("should return one show with movieId=1") {
                result.size shouldBe 1
                result.first().movieId shouldBe 1L
            }
        }
        `when`("getting Shows by date range (single day) and not existing movieId") {
            val result =
                showsService.findByDateBetweenAndMovieId(
                    dateFrom = ShowDataProvider.SHOW1.date.startOfDay(),
                    dateTo = ShowDataProvider.SHOW1.date.endOfDay(),
                    movieId = 2L
                )
            then("should return 0 shows") {
                result.size shouldBe 0
            }
        }
    }
    given("another show in ShowsService") {
        ShowDataProvider.createOneWeekOfSecondMovieShows().forEach {
            showsRepository.save(it)
        }
        `when`("getting Shows by date range (single day) not specifying movieId") {
            val result =
                showsService.findByDateBetweenAndMovieId(
                    dateFrom = ShowDataProvider.SHOW2.date.startOfDay(),
                    dateTo = ShowDataProvider.SHOW2.date.endOfDay(),
                    movieId = null
                )
            then("should return 2 shows") {
                result.size shouldBe 2
            }
        }
        `when`("getting Shows by date range (single day) specifying movieId") {
            val result =
                showsService.findByDateBetweenAndMovieId(
                    dateFrom = ShowDataProvider.SHOW2.date.startOfDay(),
                    dateTo = ShowDataProvider.SHOW2.date.endOfDay(),
                    movieId = 1L
                )
            then("should return one show with movie id 1") {
                result.size shouldBe 1
                result.first().movieId shouldBe 1L
            }
        }
    }
})
