package org.example.exercises.sessionExercise;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/invalidate-session")
public class InvalidateSessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // I don't want to create a new session, so I use false as parameter.
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            response.getWriter().println("Session invalidated.");
        } else {
            response.getWriter().println("No session to invalidate.");
        }
    }

}
