In this repository I implemented *Port and Adapters* architecture also known as onion or hexagonal architecture.

Service is split into 3 domain areas

1. Movie Catalog - information about movies
2. Shows - show times, core domain
3. Ratings - moviegoers ratings

and one adapter which could be moved to separate project

4. OmdbClient - client for omdbapi.com

Each domain area has its own domain model and repository interface.

In top level package there is Domain objects and Services interfaces. In `adapter` package there are implementations of
repositories and services interfaces. Those implementations are using Spring Data JPA and Feign. In the future it would be
possible to move implementation to different gradle modules. 

For testing purposes there is in memory implementations of repositories and clients. This allows for faster test execution.
As next step I would like to add integration tests for database implementation.

## How to run
```
# this will start postgresql database at 5432 port
docker compose up -d 

pass OMDB_APIKEY to running aplication (run in IDE), or edit it in application.yml (last line)
```

For protected endpoints please authenticate as user `owner` with password  `password`

