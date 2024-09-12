package org.example.exercises.filterExercise;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
//import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/*
@WebFilter -> instead of writing web.xml
 */
public class PrivateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        boolean authenticated = checkAuthentication(httpRequest); // Implement this method
        if (!authenticated) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }
        // if there are another filters, executes them
        // otherwise, redirects to the related servlet
        //
        chain.doFilter(request, response);
    }

    private boolean checkAuthentication(HttpServletRequest request) {
        // returns null if session is invalid.
        HttpSession session = request.getSession(false);
        if (session != null) {
            Object isLoggedIn = session.getAttribute("isLoggedIn");
            if (isLoggedIn != null) {
                return true;
            }
        }

        return false; // Example placeholder logic
    }
}
