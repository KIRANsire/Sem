package com.resumeapp.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.ArrayList;

public class RegisterServlet extends HttpServlet {
    // In-memory list to store user registrations during server session
    private static ArrayList<String> users = new ArrayList<>();

    // Handle POST requests from the form submission
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve form parameters
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Create a user record string
        String userRecord = username + "," + email + "," + password;

        // Add to in-memory users list
        users.add(userRecord);

        // Append the user record to a file named registered_users.txt in project root
        try (FileWriter fw = new FileWriter("registered_users.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter fileOut = new PrintWriter(bw)) {
            fileOut.println(userRecord);
        } catch (IOException e) {
            out.println("<p style='color:red;'>Error saving to file: " + e.getMessage() + "</p>");
        }

        // Output confirmation and list all users for current server session
        out.println("<html><head><title>Registration Result</title></head><body>");
        out.println("<h2>Registration Successful!</h2>");
        out.println("<p>Username: " + username + "</p>");
        out.println("<p>Email: " + email + "</p>");
        out.println("<h3>All Registered Users (this server session):</h3>");
        for (String u : users) {
            out.println("<p>" + u + "</p>");
        }
        out.println("</body></html>");
    }

    // Handle GET requests gracefully to avoid HTTP 405 error
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Method Not Allowed</title></head><body>");
        out.println("<h2>GET method is not supported for this URL.</h2>");
        out.println("<p>Please submit the form via <a href='register.jsp'>Registration Form</a>.</p>");
        out.println("</body></html>");
    }
}
