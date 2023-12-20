<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.webapp.User, com.example.webapp.TasksDAO, com.example.webapp.Tasks" %>
<%@ page import="java.util.List" %>
<!-- Замініть your.package на фактичний шлях до ваших пакетів та класів -->

<%
    User user = (User) session.getAttribute("user"); // Отримання користувача з сесії

// Функція для перевірки, чи користувач увійшов у систему
    boolean isLoggedIn = (user != null);


%>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Task</title>
    <style>
        /* Кольори меню */
        ul.menu {
            background-color: #333; /* Темний фон меню */
            color: #fff; /* Білий текст */
            padding: 10px 0;
        }

        ul.menu li a {
            color: #fff; /* Білий текст посилань */
            padding: 8px 16px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        ul.menu li a:hover {
            background-color: #555; /* Темніше при наведенні */
        }

        /* Кольори для блоків з завданнями */
        .catalog {
            /* Залишаємо попередні стилі */
        }

        .task {
            background-color: #f9f9f9; /* Світлий фон блоку з завданням */
            border: 1px solid #ddd; /* Світлий рамка */
            margin-bottom: 15px; /* Додали відступ між завданнями */
            padding: 10px; /* Додали внутрішній відступ */
        }

        .task h3 {
            color: #333; /* Колір заголовка завдання */
            margin-top: 0; /* Виправили відступ відгори */
        }

        .task p {
            color: #666; /* Колір тексту опису та статусу */
            margin-bottom: 8px; /* Відступ між параграфами */
        }

        /* Кольори для кнопок в формах */
        .task form input[type="submit"] {
            background-color: #4caf50;
            color: #fff;
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .task form input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>

</head>
<body>
<ul class="menu">
    <%-- Відображення меню залежно від статусу користувача --%>
    <% if (isLoggedIn) { %>
    <li><a href="logout">Logout</a></li>
    <li><a href="Add.jsp">Add</a></li>
    <% } else { %>
    <li><a href="login.jsp">Login</a></li>
    <li><a href="registration.jsp">Register</a></li>
        <li><a href="Add.jsp">Add</a></li>
    <% } %>
</ul>

<div class="catalog">
    <% List<Tasks> tasks = TasksDAO.getAllTask(); %>
    <% for (Tasks task : tasks) { %>
    <div class="task">
        <h3><%= task.getTitle() %></h3>
        <p>Status: <%= task.getStatus() %></p>
        <p>Description: <%= task.getDescription() %></p>
        <form action="update.jsp" method="get">
            <input type="hidden" name="id" value="<%= task.getId() %>">
            <input type="submit" value="Update">
        </form>
        <form action="delete" method="post">
            <input type="hidden" name="taskId" value="<%= task.getId() %>">
            <input type="submit" value="Delete">
        </form>
    </div>
    <% } %>
</div>
</body>
</html>
