package pl.kozlowski.moviedb.movieCatalogue.inMemoryImpl

import pl.kozlowski.moviedb.movieCatalogue.Movie
import pl.kozlowski.moviedb.movieCatalogue.Rating

abstract class MovieDataProvider {
    companion object{
        val FAF1 =
            Movie(
                title = "The Fast and the Furious",
                releaseYear = "2001",
                rated = "PG-13",
                released = "22 Jun 2001",
                runtime = "106 min",
                genre = "Action, Crime, Thriller",
                director = "Rob Cohen",
                writer = "Ken Li, Gary Scott Thompson, Erik Bergquist",
                actors = "Vin Diesel, Paul Walker, Michelle Rodriguez",
                plot = "Los Angeles police officer Brian O'Conner must decide where his loyalty really lies when he becomes enamored with the street racing world he has been sent undercover to destroy.",
                language = "English, Spanish",
                country = "United States, Germany",
                awards = "11 wins & 18 nominations",
                poster = "https://m.media-amazon.com/images/M/MV5BNzlkNzVjMDMtOTdhZC00MGE1LTkxODctMzFmMjkwZmMxZjFhXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg",
                ratings = listOf(
                    Rating(source = "Internet Movie Database", value = "6.8/10"),
                    Rating(source = "Rotten Tomatoes", value = "55%"),
                    Rating(source = "Metacritic", value = "58/100")
                ),
                metascore = "58",
                imdbRating = "6.8",
                imdbVotes = "403,497",
                imdbId = "tt0232500",
                type = "movie",
                dvd = "03 Jun 2003",
                boxOffice = "$144,745,925",
                production = "N/A",
                website = "N/A",
                id = 3L
            )
        val FAF2 = Movie(
            title = "2 Fast 2 Furious",
            releaseYear = "2003",
            rated = "PG-13",
            released = "06 Jun 2003",
            runtime = "107 min",
            genre = "Action, Crime, Thriller",
            director = "John Singleton",
            writer = "Gary Scott Thompson, Michael Brandt, Derek Haas",
            actors = "Paul Walker, Tyrese Gibson, Cole Hauser",
            plot = "Former cop Brian O'Conner is called upon to bust a dangerous criminal and he recruits the help of a former childhood friend and street racer who has a chance to redeem himself.",
            language = "English, Spanish",
            country = "United States, Germany",
            awards = "4 wins & 13 nominations",
            poster = "https://m.media-amazon.com/images/M/MV5BMzExYjcyYWMtY2JkOC00NDUwLTg2OTgtMDI3MGY2OWQzMDE2XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg",
            ratings = listOf(
                Rating(source = "Internet Movie Database", value = "5.9/10"),
                Rating(source = "Rotten Tomatoes", value = "37%"),
                Rating(source = "Metacritic", value = "38/100")
            ),
            metascore = "38",
            imdbRating = "5.9",
            imdbVotes = "286,285",
            imdbId = "tt0322259",
            type = "movie",
            dvd = "30 Sep 2003",
            boxOffice = "$127,154,901",
            production = "N/A",
            website = "N/A",
            id = 1L
        )
        val FAF3 =
            Movie(
                title = "The Fast and the Furious: Tokyo Drift",
                releaseYear = "2006",
                rated = "PG-13",
                released = "16 Jun 2006",
                runtime = "104 min",
                genre = "Action, Crime, Thriller",
                director = "Justin Lin",
                writer = "Chris Morgan",
                actors = "Lucas Black, Zachery Ty Bryan, Shad Moss",
                plot = "A teenager becomes a major competitor in the world of drift racing after moving in with his father in Tokyo to avoid a jail sentence in America.",
                language = "English, Japanese, Portuguese",
                country = "United States, Germany, Japan",
                awards = "1 win & 4 nominations",
                poster = "https://m.media-amazon.com/images/M/MV5BMTQ2NTMxODEyNV5BMl5BanBnXkFtZTcwMDgxMjA0MQ@@._V1_SX300.jpg",
                ratings = listOf(
                    Rating(source = "Internet Movie Database", value = "6.0/10"),
                    Rating(source = "Rotten Tomatoes", value = "38%"),
                    Rating(source = "Metacritic", value = "45/100")
                ),
                metascore = "45",
                imdbRating = "6.0",
                imdbVotes = "286,229",
                imdbId = "tt0463985",
                type = "movie",
                dvd = "26 Sep 2006",
                boxOffice = "$62,514,415",
                production = "N/A",
                website = "N/A",
                id = 2L
            )
    }
}