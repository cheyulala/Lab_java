module com.example.java_pr_task_4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.java_pr_task_4 to javafx.fxml;
    exports com.example.java_pr_task_4;
}
