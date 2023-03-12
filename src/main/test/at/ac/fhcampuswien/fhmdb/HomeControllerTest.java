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
        expectedOrdering.add(new Movie("Blade Runner", "A detective hunts down rogue androids in a dystopian future.",
                Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER, Genre.ACTION, Genre.FANTASY)));
        expectedOrdering.add(new Movie("Bridesmaids", "A woman's life falls apart as her friend gets engaged.",
                Arrays.asList(Genre.COMEDY, Genre.ROMANCE)));
        expectedOrdering.add(new Movie("Fifty Shades of Grey", "Christian Grey " +
                "makes a deal with Anastasia Steel about his fetish and his sexual life.",
                Arrays.asList(Genre.ROMANCE, Genre.DRAMA)));
        expectedOrdering.add(new Movie("Finding Nemo", "A clownfish searches for his son who was taken by a diver.",
                Arrays.asList(Genre.FAMILY, Genre.FANTASY, Genre.ANIMATION)));
        expectedOrdering.add(new Movie("Indiana Jones and the Raiders of the Lost Ark", "An archaeologist sets out to recover a powerful artifact from Nazis.",
                Arrays.asList(Genre.ADVENTURE, Genre.ACTION, Genre.FANTASY)));
        expectedOrdering.add(new Movie("Interstellar", "Earth's last chance to find a habitable planet " +
                "before a lack of resources causes the human race to go extinct.", Arrays.asList(Genre.SCIENCE_FICTION, Genre.DRAMA)));
        expectedOrdering.add(new Movie("Les Misérables", "A story of love and redemption during the French Revolution.",
                Arrays.asList(Genre.MUSICAL)));
        expectedOrdering.add(new Movie("Planet Earth", "A documentary series about the natural world and its wonders.",
                Arrays.asList(Genre.DOCUMENTARY)));
        expectedOrdering.add(new Movie("Rocky", "A boxer gets a shot at the heavyweight championship.",
                Arrays.asList(Genre.SPORT, Genre.ROMANCE, Genre.ADVENTURE, Genre.DRAMA)));
        expectedOrdering.add(new Movie("Schindler's List", "A Nazi businessman saves the lives of over a thousand Jewish workers.",
                Arrays.asList(Genre.HISTORY, Genre.DRAMA)));
        expectedOrdering.add(new Movie("Star Wars 3: Revenge of the Sith", "The jedi concil have to confront a " +
                "difficult time for their existence", Arrays.asList(Genre.WAR, Genre.SCIENCE_FICTION, Genre.ACTION)));
        expectedOrdering.add(new Movie("The Girl with the Dragon Tattoo", "A journalist investigates the disappearance of a young woman.",
                Arrays.asList(Genre.MYSTERY, Genre.THRILLER)));
        expectedOrdering.add(new Movie("The Godfather", "A mafia family in New York faces challenges and betrayals.",
                Arrays.asList(Genre.CRIME, Genre.DRAMA)));
        expectedOrdering.add(new Movie("The Lord of the Rings: The Fellowship of the Ring", "A hobbit and his friends set out to destroy a powerful ring.",
                Arrays.asList(Genre.FANTASY, Genre.ADVENTURE, Genre.ACTION)));
        expectedOrdering.add(new Movie("The Notebook", "A classic love story about a young woman and man who build a relationship " +
                "despite societal differences.", Arrays.asList(Genre.ROMANCE, Genre.DRAMA)));
        expectedOrdering.add(new Movie("The Shawshank Redemption", "A banker is sentenced to life in prison for a crime he didn't commit.",
                Arrays.asList(Genre.DRAMA, Genre.MYSTERY)));
        expectedOrdering.add(new Movie("The Shining", "A writer becomes the caretaker of an isolated hotel and descends into madness.",
                Arrays.asList(Genre.HORROR, Genre.MYSTERY)));
        expectedOrdering.add(new Movie("The Silence of the Lambs", "An FBI agent seeks the help of a psychopathic killer to catch another killer.",
                Arrays.asList(Genre.THRILLER, Genre.HORROR, Genre.MYSTERY)));
        expectedOrdering.add(new Movie("The Social Network", "The story of the creation of Facebook.",
                Arrays.asList(Genre.BIOGRAPHY, Genre.DRAMA, Genre.HISTORY)));
        expectedOrdering.add(new Movie("To all the Boys I've loved before", "A movie about a girl whose love letters" +
                "from young age were sent out.", Arrays.asList(Genre.ROMANCE, Genre.DRAMA)));
        expectedOrdering.add(new Movie("Toy Story", "A cowboy toy is threatened by the arrival of a new spaceman toy.",
                Arrays.asList(Genre.ANIMATION, Genre.FAMILY, Genre.ADVENTURE, Genre.FANTASY)));



        homeController.sortAscending();

        // check that the movies are sorted correctly
        List<Movie> expected = homeController.observableMovies;
        List<Movie> actual = expectedOrdering;
        assertEquals(expected, actual);


    }


    @Test
    void movies_are_correctly_sorted_descending() {
        homeController.initializeHomeController();
        homeController.sortDescending();

        List<Movie> expectedOrdering = new ArrayList<>();

        expectedOrdering.add(new Movie("Toy Story", "A cowboy toy is threatened by the arrival of a new spaceman toy.",
                Arrays.asList(Genre.ANIMATION, Genre.FAMILY, Genre.ADVENTURE, Genre.FANTASY)));
        expectedOrdering.add(new Movie("To all the Boys I've loved before", "A movie about a girl whose love letters" +
                "from young age were sent out.", Arrays.asList(Genre.ROMANCE, Genre.DRAMA)));
        expectedOrdering.add(new Movie("The Social Network", "The story of the creation of Facebook.",
                Arrays.asList(Genre.BIOGRAPHY, Genre.DRAMA, Genre.HISTORY)));
        expectedOrdering.add(new Movie("The Silence of the Lambs", "An FBI agent seeks the help of a psychopathic killer to catch another killer.",
                Arrays.asList(Genre.THRILLER, Genre.HORROR, Genre.MYSTERY)));
        expectedOrdering.add(new Movie("The Shining", "A writer becomes the caretaker of an isolated hotel and descends into madness.",
                Arrays.asList(Genre.HORROR, Genre.MYSTERY)));
        expectedOrdering.add(new Movie("The Shawshank Redemption", "A banker is sentenced to life in prison for a crime he didn't commit.",
                Arrays.asList(Genre.DRAMA, Genre.MYSTERY)));
        expectedOrdering.add(new Movie("The Notebook", "A classic love story about a young woman and man who build a relationship " +
                "despite societal differences.", Arrays.asList(Genre.ROMANCE, Genre.DRAMA)));
        expectedOrdering.add(new Movie("The Lord of the Rings: The Fellowship of the Ring", "A hobbit and his friends set out to destroy a powerful ring.",
                Arrays.asList(Genre.FANTASY, Genre.ADVENTURE, Genre.ACTION)));
        expectedOrdering.add(new Movie("The Godfather", "A mafia family in New York faces challenges and betrayals.",
                Arrays.asList(Genre.CRIME, Genre.DRAMA)));
        expectedOrdering.add(new Movie("The Girl with the Dragon Tattoo", "A journalist investigates the disappearance of a young woman.",
                Arrays.asList(Genre.MYSTERY, Genre.THRILLER)));
        expectedOrdering.add(new Movie("Star Wars 3: Revenge of the Sith", "The jedi concil have to confront a " +
                "difficult time for their existence", Arrays.asList(Genre.WAR, Genre.SCIENCE_FICTION, Genre.ACTION)));
        expectedOrdering.add(new Movie("Schindler's List", "A Nazi businessman saves the lives of over a thousand Jewish workers.",
                Arrays.asList(Genre.HISTORY, Genre.DRAMA)));
        expectedOrdering.add(new Movie("Rocky", "A boxer gets a shot at the heavyweight championship.",
                Arrays.asList(Genre.SPORT, Genre.ROMANCE, Genre.ADVENTURE, Genre.DRAMA)));
        expectedOrdering.add(new Movie("Planet Earth", "A documentary series about the natural world and its wonders.",
                Arrays.asList(Genre.DOCUMENTARY)));
        expectedOrdering.add(new Movie("Les Misérables", "A story of love and redemption during the French Revolution.",
                Arrays.asList(Genre.MUSICAL)));
        expectedOrdering.add(new Movie("Interstellar", "Earth's last chance to find a habitable planet " +
                "before a lack of resources causes the human race to go extinct.", Arrays.asList(Genre.SCIENCE_FICTION, Genre.DRAMA)));
        expectedOrdering.add(new Movie("Indiana Jones and the Raiders of the Lost Ark", "An archaeologist sets out to recover a powerful artifact from Nazis.",
                Arrays.asList(Genre.ADVENTURE, Genre.ACTION, Genre.FANTASY)));
        expectedOrdering.add(new Movie("Finding Nemo", "A clownfish searches for his son who was taken by a diver.",
                Arrays.asList(Genre.FAMILY, Genre.FANTASY, Genre.ANIMATION)));
        expectedOrdering.add(new Movie("Fifty Shades of Grey", "Christian Grey " +
                "makes a deal with Anastasia Steel about his fetish and his sexual life.",
                Arrays.asList(Genre.ROMANCE, Genre.DRAMA)));
        expectedOrdering.add(new Movie("Bridesmaids", "A woman's life falls apart as her friend gets engaged.",
                Arrays.asList(Genre.COMEDY, Genre.ROMANCE)));
        expectedOrdering.add(new Movie("Blade Runner", "A detective hunts down rogue androids in a dystopian future.",
                Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER, Genre.ACTION, Genre.FANTASY)));


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
        homeController.searchField.setText("Title 2");

        // makes sure the search field has an input
        assertNotNull(homeController.searchField);

        // simulates button click
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
