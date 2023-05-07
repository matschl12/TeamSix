package at.ac.fhcampuswien.fhmdb;

import java.sql.SQLException;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String message, SQLException e) {
            super(message);
        }

}
