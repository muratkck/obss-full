package org.example.exercises.otherExercises.requestParameter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "paramServlet", value = "/param-servlet")
public class ParamServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the 'param' parameter from the GET request
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");


        if(name == null || surname == null){
            response.getWriter().println("Name and Surname are required");
            return;
        }

        String greeting = String.format("Hello %s %s", name, surname);
        response.getWriter().println(greeting);
    }

}
