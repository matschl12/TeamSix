package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.Exceptions.DatabaseException;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;

public class Database {
    public static String DATABASE_URL = "jdbc:h2:file: ./db/watchlistdb";
    public static final String DATABASE_USER = "root";
    public static final String DATABASE_PASSWORD = "password";

    private static ConnectionSource connectionSource;
    static Dao<WatchlistMovieEntity, Long> dao;
    private static Database instance;

    // Database Constructor
    private Database() throws DatabaseException {
        try {
            createConnectionSource();
            dao = DaoManager.createDao(connectionSource, WatchlistMovieEntity.class);
            createTables();
        } catch (SQLException e) {
            throw new DatabaseException("Couldn't connect to Database");
        }
    }
    // database for testing purposes
    public void testDB() throws SQLException {
        WatchlistMovieEntity entity = new WatchlistMovieEntity("abcde", "Hallo", "Ja", new ArrayList<>(), 5, "test", 6, 8.2);
        dao.create(entity);
    }
    public static Database getDatabase() throws DatabaseException {
        if(instance == null) {
            instance = new Database();
        }
        return instance;
    }
    // create connection source
    public void createConnectionSource () throws DatabaseException
    {
        try {
            connectionSource = new JdbcConnectionSource(DATABASE_URL,DATABASE_USER,DATABASE_PASSWORD);
        } catch (SQLException e) {
            throw new DatabaseException("Couldn't create Connection Source");
        }
    }

    // methods not in use
    public static ConnectionSource getConnectionSource() throws DatabaseException {
        try {
            if (connectionSource == null) {
                connectionSource = new JdbcConnectionSource(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            }
            return connectionSource;
        } catch (SQLException e) {
            throw new DatabaseException("Couldn't get Connection Source");
        }

    }

    // create tables
    public void createTables() throws DatabaseException
    {
        try {
            TableUtils.createTableIfNotExists(connectionSource, WatchlistMovieEntity.class);
        } catch (SQLException e) {
            throw new DatabaseException("Couldn't create tables");
        }

    }

    // we don't use this method

    public static Dao<WatchlistMovieEntity, Long> getWatchlistMovieDao() throws SQLException, DatabaseException {
        if (dao == null) {
            dao = DaoManager.createDao(getConnectionSource(), WatchlistMovieEntity.class);
        }
        return dao;
    }

}
