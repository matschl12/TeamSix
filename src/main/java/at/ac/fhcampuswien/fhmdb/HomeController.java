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


import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import static at.ac.fhcampuswien.fhmdb.MovieAPI.fetchMovies;
import static at.ac.fhcampuswien.fhmdb.MovieAPI.fetchMoviesFilter;

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
        // yearComboBox.getItems().addAll();
        //hier methode dass die Filter angezeigt werden aufrufen, muss für Exercise 2 gemacht werden

        ratingComboBox.setPromptText("Filter by Rating");
        List<Double> allRatings = new ArrayList<>();
        for (Movie movie : allMovies) {
            double rating = movie.getRating();
            if(!allRatings.contains(rating)) {
                allRatings.add(rating);
            }
            Collections.sort(allRatings);

        }
        ratingComboBox.getItems().addAll(allRatings);
        //hier methode dass die Filter angezeigt werden aufrufen, muss für Exercise 2 gemacht werden


        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here

        searchBtn.setOnAction(actionEvent -> {
            // searchfield event if filtered by text

            if (!searchField.getText().equals("")) {
                observableMovies.clear();
                // filter by query and year
                /* if (!yearComboBox.getValue().equals("Filter by Release Year")) {
                    observableMovies.addAll(fetchMoviesFilter(searchField.getText(), "", (Integer) yearComboBox.getValue(), 0));
                    // filter by query and rating
                } else if (!ratingComboBox.getValue().equals("Filter by Rating")) {
                    observableMovies.addAll(fetchMoviesFilter(searchField.getText(),"",0, (Double) ratingComboBox.getValue()));
                    // filter by query and year and rating
                } else if (!yearComboBox.getValue().equals("Filter by Release Year") && !ratingComboBox.getValue().equals("Filter by Rating")) {
                    observableMovies.addAll(fetchMoviesFilter(searchField.getText(),"", (Integer) yearComboBox.getValue(),(Double) ratingComboBox.getValue()));
                    // filter only by query
                } else {
                    observableMovies.addAll(fetchMoviesFilter(searchField.getText(),"", 0, 0));
                } */
                observableMovies.addAll(fetchMoviesFilter(searchField.getText(),"", 0, 0));
                //searchFieldText(allMovies, searchField.getText().toLowerCase());
            }

                // genreCombobox event if filtered by Genre
            if (!genreComboBox.getValue().equals("Filter by Genre")) {
                observableMovies.clear();
                // filter by genre and year
               /* if (!yearComboBox.getValue().equals("Filter by Release Year")) {
                    observableMovies.addAll(fetchMoviesFilter("", genreComboBox.getValue().toString(), (Integer) yearComboBox.getValue(), 0));
                    // filter by genre and rating
                } else if (!ratingComboBox.getValue().equals("Filter by Rating")) {
                    observableMovies.addAll(fetchMoviesFilter("",genreComboBox.getValue().toString(),0, (Double) ratingComboBox.getValue()));
                    // filter by genre and year and rating
                } else if (!yearComboBox.getValue().equals("Filter by Release Year") && !ratingComboBox.getValue().equals("Filter by Rating")) {
                    observableMovies.addAll(fetchMoviesFilter("",genreComboBox.getValue().toString(), (Integer) yearComboBox.getValue(),(Double) ratingComboBox.getValue()));
                    // filter only by genre
                } else {
                    observableMovies.addAll(fetchMoviesFilter("",genreComboBox.getValue().toString(), 0, 0));
                } */
                observableMovies.addAll(fetchMoviesFilter("",genreComboBox.getValue().toString(), 0, 0));
                 /*for (Movie movie : allMovies) {
                    for (Genre genre : Genre.values()) {
                        if (genreComboBox.getValue().equals(genre) && movie.getGenres().contains(genre) && !observableMovies.contains(movie)) {
                            observableMovies.add(movie);
                        }
                    }
                } */
            }


            // event if filter by text and genre
           if (!genreComboBox.getValue().equals("Filter by Genre") && !searchField.getText().equals("")) {
                observableMovies.clear();
                observableMovies.addAll(fetchMoviesFilter(searchField.getText(), genreComboBox.getValue().toString(),0,0));
                /*String input = searchField.getText().toLowerCase();
                    for (Movie movie: allMovies){
                    for (Genre genre : Genre.values()) {
                        if (genreComboBox.getValue().equals(genre) && movie.getGenres().contains(genre) &&
                                (movie.getDescription().toLowerCase().contains(input) || movie.getTitle().toLowerCase().contains(input))) {
                            observableMovies.add(movie);
                        }
                    }
                } */
            }


           if (!yearComboBox.getValue().equals("Filter by Release Year")) {
               observableMovies.clear();
           }
        }
        );

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

    /* public String getMostPopularActor(List<Movie> movies) {
        movies.stream()
                .filter(Movie -> Movie.getMainCast())
                .collect(Collectors.toList());
    } */

    // method to search for a string in movie title or description
   /* public ObservableList<Movie> searchFieldText(List<Movie> allMovies, String textToSearch) {
        observableMovies.clear();
        for (Movie movie : allMovies) {
            if (movie.getDescription().toLowerCase().contains(textToSearch) || movie.getTitle().toLowerCase().contains(textToSearch)) {
                observableMovies.add(movie);
            }
        }

        return observableMovies ;
    } */

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
                .filter(movie -> movie.getDirector().equals(director))
                .count();
    }

    // Movies in between years Method
    public static List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        return movies.stream()
                .filter(movie -> movie.getYear() >= startYear && movie.getYear() <= endYear)
                .sorted(Comparator.comparingInt(Movie::getYear))
                .collect(Collectors.toList());
    }

}
