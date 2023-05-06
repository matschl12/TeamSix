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
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import static at.ac.fhcampuswien.fhmdb.MovieAPI.*;

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

    public List<Movie> allMovies = Movie.initializeMovies();

    public final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    public static List<Movie> watchList = new ArrayList<>();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

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
        /*for (Movie movie : allMovies) {
            double rating = movie.getRating();
            if(!allRatings.contains(rating)) {
                allRatings.add(rating);
            }
            Collections.sort(allRatings);

        }
        */
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

                    int releaseYearInt = 0;
                    if (yearComboBox.getValue() != null){

                        releaseYearInt = (int) yearComboBox.getValue();
                    }

                    double ratingDouble = 0;
                    if (ratingComboBox.getValue() != null ) {
                        ratingDouble = (Double) ratingComboBox.getValue();
                    }

                    observableMovies.clear();
                    observableMovies.addAll(fetchMoviesFilter(searchFieldString,genreString,releaseYearInt,ratingDouble));


                });

        searchField.onKeyReleasedProperty().addListener(observable -> {
            observableMovies.clear();
            observableMovies.addAll(fetchMovies());
            initializeHomeController();
            resetFilter();
        });


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
    private final ClickEventHandler onAddToWatchlistClicked = (clickedItem) -> {

    };



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
