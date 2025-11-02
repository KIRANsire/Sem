package com.resumeapp.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class HelloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");      // **CRUCIAL!**
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Hello Servlet</title></head>");
        out.println("<body>");
        out.println("<h2>Hello from Servlet!</h2>");
        out.println("</body>");
        out.println("</html>");
    }
}
