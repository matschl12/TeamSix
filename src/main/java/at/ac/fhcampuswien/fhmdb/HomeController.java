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

        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here

        searchBtn.setOnAction(actionEvent -> {
            // searchfield event if filtered by text
            if (!searchField.getText().equals("")) {
                observableMovies.clear();
                String input = searchField.getText().toLowerCase();
                for (Movie movie : allMovies) {
                    if (movie.getDescription().toLowerCase().contains(input) || movie.getTitle().toLowerCase().contains(input)) {
                        observableMovies.add(movie);
                    }
                }
            }

                // genreCombobox event if filtered by Genre
            if (!genreComboBox.getValue().equals("Filter by Genre")) {
                observableMovies.clear();
                for (Movie movie : allMovies) {
                    for (Genre genre : Genre.values()) {
                        if (genreComboBox.getValue().equals(genre) && movie.getGenres().contains(genre) && !observableMovies.contains(movie)) {
                            observableMovies.add(movie);
                        }
                    }
                }
            }
        });

        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                // TODO sort observableMovies ascending
                sortAscending();

                sortBtn.setText("Sort (desc)");
            } else {
                // TODO sort observableMovies descending
                sortDescending();
                sortBtn.setText("Sort (asc)");
            }
        });

        //Reset button -> resets all filters and shows the start-screen
        resetBtn.setOnAction(actionEvent -> {
            initializeHomeController();
            resetFilter();


        });

    }
    // Anfangsstate für Homecontroller bei Test
    public void initializeHomeController() {
        observableMovies.clear();
        observableMovies.addAll(allMovies);


    }

    // Filme aufsteigend sortieren
    public void sortAscending() {
        observableMovies.sort(Comparator.comparing(Movie::getTitle));
    }

    // Filme absteigend sortieren
    public void sortDescending() {
        observableMovies.sort(Comparator.comparing(Movie::getTitle).reversed());
    }


    public void resetFilter()
    {
        genreComboBox.setPromptText("Filter by Genre"); //not really working
        searchField.clear();
        searchField.setPromptText("Search FHMDb");
    }
}