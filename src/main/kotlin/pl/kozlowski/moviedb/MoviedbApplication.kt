package pl.kozlowski.moviedb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity

@SpringBootApplication
@EnableFeignClients("pl.kozlowski.moviedb.omdbClient")
@EnableJpaRepositories("pl.kozlowski.moviedb.movieCatalogue.adapters", "pl.kozlowski.moviedb.shows.adapters")
@EnableMethodSecurity(
    prePostEnabled = true,
    securedEnabled = true,
    jsr250Enabled = true
)
class MoviedbApplication

fun main(args: Array<String>) {
    runApplication<MoviedbApplication>(*args)
}
