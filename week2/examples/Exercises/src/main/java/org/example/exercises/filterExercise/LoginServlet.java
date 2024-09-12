package org.example.exercises.filterExercise;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.UUID;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final String USERNAME = "test";
    private static final String PASSWORD = "test";

    private static final String LOGIN_PAGE = "/WEB-INF/login.jsp";
    private static final String SECURED_PAGE = "/private/secured.jsp";



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        // is null control needed for session?
        // if (session == null || session.getAttribute("isLoggedIn") == null)
        if (session == null || session.getAttribute("isLoggedIn") == null) {
            req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
        }
        else{
            resp.sendRedirect(req.getContextPath() + SECURED_PAGE);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();

        if(USERNAME.equals(username) && PASSWORD.equals(password)) {
            session.setAttribute("isLoggedIn", UUID.randomUUID().toString());
            session.setAttribute("username", username);
            //resp.getWriter().println("contextPath: " + req.getContextPath());
            resp.sendRedirect(req.getContextPath() + SECURED_PAGE);
            return;
        }

        session.setAttribute("message", "Invalid username or password");
        req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
    }
}
