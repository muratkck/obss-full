package com.example.contactapp.servlets;

import com.example.contactapp.models.Contact;
import com.example.contactapp.repositories.ContactRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/edit-contact")
public class EditContactServlet extends HttpServlet {

    ContactRepository contactRepository = new ContactRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String contactId = request.getParameter("contactId");
        //response.getWriter().println("Edit the Contact: " + contactId);
        int id = Integer.parseInt(request.getParameter("contact-id"));
        Contact contactById = contactRepository.getContactById(id);
        request.setAttribute("contactById", contactById);
        request.getRequestDispatcher("/WEB-INF/pages/editContact.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // check parameters?null
        String contactById = request.getParameter("contact-id");
        String newName = request.getParameter("new-name");
        String newPhoneNumber = request.getParameter("new-phone-number");

        Contact contact = contactRepository.getContactById(Integer.parseInt(contactById));
        contact.setName(newName);
        contact.setPhoneNumber(newPhoneNumber);

        contactRepository.updateContact(contact);
        request.setAttribute("message", "Contact " + contact.getName() + " updated successfully");
        request.getRequestDispatcher("/WEB-INF/pages/successPage.jsp").forward(request, response);
    }

}
