package com.example.webapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TasksDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    // Метод для отримання з'єднання з базою даних
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public static Tasks getTaskById(int carId) {
        Tasks tasks = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            String query = "SELECT * FROM tasks WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, carId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                tasks = new Tasks();
                tasks.setId(resultSet.getInt("id"));
                tasks.setTitle(resultSet.getString("name"));
                tasks.setStatus(resultSet.getString("price"));
                tasks.setDescription(resultSet.getString("description"));
                // Встановлення інших властивостей, якщо вони є
            }
            if (tasks==null)
            {
                tasks=new Tasks();
                tasks.setId(carId);
                tasks.setTitle("f");
                tasks.setDescription("gf");
                tasks.setStatus("12");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Обробка винятків
        } finally {
            // Закриття ресурсів (resultSet, statement, connection)
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tasks;
    }
    public boolean deleteTask(int taskId) {
        boolean deleted = false;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "DELETE FROM tasks WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, taskId);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                deleted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Обробка винятків
        }
        return deleted;
    }
    // Метод для отримання списку всіх десертів з бази даних
    public static List<Tasks> getAllTask() {
        List<Tasks> tasks = new ArrayList<>();
        try (Connection conn = getConnection();

             PreparedStatement statement = conn.prepareStatement("SELECT * FROM tasks");
             ResultSet resultSet = statement.executeQuery()) {
            Class.forName("org.postgresql.Driver");
            while (resultSet.next()) {
                Tasks task = new Tasks();
                task.setId(resultSet.getInt("id"));
                task.setTitle(resultSet.getString("title"));
                task.setStatus(resultSet.getString("status"));
                task.setDescription(resultSet.getString("description"));
                // Інші поля, якщо необхідно
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Обробка винятків при роботі з БД
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }

    // Інші методи для роботи з даними в БД (додавання, видалення, оновлення)
}
