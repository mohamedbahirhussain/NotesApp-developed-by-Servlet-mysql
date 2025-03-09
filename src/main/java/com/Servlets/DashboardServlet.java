package com.Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DashboardServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        // Database connection
        try {
            // Database connection setup
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testintalio", "root", "root123");

            String query = "SELECT id, title, content FROM notes WHERE username = ?";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, username);

                try (ResultSet rs = pst.executeQuery()) {
                    List<Note> notes = new ArrayList<>();
                    while (rs.next()) {
                        Note note = new Note();
                        note.setId(rs.getInt("id"));
                        note.setTitle(rs.getString("title"));
                        note.setContent(rs.getString("content"));
                        notes.add(note);
                    }
                    // Set notes as a request attribute for use in the JSP page
                    request.setAttribute("notes", notes);
                    request.getRequestDispatcher("/dashboard.jsp").forward(request, response);  // Make sure it's dashboard.jsp
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("<h1>Error in retrieving notes!</h1>");
        }
    }
}
