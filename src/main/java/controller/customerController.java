package controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller //CRUD
public class customerController {

    /*@Autowired
    CustomerService customerService;*/

    /**
     *
     * @param customerId find by customer ID
     * @return JSON Customer
     */
    @GetMapping(value = "/CustomerId/{CUSTOMER_ID}")
    public User getUser(@PathVariable("CUSTOMER_ID") final int customerId) {
        return customerService.getUser(customerId);
    }
}
