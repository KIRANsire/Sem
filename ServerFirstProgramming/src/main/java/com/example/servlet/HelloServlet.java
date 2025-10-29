package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handle GET requests
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h2>Session Tracking with Multiple Values</h2>");

        // Get cookies
        Cookie[] cookies = request.getCookies();
        String cookieName = null;
        String cookieCourse = null;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("username")) {
                    cookieName = c.getValue();
                }
                if (c.getName().equals("course")) {
                    cookieCourse = c.getValue();
                }
            }
        }

        // Get session
        HttpSession session = request.getSession(false);
        String sessionName = (session != null) ? (String) session.getAttribute("username") : null;
        String sessionCourse = (session != null) ? (String) session.getAttribute("course") : null;

        // Display values
        out.println("<p><b>Cookie username:</b> " + (cookieName != null ? cookieName : "Not set") + "</p>");
        out.println("<p><b>Cookie course:</b> " + (cookieCourse != null ? cookieCourse : "Not set") + "</p>");

        out.println("<p><b>Session username:</b> " + (sessionName != null ? sessionName : "Not set") + "</p>");
        out.println("<p><b>Session course:</b> " + (sessionCourse != null ? sessionCourse : "Not set") + "</p>");

        // Form for POST
        out.println("<form method='post' action='hello'>");
        out.println("Enter your name: <input type='text' name='username'/><br/>");
        out.println("Enter your favorite course: <input type='text' name='course'/><br/>");
        out.println("<input type='submit' value='Save in Session & Cookie'/>");
        out.println("</form>");
    }

    // Handle POST requests
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String course = request.getParameter("course");

        if (username != null && !username.trim().isEmpty() && course != null && !course.trim().isEmpty()) {
            // Store in cookies
            Cookie cookieUser = new Cookie("username", username);
            cookieUser.setMaxAge(60 * 5);
            response.addCookie(cookieUser);

            Cookie cookieCourse = new Cookie("course", course);
            cookieCourse.setMaxAge(60 * 5);
            response.addCookie(cookieCourse);

            // Store in session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("course", course);

            out.println("<h2>Hello, " + username + "! (POST request)</h2>");
            out.println("<p>Your course <b>" + course + "</b> is saved in both Cookie and Session.</p>");
        } else {
            out.println("<h2>Please enter both username and course!</h2>");
        }

        out.println("<a href='hello'>Go back to GET page</a>");
    }
}
