package com.example.movierental.controller;

import com.example.movierental.contants.Error;
import com.example.movierental.exception.ServiceException;
import com.example.movierental.logger.AbstractLogger;
import com.example.movierental.logger.RequesterClient;
import com.example.movierental.model.ServiceError;
import com.example.movierental.model.User;
import com.example.movierental.service.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.security.Principal;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    MovieServiceImpl movieService;

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    @GetMapping("/")
    public String getLoginView(Principal principal) {
        if (isAuthenticated()) {
            return "redirect:/movies";
        } else {
            movieService.clearMovies();
            return "login";
        }
    }

    @GetMapping("/registerForm")
    public String showRegistration(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);
        return "registerForm";
    }
}

