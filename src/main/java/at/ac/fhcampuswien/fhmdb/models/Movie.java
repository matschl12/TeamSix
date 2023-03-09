package at.ac.fhcampuswien.fhmdb.models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    private List<Genre> genres;

    public Movie(String title, String description, List<Genre> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Genre> getGenres() {return genres;}


    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Fifty Shades of Grey", "Christian Grey " +
                "makes a deal with Anastasia Steel about his fetish and his sexual life.",
                Arrays.asList(Genre.ROMANCE, Genre.DRAMA)));
        movies.add(new Movie("To all the Boys I've loved before", "A movie about a girl whose love letters" +
                "from young age were sent out.", Arrays.asList(Genre.ROMANCE, Genre.DRAMA)));
        movies.add(new Movie("Star Wars 3: Revenge of the Sith", "The jedi concil have to confront a " +
                "difficult time for their existence", Arrays.asList(Genre.WAR, Genre.SCIENCE_FICTION, Genre.ACTION)));


        return movies;
    }
}
