package at.ac.fhcampuswien.fhmdb.pattern.StatePattern;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.beans.Observable;
import javafx.collections.ObservableList;

public class MovieSortStates {
    private State state;

    public MovieSortStates() {
        this.state = new SortNONE();
    }
    public void setState(State state) {
        this.state = state;

    }
    public void sortMovies(ObservableList<Movie> movies) {
        state.sort(movies);
    }

}

