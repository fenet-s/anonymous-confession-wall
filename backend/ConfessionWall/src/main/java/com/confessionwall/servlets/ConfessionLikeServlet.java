package com.confessionwall.servlets;

import com.confessionwall.dao.ConfessionDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/confessions/likes")
public class ConfessionLikeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ConfessionDAO confessionDAO;

    @Override
    public void init() {
        confessionDAO = new ConfessionDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 String idParam = request.getParameter("id");
         String action = request.getParameter("action");

         response.setContentType("application/json");
         response.setCharacterEncoding("UTF-8");

         if (idParam == null || idParam.isEmpty()) {
             response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
             return;
         }

         try {
             int id = Integer.parseInt(idParam);
             
             if ("unlike".equals(action)) {
                 confessionDAO.decrementLikes(id);
                 response.getWriter().write("{\"message\": \"Like removed\"}");
             } else {
                 confessionDAO.incrementLikes(id);
                 response.getWriter().write("{\"message\": \"Like added\"}");
             }
             
             response.setStatus(HttpServletResponse.SC_OK);

         } catch (Exception e) {
             response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
             e.printStackTrace();
         }
    }
}