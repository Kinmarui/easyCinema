package pl.kozlowski.moviedb.shows.adapters

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import pl.kozlowski.moviedb.commons.endOfDay
import pl.kozlowski.moviedb.commons.startOfDay
import pl.kozlowski.moviedb.shows.inMemoryImpl.ShowDataProvider

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(DbShowsRepository::class)
// INFO: Not sure if problem with my config. But Executing tests from class level won't work for me.
// I had to select any given as baseline and edit configuration to remove test path
class DbShowsRepositoryTest(
    dbShowsRepository: DbShowsRepository
) : BehaviorSpec({

    given("empty dbShowsRepository") {
        `when`("getting Shows by date range") {
            val result =
                dbShowsRepository.findByDateBetweenAndMovieId(
                    dateFrom = ShowDataProvider.expectedTestTimeRangeMin,
                    dateTo = ShowDataProvider.expectedTestTimeRangeMax,
                    movieId = null
                )
            then("should return no shows") {
                result.size shouldBe 0
            }
        }
    }

    given("some shows in dbShowsRepository") {
        ShowDataProvider.createOneWeekOfFirstMovieShows().forEach {
            dbShowsRepository.save(it)
        }
        `when`("getting Shows by date range") {
            val result =
                dbShowsRepository.findByDateBetweenAndMovieId(
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
                dbShowsRepository.findByDateBetweenAndMovieId(
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
                dbShowsRepository.findByDateBetweenAndMovieId(
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
                dbShowsRepository.findByDateBetweenAndMovieId(
                    dateFrom = ShowDataProvider.SHOW1.date.startOfDay(),
                    dateTo = ShowDataProvider.SHOW1.date.endOfDay(),
                    movieId = 2L
                )
            then("should return 0 shows") {
                result.size shouldBe 0
            }
        }
    }
    given("another show in dbShowsRepository") {
        ShowDataProvider.createOneWeekOfSecondMovieShows().forEach {
            dbShowsRepository.save(it)
        }
        `when`("getting Shows by date range (single day) not specifying movieId") {
            val result =
                dbShowsRepository.findByDateBetweenAndMovieId(
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
                dbShowsRepository.findByDateBetweenAndMovieId(
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
}){
    override fun extensions() = listOf(SpringExtension)
}

// Regular JUnit 5 test: Works with default runner
//class DbShowsRepositoryTest {
//
//    @Autowired
//    private lateinit var dbShowsRepository: DbShowsRepository //= DbShowsRepository(showsEntityRepository)
//
//    @Test
//    fun whenInitializedByDbUnit_thenFindsByName() {
//        val shows = dbShowsRepository.findByDateBetweenAndMovieId(
//            dateFrom = ShowDataProvider.expectedTestTimeRangeMin,
//            dateTo = ShowDataProvider.expectedTestTimeRangeMax,
//            movieId = null
//        )
//        shows.size shouldBe 0
//
//        dbShowsRepository.save(ShowDataProvider.SHOW1)
//        val shows2 = dbShowsRepository.findByDateBetweenAndMovieId(
//            dateFrom = ShowDataProvider.expectedTestTimeRangeMin,
//            dateTo = ShowDataProvider.expectedTestTimeRangeMax,
//            movieId = null
//        )
//        shows2.size shouldBe 1
//    }
//}