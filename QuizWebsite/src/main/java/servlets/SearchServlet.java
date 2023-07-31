package servlets;

import dao.UsersDao;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String usernamePrefix = httpServletRequest.getParameter("search");
        UsersDao usersDao = (UsersDao) httpServletRequest.getServletContext()
                .getAttribute("users");
        List<User> usersList = usersDao.searchPrefix(usernamePrefix);
        StringBuilder json = new StringBuilder("{ \"list\": [");
        for (int i = 0; i < usersList.size(); i++) {
            User user = usersList.get(i);
            String username = user.getUsername();
            int userId = user.getId();
            String curObj = "{ \"username\": \"" + username
                    + "\", \"link\": " + "\"/user?id=" + Integer.toString(userId) + "\"}";
            if (i != usersList.size()-1) {
                curObj += ",\n";
            }
            json.append(curObj);
        }
        json.append("] }");
        httpServletResponse.setContentType("application/json");
        PrintWriter out = httpServletResponse.getWriter();
        out.print(json.toString());
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        super.doPost(httpServletRequest, httpServletResponse);
    }
}
