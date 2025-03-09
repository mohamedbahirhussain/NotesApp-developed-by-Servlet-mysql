package com.Servlets;


import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/AddNoteServlet")
public class AddNoteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String username = (String) request.getSession().getAttribute("username"); // Assuming the username is stored in the session
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            // Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testintalio", "root", "root123");
            
            String query = "INSERT INTO notes (title, content, username) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, content);
            ps.setString(3, username);
            
            int result = ps.executeUpdate();
            
            if (result > 0) {
            	 response.sendRedirect("dashboard.html?username=" + username); // Redirect to dashboard after adding note
            } else {
                response.getWriter().write("Error adding note.");
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
