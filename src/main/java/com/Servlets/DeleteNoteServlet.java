package com.Servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class DeleteNoteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int noteId = Integer.parseInt(request.getParameter("id"));
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            // Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testintalio", "root", "root123");
            
            String query = "DELETE FROM notes WHERE id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, noteId);
            
            int result = ps.executeUpdate();
            
            if (result > 0) {
                response.sendRedirect("dashboard.html"); // Redirect to dashboard after deletion
            } else {
                response.getWriter().write("Error deleting note.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
