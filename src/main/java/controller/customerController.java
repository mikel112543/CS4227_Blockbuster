package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController //CRUD
public class customerController {

    /**
     *
     * @param customerId find by customer ID
     * @return JSON Customer
     */
    @GetMapping(value = "/CustomerId/{CUSTOMER_ID}")
    public User getUser(@PathVariable("CUSTOMER_ID") final int customerId) {
        return customerService.getUser(customerId);
    }

    /**
     *
     * @param customerId - Customer ID renting the movie
     * @param movieId
     * @return
     */
    @GetMapping(value = "customerID/{CUSTOMER_ID}/rentMovieID/{MOVIE_ID}")
    public User rentMovie(@PathVariable("CUSTOMER_ID") final int customerId,
                          @PathVariable("MOVIE_ID") final int movieId) {
        return customerService.rentMovie(customerId, movieId);
    }

}
