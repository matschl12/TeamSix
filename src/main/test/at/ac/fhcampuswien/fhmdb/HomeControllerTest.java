package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class HomeControllerTest {
    private static HomeController homeController;

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
                Arrays.asList(Genre.SCIENCE_FICTION, Genre.DRAMA));

        assertEquals("Interstellar", movie.getTitle());
        assertEquals("Earth's last chance to find a habitable planet " +
                "before a lack of resources causes the human race to go extinct.", movie.getDescription());
        assertEquals(Arrays.asList(Genre.SCIENCE_FICTION, Genre.DRAMA), movie.getGenres());
    }


    @Test
    void movies_are_correctly_sorted_ascending() {
        homeController.initializeHomeController();
        homeController.sortAscending();

        List<Movie> expectedOrdering = new ArrayList<>();
        expectedOrdering.add(new Movie("Fifty Shades of Grey", "Christian Grey " +
                "makes a deal with Anastasia Steel about his fetish and his sexual life.",
                Arrays.asList(Genre.ROMANCE, Genre.DRAMA)));
        expectedOrdering.add(new Movie("Interstellar", "Earth's last chance to find a habitable planet " +
                "before a lack of resources causes the human race to go extinct.", Arrays.asList(Genre.SCIENCE_FICTION, Genre.DRAMA)));
        expectedOrdering.add(new Movie("Star Wars 3: Revenge of the Sith", "The jedi concil have to confront a " +
                "difficult time for their existence", Arrays.asList(Genre.WAR, Genre.SCIENCE_FICTION, Genre.ACTION)));
        expectedOrdering.add(new Movie("To all the Boys I've loved before", "A movie about a girl whose love letters" +
                "from young age were sent out.", Arrays.asList(Genre.ROMANCE, Genre.DRAMA)));

        assertEquals(expectedOrdering, homeController.observableMovies);

    }


    @Test
    void movies_are_correctly_sorted_descending() {
        homeController.initializeHomeController();
        homeController.sortDescending();

        List<Movie> expectedOrdering = new ArrayList<>();
        expectedOrdering.add(new Movie("To all the Boys I've loved before", "A movie about a girl whose love letters" +
                "from young age were sent out.", Arrays.asList(Genre.ROMANCE, Genre.DRAMA)));
        expectedOrdering.add(new Movie("Star Wars 3: Revenge of the Sith", "The jedi concil have to confront a " +
                "difficult time for their existence", Arrays.asList(Genre.WAR, Genre.SCIENCE_FICTION, Genre.ACTION)));
        expectedOrdering.add(new Movie("Interstellar", "Earth's last chance to find a habitable planet " +
                "before a lack of resources causes the human race to go extinct.", Arrays.asList(Genre.SCIENCE_FICTION, Genre.DRAMA)));
        expectedOrdering.add(new Movie("Fifty Shades of Grey", "Christian Grey " +
                "makes a deal with Anastasia Steel about his fetish and his sexual life.",
                Arrays.asList(Genre.ROMANCE, Genre.DRAMA)));

        assertEquals(expectedOrdering, homeController.observableMovies);
    }
    @Test
    public void test_filter_by_string_in_search_field() {
        // create some test data
        Movie movie1 = new Movie("Title 1", "Description 1", List.of(Genre.ACTION));
        Movie movie2 = new Movie("Title 2", "Description 2", List.of(Genre.COMEDY));
        Movie movie3 = new Movie("Title 3", "Description 3", List.of(Genre.DRAMA));
        List<Movie> allMovies = Arrays.asList(movie1, movie2, movie3);

        // create the controller and set the data
        homeController.allMovies.addAll(allMovies);

        // simulate user input
        homeController.searchField.setText("title 2");
        // <- diese Line funktioniert nicht
        homeController.searchBtn.fire();

        // check that the search results are correct
        List<Movie> expected = Arrays.asList(movie2);
        List<Movie> actual = homeController.observableMovies;
        assertEquals(expected, actual);
    }
    @Test
    public void test_filter_by_genre_in_genre_combo_box() {
        //  initialize the genreComboBox
        homeController.genreComboBox.setPromptText("Filter by Genre");
        homeController.genreComboBox.getItems().addAll(Genre.values());

        // check that the genreComboBox is initialized correctly
        List<Genre> expected = Arrays.asList(Genre.values());
        List<Genre> actual = homeController.genreComboBox.getItems();
        assertEquals(expected, actual);
    }

}
