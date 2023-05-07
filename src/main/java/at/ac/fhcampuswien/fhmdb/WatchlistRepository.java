package at.ac.fhcampuswien.fhmdb;

import java.util.List;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.Objects;



public class WatchlistRepository {
    Dao <WatchlistMovieEntity, Long> dao = initializeDao();

    public static ConnectionSource createConnectionSource() throws SQLException
    {
        return new JdbcConnectionSource(Database.DATABASE_URL,Database.DATABASE_USER,Database.DATABASE_PASSWORD);
    }

   private static Dao<WatchlistMovieEntity, Long> initializeDao() {
        try {
            return DaoManager.createDao(createConnectionSource(), WatchlistMovieEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeMovie(WatchlistMovieEntity movie) throws DatabaseException, SQLException {
        List<WatchlistMovieEntity> entityList = getAllMovies();
        for (WatchlistMovieEntity entity : entityList) {
            if (Objects.equals(movie.apiId, entity.apiId)) {
                dao.delete(entity);
            }
        }

    }

    public List<WatchlistMovieEntity> getAllMovies() throws SQLException{
        return dao.queryForAll();
    }

    public void addMovie(WatchlistMovieEntity movie) throws DatabaseException {
        try {
            dao.create(movie);
        } catch (SQLException e) {
            throw new DatabaseException("Error while adding movie to watchlist in database", e);
        }
    }

    public static WatchlistMovieEntity changeMovieToWatchlistMovie(Movie movie) {
        WatchlistMovieEntity entity = new WatchlistMovieEntity(movie.id, movie.getTitle(), movie.getDescription(), movie.getGenres(), movie.getReleaseYear(), movie.imgUrl, movie.lengthInMinutes, movie.getRating());
        return entity;
    }

    public static Movie changeWatchlistMovieToMovie(WatchlistMovieEntity entity) {
       for (Movie movie : HomeController.allMovies) {
           if (Objects.equals(movie.id, entity.apiId)) {
               return movie;
           }
       }
       return null;
    }

}
