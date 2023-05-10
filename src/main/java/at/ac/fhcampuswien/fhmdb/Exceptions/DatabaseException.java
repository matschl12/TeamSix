package at.ac.fhcampuswien.fhmdb.Exceptions;

import javafx.scene.control.Alert;

import java.sql.SQLException;

public class DatabaseException extends Exception {

    public DatabaseException(String message) {
        super(message);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("An exception occurred");
        alert.setHeaderText("DatabaseException");
        alert.setContentText(message);
        alert.showAndWait();
    }

}
