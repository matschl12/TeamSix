package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;


import java.lang.reflect.Array;
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
                    /* if (!searchField.getText().equals("")) {
                        observableMovies.clear();
                        observableMovies.addAll(fetchMoviesFilter(searchField.getText(), "none", 0, 0));
                    }
           /* // genreCombobox event if filtered by Genre
            if (!genreComboBox.getValue().equals("Filter by Genre")) {
                observableMovies.addAll(fetchMoviesFilter("",genreComboBox.getValue().toString(), 0, 0));
            }

            // event if filter by text and genre
            if (!genreComboBox.getValue().equals("Filter by Genre") && !searchField.getText().equals("")) {
                observableMovies.addAll(fetchMoviesFilter(searchField.getText(), genreComboBox.getValue().toString(), 0, 0));
            } */

        if (!searchField.getText().equals("")) {
                observableMovies.clear();
                    // query and...
                if (genreComboBox.getValue().toString() == "Filter by Genre" && yearComboBox.getValue() == ("Filter by Release Year") && ratingComboBox.getValue() ==("Filter by Rating")) {
                    observableMovies.addAll(fetchMoviesFilter(searchField.getText(),"none",0,0));
                    //  genre
                } else if (genreComboBox.getValue().toString() != ("Filter by Genre") && yearComboBox.getValue() == ("Filter by Release Year") && ratingComboBox.getValue() ==("Filter by Rating")) {
                    observableMovies.addAll(fetchMoviesFilter(searchField.getText(),genreComboBox.getValue().toString(),0,0));
                    // genre and year
                } else if (genreComboBox.getValue().toString() != ("Filter by Genre") && yearComboBox.getValue() != ("Filter by Release Year") && ratingComboBox.getValue() == ("Filter by Rating")) {
                    observableMovies.addAll(fetchMoviesFilter(searchField.getText(),genreComboBox.getValue().toString(), (Integer) yearComboBox.getValue(),0));
                    // genre and rating
                } else if (genreComboBox.getValue().toString() != ("Filter by Genre") && yearComboBox.getValue() == ("Filter by Release Year") && ratingComboBox.getValue() != ("Filter by Rating")) {
                    observableMovies.addAll(fetchMoviesFilter(searchField.getText(),genreComboBox.getValue().toString(), 0, (Double) ratingComboBox.getValue()));
                    // year
                } else if(genreComboBox.getValue().toString() == ("Filter by Genre") && yearComboBox.getValue() != ("Filter by Release Year") && ratingComboBox.getValue() == ("Filter by Rating")) {
                    observableMovies.addAll(fetchMoviesFilter(searchField.getText(), "none", (Integer) yearComboBox.getValue(), 0));
                    // rating
                } else if (genreComboBox.getValue().toString() == ("Filter by Genre") && yearComboBox.getValue() == ("Filter by Release Year") && ratingComboBox.getValue() != ("Filter by Rating")) {
                    observableMovies.addAll(fetchMoviesFilter(searchField.getText(), "none", 0, (Double) ratingComboBox.getValue()));
                    // year and rating
                } else if(genreComboBox.getValue().toString() == ("Filter by Genre") && yearComboBox.getValue() == ("Filter by Release Year") && ratingComboBox.getValue() != ("Filter by Rating")) {
                    observableMovies.addAll(fetchMoviesFilter(searchField.getText(),"none", (Integer) yearComboBox.getValue(), (Double) ratingComboBox.getValue()));
                    // genre and year and rating
                } else {
                    observableMovies.addAll(fetchMoviesFilter(searchField.getText(), genreComboBox.getValue().toString() ,0,0));
                }
            }

        else if (genreComboBox.getValue().toString() != ("Filter by Genre")) {
            observableMovies.clear();
                // genre and....
            if (searchField.getText().equals("") && yearComboBox.getValue() == ("Filter by Release Year") && ratingComboBox.getValue() == ("Filter by Rating")) {
                observableMovies.addAll(fetchMoviesFilter("", genreComboBox.getValue().toString(), 0, 0));
                // rating
            } else if(searchField.getText().equals ("") && yearComboBox.getValue() == ("Filter by Release Year") && ratingComboBox.getValue() != ("Filter by Rating")) {
                observableMovies.addAll(fetchMoviesFilter("", genreComboBox.getValue().toString(), 0, (Double) ratingComboBox.getValue()));
                // year
            } else if(searchField.getText().equals("") && yearComboBox.getValue() != ("Filter by Release Year") && ratingComboBox.getValue() == ("Filter by Rating")) {
                observableMovies.addAll(fetchMoviesFilter("", genreComboBox.getValue().toString(), (Integer) yearComboBox.getValue(), 0));
                // year and rating
            } else {
                observableMovies.addAll(fetchMoviesFilter("", genreComboBox.getValue().toString() , (Integer) yearComboBox.getValue(), (Double) ratingComboBox.getValue()));
            }

            }


                // old
             // event if filter by text and genre

                /*String input = searchField.getText().toLowerCase();
                    for (Movie movie: allMovies){
                    for (Genre genre : Genre.values()) {
                        if (genreComboBox.getValue().equals(genre) && movie.getGenres().contains(genre) &&
                                (movie.getDescription().toLowerCase().contains(input) || movie.getTitle().toLowerCase().contains(input))) {
                            observableMovies.add(movie);
                        }
                    }
                }
            } */


        }
        );


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

    // old
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
