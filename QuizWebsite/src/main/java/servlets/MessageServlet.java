package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.MessageDao;
import models.Message;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Map;

public class MessageServlet extends HttpServlet {
    /**
     * Handles the HTTP POST request to add a new message to the database.
     * Receives a JSON object containing message details from the client-side.
     * Converts the JSON data to a Map object and extracts 'from', 'to', and 'message' fields.
     * Saves the message using the MessageDao class.
     *
     * @param httpServletRequest  the request object representing the HTTP request
     * @param httpServletResponse the response object representing the HTTP response
     * @throws ServletException if the servlet encounters a problem during request handling
     * @throws IOException      if there is an I/O problem while reading the request data
     */
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        // Get the MessageDao instance from the application context
        MessageDao messageDao =  (MessageDao) httpServletRequest.getServletContext().getAttribute("messages");

        // Read the JSON data from the request body
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream()));
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }
        reader.close();

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonData = mapper.readValue(jsonBuilder.toString(), Map.class);

        String from = (String) jsonData.get("from");
        String to = (String) jsonData.get("to");
        String message = (String) jsonData.get("message");
        Message messageObj = new Message(from, to, message, new Timestamp(System.currentTimeMillis()));
        messageDao.addMessage(messageObj);
    }
}
