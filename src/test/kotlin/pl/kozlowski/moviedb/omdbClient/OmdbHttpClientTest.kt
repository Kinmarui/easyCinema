package pl.kozlowski.moviedb.omdbClient

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class OmdbHttpClientTest {

    @Autowired
    lateinit var omdbMovieHttpClient: OmdbMovieHttpClient

    @Test
    @EnabledIfEnvironmentVariable(named = "INTEGRATION_TEST", matches = "true")
    fun getMovies() {
        val expected = OmdbMovieDto(
            title="The Matrix",
            year="1999",
            rated="R",
            released="31 Mar 1999",
            runtime="136 min",
            genre="Action, Sci-Fi",
            director="Lana Wachowski, Lilly Wachowski",
            writer="Lilly Wachowski, Lana Wachowski",
            actors="Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss",
            plot="When a beautiful stranger leads computer hacker Neo to a forbidding underworld, he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence.",
            language="English",
            country="United States, Australia",
            awards="Won 4 Oscars. 42 wins & 52 nominations total",
            poster="https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg",
            ratings= listOf(
                Rating(source="Internet Movie Database", value="8.7/10"),
                Rating(source="Rotten Tomatoes", value="88%"),
                Rating(source="Metacritic", value="73/100")
            ),
            metascore="73",
            imdbRating="8.7",
            imdbVotes="1,963,966",
            imdbId="tt0133093",
            type="movie",
            dvd="15 May 2007",
            boxOffice="$172,076,928",
            production="N/A",
            website="N/A"
        )
        val movie: OmdbMovieDto = omdbMovieHttpClient.getMovie( "tt0133093") as? OmdbMovieDto ?: throw IllegalArgumentException("Movie not found!")
        assertEquals(expected, movie)
    }
}
