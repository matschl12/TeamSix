package at.ac.fhcampuswien.fhmdb;

public class MovieApiException extends RuntimeException{
    public MovieApiException(String message) {
        super(message);
    }
}
