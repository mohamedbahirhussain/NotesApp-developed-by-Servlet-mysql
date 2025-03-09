<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Note</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="note-container">
        <h1>Add New Note</h1>
        <h3>Welcome, <span id="username">User</span></h3>

        <form action="AddNoteServlet" method="POST" class="note-form">
            <div class="form-group">
                <label for="title">Title:</label>
                <input type="text" id="title" name="title" required placeholder="Enter the title">
            </div>
            <div class="form-group">
                <label for="content">Content:</label>
                <textarea id="content" name="content" rows="4" placeholder="Enter the note content" required></textarea>
            </div>
            <div class="form-actions">
                <button type="submit" class="submit-btn">Save Note</button>
            </div>
        </form>

        <a href="dashboard.jsp" class="back-btn">Back to Dashboard</a>
    </div>

    <script src="script.js"></script>
</body>
</html>
