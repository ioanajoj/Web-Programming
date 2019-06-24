package webapp;

import model.Authenticator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author joj on 6/24/2019
 **/
@WebServlet(name = "login")
public class login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        printWriter.print("Hello " + request.getParameter("username") +
                ". Your password is " + request.getParameter("password"));

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(Authenticator.isValidAstronaut(username, password)) {
            request.setAttribute("username", username);
            request.setAttribute("password", password);

            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }
            //generate a new session
            HttpSession newSession = request.getSession(true);

            //setting session to expiry in 5 mins
            newSession.setMaxInactiveInterval(5*60);

            HttpSession session = request.getSession();
            if (request.getParameter("JSESSIONID") != null) {
                Cookie userCookie = new Cookie("JSESSIONID", request.getParameter("JSESSIONID"));
                response.addCookie(userCookie);
            } else {
                String sessionId = session.getId();
                Cookie userCookie = new Cookie("JSESSIONID", sessionId);
                response.addCookie(userCookie);
            }
            session.setAttribute("username", username);

            request.getRequestDispatcher("/welcome.jsp").forward(request, response);
        }
        else {
            request.setAttribute("errorMessage", "Invalid credentials");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter printWriter = response.getWriter();
        printWriter.print("Hello " + request.getParameter("username") +
                ". Your password is " + request.getParameter("password"));
    }
}
