package com.example.contactapp.servlets;

import com.example.contactapp.models.Contact;
import com.example.contactapp.repositories.ContactRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/search-contact")
public class SearchContactServlet extends HttpServlet {

    ContactRepository contactRepository = new ContactRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/searchContact.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contactName = request.getParameter("contact-name");

        List<Contact> contactList = contactRepository.searchContactsByName(contactName);
        request.setAttribute("contactList", contactList);
        request.getRequestDispatcher("/WEB-INF/pages/searchContact.jsp").forward(request, response);
    }

}
