package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.HomeController;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import at.ac.fhcampuswien.fhmdb.HomeController;

import java.util.Objects;

public class MovieCell extends ListCell<Movie> {
    private final Label title = new Label();
    private final Label detail = new Label();
    private final Label genres = new Label(); //to show genres
    private final Label rating = new Label(); //to show the rating

    private final Button addToWatchlist = new Button();

    private final VBox layout = new VBox(title, detail, genres, rating, addToWatchlist);

    HomeController controller;
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
            genres.setText(movie.getGenres().toString().replace("[", "").replace("]", "")); //to get informations to show genres
            rating.setText("Rating: " + String.valueOf(movie.getRating())); //to get information to show the rating
            addToWatchlist.setText("Add to Watchlist");

            // color scheme
            title.getStyleClass().add("text-yellow");
            detail.getStyleClass().add("text-white");
            genres.getStyleClass().add("text-white"); //added
            genres.setFont(genreFont); //added
            rating.getStyleClass().add("text-white"); //added
            rating.setFont(genreFont); //added
            addToWatchlist.getStyleClass().add("background-yellow"); //added
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


            addToWatchlist.setOnAction(new EventHandler<ActionEvent>() { //clickOnEvent for the addToWatchlist button which uses method from the controller class
                @Override
                public void handle(ActionEvent actionEvent) {
                    controller.addToWishlistBtnClicked();
                }
            });
        }
    }

    // Exercise 3 Business Layer
    public MovieCell() {

    }



    public MovieCell(ClickEventHandler addToWatchlistClicked) {
        super();
        addToWatchlist.setOnMouseClicked(mouseEvent -> {
            addToWatchlistClicked.onClick(getItem());
            if (!HomeController.watchList.isEmpty()) {
                for (Movie movie: HomeController.watchList) {
                    if (movie.id.equals(getItem().id)) {
                        addToWatchlist.setText("Delete from Watchlist");
                    }
                }
            }
        });
    }
}

