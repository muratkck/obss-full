package org.example.exercises.otherExercises.ATMExercise;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "atmServlet", value = "/atm")
public class ATMServlet extends HttpServlet {

    private static final String WITHDRAW = "withdraw";
    private static final String DEPOSIT = "deposit";

    private int balance = 4000;
    private int amount = 0;

    Object lock = new Object();
    private static final String EXAMPLE_REQUEST = "exercises/atm?amount=<number>&method=<method-name>";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("method");
        if(req.getParameter("amount") == null){
            resp.getWriter().println("Amount should be provided!");
            resp.getWriter().println(EXAMPLE_REQUEST);
        }
        amount = Integer.parseInt(req.getParameter("amount"));
        doOperations(method, amount, resp);

    }

    private void doOperations(String method, int amount, HttpServletResponse resp) throws IOException {
        if(WITHDRAW.equals(method)){
            printBalance("Before Withdraw", resp);
            withdraw(amount);
            printBalance("After Withdraw", resp);
        }
        else if(DEPOSIT.equals(method)){
            printBalance("Before Deposit", resp);
            deposit(amount);
            printBalance("After Deposit", resp);
        }
        else {
            resp.getWriter().println("Method should be provided!");
            resp.getWriter().println("Usage: " + EXAMPLE_REQUEST);
        }
    }

    private void deposit(int amount){
        balance += amount;
    }
    
    private void withdraw(int amount){
        synchronized (lock){
            if(balance >= amount){
                sleep(5);
                balance -= amount;
            }
        }
    }

    private void sleep(int seconds){
        int milliseconds = seconds * 1000;
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void printBalance(String message, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(message + " :" + balance);
    }
}
