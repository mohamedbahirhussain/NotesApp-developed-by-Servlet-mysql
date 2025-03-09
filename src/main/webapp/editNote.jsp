<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.utils.DBConnections" %>
<%@ page import="java.sql.*" %>
<%
    int noteId = Integer.parseInt(request.getParameter("noteId"));
    String title = "";
    String content = "";

    try (Connection conn = DBConnections.initializeDatabase()) {
        String query = "SELECT title, content FROM notes WHERE note_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, noteId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    title = rs.getString("title");
                    content = rs.getString("content");
                }
            }
        }
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
%>
<html>
<head>
    <title>Edit Note</title>
    <link rel="stylesheet" href="style.css">
    
</head>
<body>
    <h1>Edit Note</h1>
    <form action="editNote" method="post">
        <input type="hidden" name="noteId" value="<%= noteId %>">
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" value="<%= title %>" required><br><br>
        <label for="content">Content:</label><br>
        <textarea id="content" name="content" rows="10" cols="30" required><%= content %></textarea><br><br>
        <input type="submit" value="Update Note">
    </form>
    <br>
    <a href="dashboard.html">Back to Dashboard</a>
</body>
</html>
