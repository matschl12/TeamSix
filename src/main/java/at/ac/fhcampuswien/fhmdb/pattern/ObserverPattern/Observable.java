package at.ac.fhcampuswien.fhmdb.pattern.ObserverPattern;

import at.ac.fhcampuswien.fhmdb.Exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.WatchlistMovieEntity;


import java.util.List;

public interface Observable {
    void addMovie(WatchlistMovieEntity movie) throws DatabaseException;
    void removeMovie(WatchlistMovieEntity movie) throws DatabaseException;
}
