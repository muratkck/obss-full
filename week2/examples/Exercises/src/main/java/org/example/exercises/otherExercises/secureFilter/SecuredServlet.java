package org.example.exercises.otherExercises.secureFilter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(
        name = "securedServlet",
        urlPatterns = {"/protected-servlet/*"}
)
public class SecuredServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //response.getWriter().println("Unsecured Page!");
        request.getRequestDispatcher("/WEB-INF/static/unSecured.jsp").forward(request, response);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo.contains("secure")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
        else{
            super.service(request, response);
        }
    }
}
