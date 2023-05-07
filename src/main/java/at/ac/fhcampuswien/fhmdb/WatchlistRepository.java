package at.ac.fhcampuswien.fhmdb;

import java.util.List;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;

public class WatchlistRepository {
    static Dao <WatchlistMovieEntity, Long> dao = initializeDao();

    private static Dao<WatchlistMovieEntity, Long> initializeDao() {
        try {
            return Database.getWatchlistMovieDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void removeMovie(WatchlistMovieEntity movie) throws ExceptionHandling.DatabaseException {
        try {
            dao.delete(movie);
        } catch (SQLException e) {
            throw new ExceptionHandling.DatabaseException("Error while removing movie from watchlist in database", e);
        }
    }

    public static List<WatchlistMovieEntity> getAllMovies() throws ExceptionHandling.DatabaseException {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            throw new ExceptionHandling.DatabaseException("Error while fetching watchlist movies from database", e);
        }
    }

    public static void addMovie(WatchlistMovieEntity movie) throws ExceptionHandling.DatabaseException {
        try {
            dao.createOrUpdate(movie);
        } catch (SQLException e) {
            throw new ExceptionHandling.DatabaseException("Error while adding movie to watchlist in database", e);
        }
    }

    public static WatchlistMovieEntity changeMovieToWatchlistMovie(Movie movie) {
        WatchlistMovieEntity entity = new WatchlistMovieEntity(movie.id, movie.getTitle(), movie.getDescription(), movie.getGenres(), movie.getReleaseYear(), movie.imgUrl, movie.lengthInMinutes, movie.getRating());
        return entity;
    }

}
