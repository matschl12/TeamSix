package at.ac.fhcampuswien.fhmdb;

import java.util.List;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class WatchlistRepository {
    Dao <WatchlistMovieEntity, Long> dao;

    public static void removeMovie(WatchlistMovieEntity movie) throws ExceptionHandling.DatabaseException {
        try {
            Dao<WatchlistMovieEntity, Long> dao = Database.getWatchlistMovieDao();
            dao.delete(movie);
        } catch (SQLException e) {
            throw new ExceptionHandling.DatabaseException("Error while removing movie from watchlist in database", e);
        }
    }

    public static List<WatchlistMovieEntity> getAllMovies() throws ExceptionHandling.DatabaseException {
        try {
            Dao<WatchlistMovieEntity, Long> dao = Database.getWatchlistMovieDao();
            return dao.queryForAll();
        } catch (SQLException e) {
            throw new ExceptionHandling.DatabaseException("Error while fetching watchlist movies from database", e);
        }
    }

    public static void addMovie(WatchlistMovieEntity movie) throws ExceptionHandling.DatabaseException {
        try {
            Dao<WatchlistMovieEntity, Long> dao = Database.getWatchlistMovieDao();
            dao.createOrUpdate(movie);
        } catch (SQLException e) {
            throw new ExceptionHandling.DatabaseException("Error while adding movie to watchlist in database", e);
        }
    }


}
