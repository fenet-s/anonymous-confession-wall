import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/chat")
public class ChatServlet extends HttpServlet {

    // Shared message list (thread-safe)
    private static final List<String> messages =
            Collections.synchronizedList(new ArrayList<>());

    // Get all messages
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();

        synchronized (messages) {
            for (String msg : messages) {
                out.println(msg);
            }
        }
    }

    // Post a new anonymous message
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String message = req.getParameter("message");

        if (message != null && !message.trim().isEmpty()) {
            messages.add("Anonymous: " + message);
        }

        resp.setStatus(HttpServletResponse.SC_OK);
    }
}

