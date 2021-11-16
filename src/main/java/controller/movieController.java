package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller //CRUD
public class movieController {

    /**
     * @return List of Movies
     */
    @GetMapping(value = "/movies")
    public String listMoviesView(Model model) {
        ArrayList<Movie> movies = movieService.listAll();
        model.addAttribute("movies", movies);
        return "movies";
    }

    /**
     * @param movieId - Unique identifier attached to each of the movies.
     */
    @GetMapping(value = "movies/MovieId/{MOVIE_ID}")
    public String getMovie(@PathVariable("MOVIE_ID") final int movieId) {
        return movieService.findById(movieId); // Send request to movie service handler
    }

    /**
     *
     * @param movieName - name of movie to be searched
     */
    @GetMapping(value = "/movies/movieName/{MOVIE_NAME}")
    public String searchMovie(@PathVariable("MOVIE_NAME") String movieName,
                              Model model) {
        ArrayList<Movie> movies = movieService.findByName(movieName);
        model.addAttribute("movies", movies);
        return "MovieSearch";
    }
}
