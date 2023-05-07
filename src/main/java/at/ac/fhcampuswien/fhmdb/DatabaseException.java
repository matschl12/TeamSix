package at.ac.fhcampuswien.fhmdb;

import javafx.scene.control.Alert;

import java.sql.SQLException;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String message, SQLException e) {
            super(message);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog"); alert.setHeaderText("An error occurred:"); alert.setContentText(e.getMessage());
        alert.showAndWait();
        }

}
