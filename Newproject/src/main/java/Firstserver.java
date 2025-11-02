import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/aa")  // must match form action="aa"
public class Firstserver extends HttpServlet {	

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException { 

        // Retrieve parameters (case-sensitive)
        String username = request.getParameter("username");	
        String password = request.getParameter("password");

        // Debugging in console
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        // Send response to browser
        PrintWriter out = response.getWriter();
        out.println("Username: " + username );
        out.println("Password: " + password );
        
        
    }
}
