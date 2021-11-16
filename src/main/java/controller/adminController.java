package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller //CRUD
public class adminController {

    /**
     * @param userId - Unique identifier attached to each of the users.
     * @return User - Returns object of type user.
     */
    @PostMapping(value = "admin/customerId/{CUSTOMER_ID}")
    public String banCustomer(@PathVariable("USER_ID") final int customerId) {
        adminService.deleteUser(customerId);
        return "adminMenu";
    }

    /**
     * @param movieId - Unique identifier attached to each of the movies.
     * @return boolean - Returns boolean if movie was correctly removed.
     */
    @DeleteMapping(value = "admin/MovieIdDelete/{MOVIE_ID}")
    public String deleteMovie(@PathVariable("MOVIE_ID") final int movieId) {
        adminService.deleteMovie(movieId);
        return "adminMenu";
    }

    /**
     * @return returns all users
     */
    @GetMapping(value = "/adminMenu")
    public String showAdminMenuView(Model userModel, Model movieModel) {
        ArrayList<User> Users = adminService.listAllUsers();
        ArrayList<Movie> Movies = movieService.listAllMovies();
        userModel.addAttribute("users", users);
        movieModel.addAttribute("movies", movies);

        return "adminMenu";
    }
}
