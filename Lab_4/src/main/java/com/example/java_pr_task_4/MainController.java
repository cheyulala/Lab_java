package com.example.java_pr_task_4;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;




public class MainController {
    @FXML
    private TextField brandField;
    @FXML
    private TextField year_productionField;
    @FXML
    private TextField model_nameField;
    @FXML
    private TextField statusField;
    @FXML
    private Button addButton;
    @FXML
    private Button showTableButton;

    @FXML
    //кнопки
    private void addCar(ActionEvent event) {
        String brand = brandField.getText();
        String year_productionText = year_productionField.getText();  // Получаем текст из текстового поля
        String model_name = model_nameField.getText();
        String status = statusField.getText();
        if (brand.isEmpty() || year_productionText.isEmpty() || model_name.isEmpty() || status.isEmpty()) {
            showAlert("Error", "All fields must be filled!");
            return;
        }

        try {
            int year_production = Integer.parseInt(year_productionText);  // Преобразуем возраст в целое число
            DBAdapter adapter = new DBAdapter();
            adapter.create_or_connection();
            adapter.insert_data(brand, year_production, model_name, status );

        } catch (NumberFormatException e) {
            showAlert("Error", "Year must be a valid number!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void showTable(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TableView.fxml"));
        Scene scene = new Scene(loader.load(), 230, 230);
        Stage stage = new Stage();

        stage.setTitle("Car list");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        try {}
        catch (Exception e) {
            showAlert("Error", "Failed to open table view: " + e.getMessage());
        }
    }

    private void clearFields() {
        brandField.clear();
        year_productionField.clear();
        model_nameField.clear();
        statusField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
