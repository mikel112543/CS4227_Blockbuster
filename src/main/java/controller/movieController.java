package controller;

import org.springframework.web.bind.annotation.*;

@RestController //CRUD
public class movieController {

    /**
     * @return List of Movies
     */
    @GetMapping(value = "/movies")
    public Movie listAll() {
        return movieService.listAll();
    }

    /**
     * @param movieId - Unique identifier attached to each of the movies.
     * @return JSON Movie
     */
    @GetMapping(value = "movies/MovieId/{MOVIE_ID}")
    public Movie getMovie(@PathVariable("MOVIE_ID") final int movieId) {
        return movieService.getMovie(movieId); // Send request to movie service handler
    }

    /**
     *
     * @param movieName - name of movie to be searched
     * @return JSON Movie
     */
    @GetMapping(value = "/movies/movieName/{MOVIE_NAME}")
    public Movie searchMovie(@PathVariable("MOVIE_NAME") String movieName) {
        return movieService.searchMovie(movieName);
    }
}
