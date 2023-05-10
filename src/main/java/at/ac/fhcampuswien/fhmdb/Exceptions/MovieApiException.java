package at.ac.fhcampuswien.fhmdb.Exceptions;

import com.fasterxml.jackson.core.JsonParseException;
import javafx.scene.control.Alert;

public class MovieApiException extends ThrowExceptionsHere {
    public MovieApiException(String message) {
        super("MovieApiException" , message);//shall be received by ThrowExceptionsHere
    /*    Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("An exception occurred");
        alert.setHeaderText("MovieApiException");
        alert.setContentText(message);
        alert.showAndWait();
//        ThrowExceptionsHere throwAnException=new ThrowExceptionsHere("MovieApiException", message);
*/

        //SOLL ERLAUBEN DASS DIE APP STARTET, NICHT SOFORT POP-UP
    }
}

