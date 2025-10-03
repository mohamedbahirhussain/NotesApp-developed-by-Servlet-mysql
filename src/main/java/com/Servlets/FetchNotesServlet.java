package com.Servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class FetchNotesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username"); // Get username from session
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Map<String, String>> notes = new ArrayList<>();
        
        try {
            // Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testintalio", "root", "root123");
            
            String query = "SELECT * FROM notes WHERE username = '" + username + "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                Map<String, String> note = new HashMap<>();
                note.put("id", rs.getString("id"));
                note.put("title", rs.getString("title"));
                note.put("content", rs.getString("content"));
                notes.add(note);
            }
            
            request.setAttribute("notes", notes);
            RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.html");
            dispatcher.forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

