package com.example.contactapp.servlets;

import com.example.contactapp.models.Contact;
import com.example.contactapp.repositories.ContactRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// jakarta validation -> check fields with annotations!
import java.io.IOException;

@WebServlet("/create-contact")
public class CreateContactServlet extends HttpServlet {
    private ContactRepository contactRepository = new ContactRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/createContact.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String phone_number = request.getParameter("phone_number");
        response.getWriter().println("Name: " + name + "\nPhone Number: " + phone_number);
        Contact contact = new Contact(name, phone_number);
        contactRepository.createContact(contact);
        request.setAttribute("message", "Contact " + contact.getName() + " added successfully");
        request.getRequestDispatcher("/WEB-INF/pages/successPage.jsp").forward(request, response);
    }
}
