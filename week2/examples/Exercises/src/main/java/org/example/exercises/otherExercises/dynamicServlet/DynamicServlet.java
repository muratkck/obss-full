package org.example.exercises.otherExercises.dynamicServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(
        name = "dynamicServlet",
        urlPatterns = {"/dynamic-servlet/*"}
)
public class DynamicServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StringBuffer requestURL = request.getRequestURL();
        String requestURI = request.getRequestURI();
        String servletPath = request.getServletPath();
        String contextPath = request.getContextPath();
        String pathInfo = request.getPathInfo();
        String pathTranslated = request.getPathTranslated();
        String queryString = request.getQueryString();

        response.getWriter().println("Request URL: " + requestURL.toString());
        response.getWriter().println("Request URI: " + requestURI);
        response.getWriter().println("Servlet Path: " + servletPath);
        response.getWriter().println("Context Path: " + contextPath);
        response.getWriter().println("Path info: " + pathInfo);
        response.getWriter().println("Path Translated: " + pathTranslated);
        response.getWriter().println("Query String: " + queryString);


    }
}
