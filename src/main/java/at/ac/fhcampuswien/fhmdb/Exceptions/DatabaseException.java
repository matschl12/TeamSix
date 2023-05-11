package at.ac.fhcampuswien.fhmdb.Exceptions;

import java.sql.SQLException;

public class DatabaseException extends AllExceptionsHandler {

    public DatabaseException(String message) {
        super("DatabaseException" , message);//shall be received by AllExceptionsHandler
    }
    public DatabaseException(String message, SQLException e) {
        super("DatabaseException" , message);//shall be received by AllExceptionsHandler
/*
        super(message);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("An exception occurred");
        alert.setHeaderText("DatabaseException");
        alert.setContentText(message);
        alert.showAndWait();

 */
    }

}
