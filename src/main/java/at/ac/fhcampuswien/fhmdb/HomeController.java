package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;

    @FXML
    public JFXComboBox yearComboBox;

    @FXML
    public JFXComboBox ratingComboBox;

    @FXML
    public JFXButton sortBtn;

    @FXML
    public JFXButton resetBtn;
    @FXML
    public JFXButton switchSceneBtn;

    public static List<Movie> allMovies = new ArrayList<>();

    public final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    public static List<Movie> watchList = new ArrayList<>();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Database.getDatabase();
        allMovies = Movie.initializeMovies();
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell(onAddToWatchlistClicked)); // use custom cell factory to display data
        // add watchlist movies from database to watchlist
        try {
            WatchlistRepository wr = new WatchlistRepository();
            watchList.addAll(fillWatchlist(wr.getAllMovies()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(Genre.values());

        yearComboBox.setPromptText("Filter by Release Year");
        List<Integer> allReleaseYears = new ArrayList<>();
        for (Movie movie : allMovies) {
            int releaseYear = movie.getReleaseYear();
            if(!allReleaseYears.contains(releaseYear)) {
                allReleaseYears.add(releaseYear);
            }
            Collections.sort(allReleaseYears);

        }
        yearComboBox.getItems().addAll(allReleaseYears);
        //hier methode dass die Filter angezeigt werden aufrufen, muss für Exercise 2 gemacht werden

        ratingComboBox.setPromptText("Filter by Rating");
        List<Double> allRatings = new ArrayList<>();
         for(double i = 1 ; i<=10; i = i + 0.5)
         {
             allRatings.add(i);
         }
        ratingComboBox.getItems().addAll(allRatings);
        //hier methode dass die Filter angezeigt werden aufrufen, muss für Exercise 2 gemacht werden


        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here


        searchBtn.setOnAction(actionEvent -> {

                    String searchFieldString = "";
                    if (!searchField.getText().equals("")) {
                        searchFieldString = searchField.getText();
                    }


                    String genreString = "";
                    if (genreComboBox.getValue() != null) {
                        genreString = genreComboBox.getValue().toString();
                    }

                    String releaseYearInt = "";
                    if (yearComboBox.getValue() != null){

                        releaseYearInt = yearComboBox.getValue().toString();
                    }

                    String ratingDouble = "";
                    if (ratingComboBox.getValue() != null ) {
                        ratingDouble = ratingComboBox.getValue().toString();
                    }

                    switchSceneBtn.setText("Switch to Watchlist");

                    observableMovies.clear();
                    observableMovies.addAll(MovieAPI.fetchMovies(searchFieldString,genreString,releaseYearInt,ratingDouble));


                });

        /* searchField.onKeyReleasedProperty().addListener(observable -> {
            observableMovies.clear();
            observableMovies.addAll(fetchMoviesFilter("","","",""));
            initializeHomeController();
            resetFilter();
        }); */


        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                // TODO sort observableMovies ascending
                sortAscending(observableMovies);

                sortBtn.setText("Sort (desc)");
            } else {
                // TODO sort observableMovies descending
                sortDescending(observableMovies);
                sortBtn.setText("Sort (asc)");
            }
        });

        //Reset button -> resets all filters and shows the start-screen
        resetBtn.setOnAction(actionEvent -> {
            initializeHomeController();
            resetFilter();
        });

    }
    // homeController beginning state
    public void initializeHomeController() {
        observableMovies.clear();
        observableMovies.addAll(allMovies);
    }

    // sort movies ascending
    public void sortAscending(ObservableList<Movie> movies) {
        movies.sort(Comparator.comparing(Movie::getTitle));
    }

    // sort movies descending
    public void sortDescending(ObservableList<Movie> movies) {
        movies.sort(Comparator.comparing(Movie::getTitle).reversed());
    }

    // reset button method
    public void resetFilter() {
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.setValue(null);
        yearComboBox.setPromptText("Filter by Release Year");
        yearComboBox.setValue(null);
        ratingComboBox.setPromptText("Filter by Rating");
        ratingComboBox.setValue(null);
        searchField.setText("");
    }

    // getMostPopularActor method

    public static String getMostPopularActor(List<Movie> movies) {
        Map<String, Long> actorCount = movies.stream()
                .flatMap(movie -> movie.getMainCast().stream())
                .collect(Collectors.groupingBy(actor -> actor, Collectors.counting()));
        return actorCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
    }


    // longest Movie title - method
    public static int getLongestMovieTitle(List<Movie> movies) {
        return movies.stream()
                .mapToInt(movie -> movie.getTitle().length())
                .max()
                .orElse(0);
    }

    // Director Count method
    public static long countMoviesFrom(List<Movie> movies, String director) {
        return movies.stream()
                .filter(movie -> movie.getDirectors().contains(director))
                .count();
    }

    // Movies in between years Method
    public static List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        return movies.stream()
                .filter(movie -> movie.getReleaseYear() >= startYear && movie.getReleaseYear() <= endYear)
                .sorted(Comparator.comparingInt(Movie::getReleaseYear))
                .collect(Collectors.toList());
    }

    // Exercise 3 Business Layer
    // onAddToWatchlistButton clicked event
    private final ClickEventHandler onAddToWatchlistClicked = (clickedItem) -> {
        if (clickedItem instanceof Movie) {
            Movie movie = (Movie) clickedItem;
            WatchlistRepository wr = new WatchlistRepository();
            try {
                if(watchList.contains(movie)){
                        wr.removeMovie(WatchlistRepository.changeMovieToWatchlistMovie(movie));

                    watchList.remove(movie);
                    reloadWatchlist();
                } else {
                    wr.addMovie(WatchlistRepository.changeMovieToWatchlistMovie(movie));
                    watchList.add(movie);
                    reloadWatchlist();
                }
            } catch (DatabaseException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    };

    // fill watchlist with watchlistmovies
   public List<Movie> fillWatchlist(List<WatchlistMovieEntity> entities) {
       List<Movie> watchlistMovies = new ArrayList<>();
       System.out.println(entities);
       if (!entities.isEmpty()) {
           for (WatchlistMovieEntity entity : entities) {
               watchlistMovies.add(WatchlistRepository.changeWatchlistMovieToMovie(entity));
           }
       }
       return watchlistMovies;
   }

   public void reloadWatchlist() {
       if (switchSceneBtn.getText().equals("Switch to Homepage")) {
           observableMovies.clear();
           observableMovies.addAll(watchList);
       }
   }

   // switch between watchlist and allMovies
    public void switchScene()
    {
        if(switchSceneBtn.getText().equals("Switch to Watchlist"))
        {
            observableMovies.clear();
            System.out.println(watchList);
            observableMovies.addAll(watchList); //show all movies from the watchlist
            switchSceneBtn.setText("Switch to Homepage");
        }
        else
        {
            observableMovies.clear();
            System.out.println(watchList);
            observableMovies.addAll(allMovies); //show all movies
            switchSceneBtn.setText("Switch to Watchlist");
        }
    }

    // main method for method testing
    public static void main(String[] args) {
        HomeController controller = new HomeController();
        List<Movie> movielist = new ArrayList<>();
        movielist.addAll(controller.allMovies);
        System.out.println(countMoviesFrom(movielist,"Peter Jackson"));
        System.out.println(getLongestMovieTitle(movielist));
        System.out.println(getMostPopularActor(movielist));
        System.out.println(getMoviesBetweenYears(movielist,1900,1980));
    }
}
