package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import dao.UsersDao;

@WebServlet("/removeUser")
public class RemoveUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        doPost(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String userId = httpServletRequest.getParameter("id");

        if (userId != null) {
            ServletContext servletContext = httpServletRequest.getServletContext();
            UsersDao usersDao = (UsersDao) servletContext.getAttribute("users");
            usersDao.removeUser(Integer.parseInt(userId));
            // Redirect the user to the homepage or any other desired page
            httpServletRequest.getRequestDispatcher("homepage.jsp").forward(httpServletRequest, httpServletResponse);

        } else {
            // Handle error case, perhaps show an error message or redirect to an error page
        }
    }
}
