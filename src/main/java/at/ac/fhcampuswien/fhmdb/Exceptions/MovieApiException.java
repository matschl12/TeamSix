package at.ac.fhcampuswien.fhmdb.Exceptions;

import com.fasterxml.jackson.core.JsonParseException;
import javafx.scene.control.Alert;

public class MovieApiException extends RuntimeException {
    public MovieApiException(String message) {
        super(message);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("An exception occurred");
        alert.setHeaderText("MovieApiException");
        alert.setContentText(message);
        alert.showAndWait();
    }
}

