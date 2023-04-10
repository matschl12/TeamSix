package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import static at.ac.fhcampuswien.fhmdb.HomeController.*;
import static at.ac.fhcampuswien.fhmdb.MovieAPI.fetchMoviesFilter;
import static org.junit.jupiter.api.Assertions.*;


class HomeControllerTest {
    private static HomeController homeController;


    @Test

    void show_unfiltered_movies_initially() {
        List<Movie> initialMovies = Movie.initializeMovies();
        assertNotEquals(null, initialMovies);
    }


    @Test
    void filterAllMoviesBySearchboxSearchingInTitleAndDescription(){};
    //void


    @BeforeAll
    static void init() {
        homeController = new HomeController();
    }

    @Test
    void observable_movies_and_allMovies_are_equal() {
        homeController.initializeHomeController();
        assertEquals(homeController.allMovies, homeController.observableMovies);
    }
    @Test
    public void test_get_methods_of_title_description_and_genres() {
        Movie movie = new Movie("Interstellar", "Earth's last chance to find a habitable planet " +
                "before a lack of resources causes the human race to go extinct.",
                Arrays.asList(Genre.SCIENCE_FICTION, Genre.DRAMA), 0, null, 0, null, null, null, 0);

        assertEquals("Interstellar", movie.getTitle());
        assertEquals("Earth's last chance to find a habitable planet " +
                "before a lack of resources causes the human race to go extinct.", movie.getDescription());
        assertEquals(Arrays.asList(Genre.SCIENCE_FICTION, Genre.DRAMA), movie.getGenres());
    }


    @Test
    void movies_are_correctly_sorted_ascending() {

        Movie movie1 = new Movie("A Movie about Tigers", "Description 1", List.of(Genre.ACTION), 0, null, 0, null, null, null, 0);
        Movie movie2 = new Movie("C Language explained", "Description 2", List.of(Genre.COMEDY), 0, null, 0, null, null, null, 0);
        Movie movie3 = new Movie("B Letter", "Description 3", List.of(Genre.DRAMA), 0, null, 0, null, null, null, 0);

        // expected list
        ObservableList<Movie> expected = FXCollections.observableArrayList();
        expected.add(movie1);
        expected.add(movie3);
        expected.add(movie2);

        // actual list
        ObservableList<Movie> actual = FXCollections.observableArrayList();
        actual.add(movie1);
        actual.add(movie2);
        actual.add(movie3);

        homeController.sortAscending(actual);

        assertEquals(expected, actual);
    }


    @Test
    void movies_are_correctly_sorted_descending() {

        Movie movie1 = new Movie("A Movie about Tigers", "Description 1", List.of(Genre.ACTION), 0, null, 0, null, null, null, 0);
        Movie movie2 = new Movie("C Language explained", "Description 2", List.of(Genre.COMEDY), 0, null, 0, null, null, null, 0);
        Movie movie3 = new Movie("B Letter", "Description 3", List.of(Genre.DRAMA), 0, null, 0, null, null, null, 0);

        // expected list
        ObservableList<Movie> expected = FXCollections.observableArrayList();
        expected.add(movie2);
        expected.add(movie3);
        expected.add(movie1);


        // actual list
        ObservableList<Movie> actual = FXCollections.observableArrayList();
        actual.add(movie1);
        actual.add(movie2);
        actual.add(movie3);

        homeController.sortDescending(actual);

        assertEquals(expected, actual);
    }
   /* test is not necessary anymore
   @Test
    public void test_filter_by_string_in_search_field() {

        // create some test data
        Movie movie1 = new Movie("Title", "Description 1", List.of(Genre.ACTION), 0, null, 0, null, null, null, 0);
        Movie movie2 = new Movie("Movie", "Description 2", List.of(Genre.COMEDY), 0, null, 0, null, null, null, 0);
        Movie movie3 = new Movie("Baum", "Description 3", List.of(Genre.DRAMA), 0, null, 0, null, null, null, 0);
        List<Movie> allMovies = Arrays.asList(movie1, movie2, movie3);

        // expected list
        List<Movie> expected = new ArrayList<>();
        expected.add(movie2);

        // actual list
        homeController.searchFieldText(allMovies, "title 2");

        assertEquals(expected,actual);
    } */

    /* @Test
    public void test_filter_by_genre_in_genre_combo_box() {

        // test is not working because genreComboBox is null

        //  initialize the genreComboBox
        homeController.genreComboBox.setPromptText("Filter by Genre");
        homeController.genreComboBox.getItems().addAll(Genre.values());

        // check that the genreComboBox is initialized correctly
        List<Genre> expected = Arrays.asList(Genre.values());
        List<Genre> actual = homeController.genreComboBox.getItems();
        assertEquals(expected, actual);
    } */

    @Test
    public void test_get_most_popular_actor_method() {
        List<Movie> allMovies = homeController.allMovies;
        String expected = "Tom Hanks";
        String actual = getMostPopularActor(allMovies);
        assertEquals(expected,actual);

    }

    @Test
    public void test_get_longest_movie_title_method() {
        List<Movie> allMovies = homeController.allMovies;
        int expected = 46;
        int actual = getLongestMovieTitle(allMovies);
        assertEquals(expected,actual);
    }

    @Test
    public void test_count_movies_from_method() {
        List<Movie> allMovies = homeController.allMovies;
        long expected = 2;
        long actual = countMoviesFrom(allMovies, "Peter Jackson");
        assertEquals(expected,actual);
    }

    @Test
    public void test_get_movies_between_years() {
        List<Movie> allMovies = new ArrayList<>();
        Movie movie1 = new Movie("Test1",null,null,1920,null,0,null,null,null,0);
        Movie movie2 = new Movie("Test2",null,null,1921,null,0,null,null,null,0);
        Movie movie3 = new Movie("Test3",null,null,2000,null,0,null,null,null,0);
        Movie movie4 = new Movie("Test4",null,null,1949,null,0,null,null,null,0);
        Movie movie5 = new Movie("Test5",null,null,1888,null,0,null,null,null,0);
        allMovies.add(movie1);
        allMovies.add(movie2);
        allMovies.add(movie3);
        allMovies.add(movie4);
        allMovies.add(movie5);

        List<Movie> expected = new ArrayList<>();
        expected.add(movie2);
        expected.add(movie4);

        List<Movie> actual = getMoviesBetweenYears(allMovies,1921,1960);

        assertEquals(expected,actual);
    }
}
