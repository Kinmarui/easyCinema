package pl.kozlowski.moviedb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MoviedbApplication

fun main(args: Array<String>) {
    runApplication<MoviedbApplication>(*args)
}
