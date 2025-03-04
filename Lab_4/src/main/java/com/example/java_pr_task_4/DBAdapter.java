package com.example.java_pr_task_4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// ДБ адаптор для подключения ДБ
public class DBAdapter {
    Connection con; // Поле для хранения соединения с базой данных
    ResultSet  SelectData() throws SQLException {String sql = "SELECT * FROM car_dump"; // Метод для выполнения SQL-запроса на выборку данных из таблицы car_dump
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

    //создание или подключение к бд
    void create_or_connection() {
        try {
            con = DriverManager.getConnection("jdbc:sqlite:car_dump.SQLite"); // Подключаемся к Cars.db
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS car_dump (\n" +
                    "\tid INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "\tbrand TEXT NOT NULL,\n" +
                    "\tyear_production INTEGER NOT NULL,\n" +
                    "\tmodel_name TEXT NOT NULL,\n" +
                    "\tstatus TEXT NOT NULL\n" +
                    ");";
            stmt.execute(sql);
            stmt.close();
            System.out.println("Table 'car_dump' created or already exists.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //вставка данных в таблицу (формирование SQL команд)
    void insert_data(String brand, int year_production, String model_name, String status) throws SQLException {
        String sql = "INSERT INTO car_dump (brand, year_production, model_name, status) VALUES (?, ?, ?, ?)"; // Используем параметризированный запрос
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, brand);
        pstmt.setInt(2, year_production);
        pstmt.setString(3, model_name);
        pstmt.setString(4, status);
        pstmt.executeUpdate();
        pstmt.close();
        System.out.println("Inserted data into 'car_dump'.");
    }

    //удаление данных из таблицы (по ID)
    void delete_data(Integer id) throws SQLException {
        String sql = "DELETE FROM car_dump WHERE id = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        pstmt.close();
        System.out.println("Deleted data with ID: " + id);
    }
}
