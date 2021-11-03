package controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class movieController {

    /**
     *
     * @param movieId - Unique identifier attached to each of the movies.
     * @return Movie - Returns object of type Movie
     */
    @GetMapping(value = "MovieId/{movieId}")
    public Movie getMovie(@PathVariable("movieId") final int movieId) {
        return movieService.getMovie(movieId); // Send request to movie service handler
    }

    /**
     *
     * @param movieId - Unique identifier attached to each of the movies.
     * @return boolean - Returns boolean if movie was correctly removed.
     */
    @DeleteMapping(value = "MovieId/{movieId}")
    public Movie deleteMovie(@PathVariable("movieId") final int movieId) {
        return movieService.deleteMovie(movieId);
    }
}
