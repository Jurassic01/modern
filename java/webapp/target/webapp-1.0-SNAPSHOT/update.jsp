<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.webapp.TasksDAO, com.example.webapp.Tasks" %>
<!-- інші імпорти, стилі, заголовок сторінки і т.д. -->

<!DOCTYPE html>
<html>
<head>
  <title>Update Dessert</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
    }

    h1 {
      text-align: center;
    }

    form {
      max-width: 400px;
      margin: 0 auto;
      padding: 20px;
      border: 1px solid #ccc;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      background-color: #f9f9f9; /* Зміна колірного фону форми */
    }

    label {
      display: block;
      margin-bottom: 8px;
      font-weight: bold; /* Зміна жирності тексту міток */
    }

    input[type="text"],
    input[type="number"],
    textarea {
      width: calc(100% - 12px);
      padding: 8px;
      margin-bottom: 15px;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
      font-size: 16px; /* Збільшення розміру шрифту для полів вводу */
    }

    input[type="submit"] {
      background-color: #4caf50;
      color: #fff;
      padding: 12px 24px; /* Збільшення відступів кнопки */
      border: none;
      border-radius: 4px;
      cursor: pointer;
      font-size: 18px; /* Збільшення розміру шрифту кнопки */
      text-transform: uppercase; /* Перетворення тексту кнопки на верхній регістр */
    }

    input[type="submit"]:hover {
      background-color: #45a049;
    }
  </style>
</head>
<body>
<h1>Update Car</h1>
<%
  int taskId = Integer.parseInt(request.getParameter("id"));
  Tasks task = TasksDAO.getTaskById(taskId);
%>

<form action="update" method="post">
  <input type="hidden" name="id" value="<%= task.getId() %>">
  <label for="name">New Name:</label>
  <input type="text" id="name" name="name" value="<%= task.getTitle() %>" required><br>
  <label for="price">New Price:</label>
  <input type="number" id="price" name="price" value="<%= task.getStatus() %>" min="0" step="0.01" required><br>
  <label for="description">New Description:</label><br>
  <textarea id="description" name="description" rows="4" cols="50" required><%= task.getDescription() %></textarea><br>
  <input type="submit" value="Update">
</form>
</body>
</html>
