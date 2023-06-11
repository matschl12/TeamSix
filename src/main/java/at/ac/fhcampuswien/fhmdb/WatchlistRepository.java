package at.ac.fhcampuswien.fhmdb;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import at.ac.fhcampuswien.fhmdb.Exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.pattern.ObserverPattern.Observable;
import at.ac.fhcampuswien.fhmdb.pattern.ObserverPattern.Observer;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;


import java.sql.SQLException;
import java.util.Objects;



public class WatchlistRepository implements Observable {

    // Exercise 4 SINGLETON Pattern
    private static WatchlistRepository instance;

    private List<Observer> observers = new ArrayList<>();


    static {
        try {
            instance = new WatchlistRepository();
        } catch (DatabaseException e) {
            System.out.println();
        }
    }

    private WatchlistRepository() throws DatabaseException {

    }
    public static WatchlistRepository getInstance() {
        return instance;
    }

    // Exercise 3
    Dao <WatchlistMovieEntity, Long> dao = initializeDao();

    public static ConnectionSource createConnectionSource() throws DatabaseException
    {
        try {
            return new JdbcConnectionSource(Database.DATABASE_URL,Database.DATABASE_USER,Database.DATABASE_PASSWORD);
        }  catch (SQLException e) {
            throw new DatabaseException("Couldnt create Connection Source");
        }
    }

   private static Dao<WatchlistMovieEntity, Long> initializeDao() throws DatabaseException {
        try {
            return DaoManager.createDao(createConnectionSource(), WatchlistMovieEntity.class);
        } catch (SQLException e) {
            throw new DatabaseException("Couldnt initialize Dao");
        }
    }



    // get all movies from database
    public List<WatchlistMovieEntity> getAllMovies() throws DatabaseException{

        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            throw new DatabaseException("getAllMovies doesnt work");
        }
    }

    // remove movie from database
    public void removeMovie(WatchlistMovieEntity movie) throws DatabaseException {

        try {
            List<WatchlistMovieEntity> entityList = getAllMovies();
            for (WatchlistMovieEntity entity : entityList) {
                if (Objects.equals(movie.apiId, entity.apiId)) {
                    dao.delete(entity);
                    notifyObserver(movie.title + " was removed from the Watchlist.");
                }
            }
        } catch (SQLException  e) {
            throw new DatabaseException("Remove Movie Failure");
        }

    }

    // add movie to database
    public void addMovie(WatchlistMovieEntity movie) throws DatabaseException {

        try {
            dao.create(movie);
            notifyObserver("Added " + movie.title + " to Watchlist.");
            // notifyObserver("The Movie: " + movie.title + " is already in the Watchlist.");
        } catch (SQLException e) {
            throw new DatabaseException("Error while adding movie to watchlist in database");
        }
    }

    // movie to watchlistmovieentity change
    public static WatchlistMovieEntity changeMovieToWatchlistMovie(Movie movie) {
        WatchlistMovieEntity entity = new WatchlistMovieEntity(movie.id, movie.getTitle(), movie.getDescription(), movie.getGenres(), movie.getReleaseYear(), movie.imgUrl, movie.lengthInMinutes, movie.getRating());
        return entity;
    }

    // watchlistmovieentity to movie change
    public static Movie changeWatchlistMovieToMovie(WatchlistMovieEntity entity) {
       for (Movie movie : HomeController.allMovies) {
           if (Objects.equals(movie.id, entity.apiId)) {
               return movie;
           }
       }
       return null;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(String msg) {
        for(Observer observer: observers) {
            observer.watchListUpdate(msg);
        }
    }
}
