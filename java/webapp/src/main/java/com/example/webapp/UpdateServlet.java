package com.example.webapp;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.postgresql.Driver;
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String status = request.getParameter("status");
        String description = request.getParameter("description");

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE tasks SET title= ?, status = ?, description = ? WHERE id = ?");

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, status);
            preparedStatement.setString(3, description);
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();

            connection.close();

            // Після успішного оновлення десерту перенаправлення на головну сторінку або іншу сторінку
            response.sendRedirect("index.jsp");
        } catch (SQLException e) {
            // Обробка винятків SQL
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

