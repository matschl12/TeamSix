package at.ac.fhcampuswien.fhmdb.pattern.StatePattern;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.beans.Observable;
import javafx.collections.ObservableList;

public interface State {
    void sort(ObservableList<Movie> movies);
}
