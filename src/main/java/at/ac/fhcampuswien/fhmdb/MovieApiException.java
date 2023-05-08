package at.ac.fhcampuswien.fhmdb;

import com.fasterxml.jackson.core.JsonParseException;
import javafx.scene.control.Alert;

public class MovieApiException extends RuntimeException {
    public MovieApiException(String message) {
        super(message);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("An error occurred:");
        alert.setContentText(message);
        alert.showAndWait();
    }
}

