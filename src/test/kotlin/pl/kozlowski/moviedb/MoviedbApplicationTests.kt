package pl.kozlowski.moviedb

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootTest
@EnableJpaRepositories("pl.kozlowski.moviedb.movieCatalogue.adapters", "pl.kozlowski.moviedb.shows.adapters")
class MoviedbApplicationTests {

    @Test
    fun contextLoads() {
    }

}
