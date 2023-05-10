package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.Exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.HomeController;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

import java.util.Objects;

public class MovieCell extends ListCell<Movie> {
    private final Label title = new Label();
    private final Label detail = new Label();
    private final Label genres = new Label(); //to show genres
    private final Label rating = new Label(); //to show the rating

    private Button addToWLBtn = new Button();

    private Button detailsBtn = new Button();

    private final VBox layout = new VBox(title, detail, genres, rating, addToWLBtn, detailsBtn);

    // private ClickEventHandler<Movie> clickHandler;

    Font genreFont = Font.font("Verdana", FontPosture.ITALIC, 10); //to make genre font italic

    @Override
    protected void updateItem(Movie movie, boolean empty) {
        super.updateItem(movie, empty);

        if (empty || movie == null) {
            setText(null);
            setGraphic(null); //had to add this line because there was a bug with showing duplicate movies
        } else {
            this.getStyleClass().add("movie-cell");
            title.setText(movie.getTitle() + " ("+ movie.getReleaseYear() + ")"); //changed to show the release year after the title
            detail.setText(
                    movie.getDescription() != null
                            ? movie.getDescription()
                            : "No description available");
            genres.setText(movie.getGenres().toString().replace("[", "").replace("]", "")); //to get information to show genres
            rating.setText("Rating: " + String.valueOf(movie.getRating())); //to get information to show the rating
            // change text of addWatchList Button
            for (Movie movie2 : HomeController.allMovies) {
                addToWLBtn.setText("Add to Watchlist");
                if (HomeController.watchList.contains(movie) && Objects.equals(movie.id, movie2.id)) {
                    addToWLBtn.setText("Remove from Watchlist");
                    break;
                } else {
                    addToWLBtn.setText("Add to Watchlist");
                }
                }


            detailsBtn.setText("Show Details"); //added
            detailsBtn.setDisable(true); //temporary disabled


            // color scheme
            title.getStyleClass().add("text-yellow");
            detail.getStyleClass().add("text-white");
            genres.getStyleClass().add("text-white"); //added
            genres.setFont(genreFont); //added
            rating.getStyleClass().add("text-white"); //added
            rating.setFont(genreFont); //added
            addToWLBtn.getStyleClass().add("background-yellow"); //added
            detailsBtn.getStyleClass().add("background-yellow"); //added
            layout.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null)));

            // layout
            title.fontProperty().set(title.getFont().font(20));
            detail.setMaxWidth(this.getScene().getWidth() - 30);
            detail.setWrapText(true);
            genres.setMaxWidth(this.getScene().getWidth() - 30); //added
            genres.setWrapText(true); //added
            rating.setMaxWidth(this.getScene().getWidth() - 30); //added
            rating.setWrapText(true); //added
            layout.setPadding(new Insets(10));
            layout.spacingProperty().set(10);
            layout.alignmentProperty().set(javafx.geometry.Pos.CENTER_LEFT);
            setGraphic(layout);



        }
    }



    // Exercise 3 Business Layer
    public MovieCell() {


    }
    // MovieCell with addToWatchListClicked Button
    public MovieCell(ClickEventHandler<Movie> addToWatchlistClicked) {
        super();
        // this.clickHandler = addToWatchlistClicked;
        addToWLBtn.setOnMouseClicked(mouseEvent -> {
            try {
                addToWatchlistClicked.onClick(getItem());
            } catch (DatabaseException e) {
                System.out.println();
            }
            if (!HomeController.watchList.isEmpty()) {
                for (Movie movie: HomeController.watchList) {
                    if (Objects.equals(movie.id, getItem().id)) {
                        addToWLBtn.setText("Remove from Watchlist");
                        break;
                    } else {
                        addToWLBtn.setText("Add to Watchlist");
                    }
                }
            } else {
                addToWLBtn.setText("Add to Watchlist");
            }
        });
    }
}

