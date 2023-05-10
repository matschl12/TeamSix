package at.ac.fhcampuswien.fhmdb.pattern.StatePattern;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.collections.ObservableList;

import java.util.Comparator;


public class SortASCENDING implements State {
    @Override
    public void sort(ObservableList<Movie> movies) {
        movies.sort(Comparator.comparing(Movie::getTitle));

    }
}
