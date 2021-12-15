package com.example.movierental.controller;
import com.example.movierental.model.User;
import com.example.movierental.service.UserRepoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Author - Michael Danaher
 */
@Controller //CRUD
public class CustomerController {

    UserRepoServiceImpl userService;

    @Autowired
    public CustomerController(UserRepoServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    ObjectMapper mapper;

    /**
     *
     * @param customerId find by customer ID
     * @return JSON Object Customer
     */
    @GetMapping(value = "/customerId/{CUSTOMER_ID}")
    @ResponseBody
    public ObjectNode getUser(@PathVariable("CUSTOMER_ID") final int customerId) {
        User user =  userService.findByID(customerId);
        ObjectNode userNode = mapper.createObjectNode();
        userNode.put("Username" ,user.getUsername());
        userNode.put("Is banned", user.isBanned());
        userNode.put("Loyalty Points", user.getLoyaltyPoints());
        return userNode;
    }

    @PostMapping(value = "/register")
    public String getUserDetails(@RequestParam("userName") String userName,
                                 @RequestParam("password") String password,
                                 RedirectAttributes redirectAttributes){
            userService.registerUser(userName, password);
            redirectAttributes.addFlashAttribute("Success", "Registration Successful");
            return "redirect:/";
    }
}
