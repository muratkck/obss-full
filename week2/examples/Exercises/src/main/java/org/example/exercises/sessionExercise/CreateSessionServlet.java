package org.example.exercises.sessionExercise;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/create-session")
public class CreateSessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        session.setAttribute("username", "JohnDoe");
        session.setAttribute("userAge", "22");

        // Forward request to another servlet
        req.getRequestDispatcher("/list-session-attributes").forward(req, resp);

        // Uncomment below to test redirection
        // resp.sendRedirect("list-session-attributes");

    }
}
