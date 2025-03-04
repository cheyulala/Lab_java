package com.example.java_pr_task_4;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


// реализация отображения таблицы
public class TableViewController {

    @FXML
    private TableView<Car> tableView;
    @FXML
    private TableColumn<Car, String> brandColumn;
    @FXML
    private TableColumn<Car, Integer> year_productionColumn;
    @FXML
    private TableColumn<Car, String> model_nameColumn;
    @FXML
    private TableColumn<Car, String> statusColumn;
    @FXML
    private void initialize() {
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        year_productionColumn.setCellValueFactory(new PropertyValueFactory<>("year_production"));
        model_nameColumn.setCellValueFactory(new PropertyValueFactory<>("model_name"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        loadData();
    }

    private void loadData() {
        ObservableList<Car> Cars = FXCollections.observableArrayList();
        DBAdapter adapter = new DBAdapter();
        adapter.create_or_connection();

        try (ResultSet rs = adapter.SelectData()) {


            while (rs.next()) {
                Cars.add(new Car(
                        rs.getString("brand"),
                        rs.getInt("year_production"),  // Извлекаем возраст как целое число
                        rs.getString("model_name"),
                        rs.getString("status")
                ));
            }
        } catch (Exception e) { showAlert("Error", "Failed to load data: " + e.getMessage());}
        tableView.setItems(Cars);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
