package at.ac.fhcampuswien.fhmdb;

import java.sql.SQLException;

public class ExceptionHandling {
    public static class DatabaseException extends Exception {
        public DatabaseException(String message, SQLException e) {
            super(message);
        }
    }

    public class MovieApiException extends Exception {
        public MovieApiException(String message) {
            super(message);
        }
    }
}
