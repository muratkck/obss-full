package org.example.exercises.otherExercises.destination;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DestinationServlet extends HttpServlet {

    private static final String REDIRECT = "redirect";
    private static final String FORWARD = "forward";

    private String method;
    private String destination;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // Parameter init ile set edemiyoruz!
        method = config.getInitParameter("method");
        destination = config.getInitParameter("destination");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        String method = request.getParameter("method");
//        String destination = request.getParameter("destination");

        if (destination == null) {
            response.getWriter().println("JSP file name is required");
            return;
        }

        if (FORWARD.equals(method)){
            request.getRequestDispatcher(destination).forward(request, response);
        }
        else if (REDIRECT.equals(method)){
            // We cannot acces to WEB-INF Folder with sendRedirect();
            // response.sendRedirect(destination);
            // Without http, it redirects to the week/www.google.com
            response.sendRedirect(request.getContextPath() + destination);
        }
        else{
            response.getWriter().println("Destination must be provided!");
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.service(request, response);
    }
}
