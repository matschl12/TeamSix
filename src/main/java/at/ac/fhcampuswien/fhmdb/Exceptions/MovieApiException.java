package at.ac.fhcampuswien.fhmdb.Exceptions;

public class MovieApiException extends AllExceptionsHandler {
    public MovieApiException(String message) {
        super("MovieApiException" , message);//shall be received by AllExceptionsHandler
    /*    Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("An exception occurred");
        alert.setHeaderText("MovieApiException");
        alert.setContentText(message);
        alert.showAndWait();
//        AllExceptionsHandler throwAnException=new AllExceptionsHandler("MovieApiException", message);
*/

        //SOLL ERLAUBEN DASS DIE APP STARTET, NICHT SOFORT POP-UP
    }
}

