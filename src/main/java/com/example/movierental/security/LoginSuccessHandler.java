package com.example.movierental.security;

import com.example.movierental.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        User user = (User) authentication.getPrincipal();

        String redirectURL = request.getContextPath();

        if (user.hasRole(UserRole.USER)) {
            redirectURL = "/movies";
        } else if (user.hasRole(UserRole.ADMIN)) {
            redirectURL = "/admin";
        } else {
            redirectURL = "/?error=true";
        }

        response.sendRedirect(redirectURL);
    }
}
