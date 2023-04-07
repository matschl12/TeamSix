package at.ac.fhcampuswien.fhmdb.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

import static at.ac.fhcampuswien.fhmdb.MovieAPI.fetchMovies;
import static at.ac.fhcampuswien.fhmdb.MovieAPI.fetchMoviesFilter;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"id"})
public class Movie {
    // private String id;
    private String title;
    private String description;
    private List<Genre> genres;
    private int releaseYear;
    private String imgUrl;
    private int lengthInMinutes;
    private String[] directors;
    private String[] writers;
    private String[] mainCast;
    private double rating;

    public Movie() {

    }
    public Movie(String title, String description, List<Genre> genres, int releaseYear, String imgUrl, int lengthInMinutes, String[] directors, String[] writers, String[] mainCast, double rating) {
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.imgUrl = imgUrl;
        this.lengthInMinutes = lengthInMinutes;
        this.directors = directors;
        this.writers = writers;
        this.mainCast = mainCast;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Genre> getGenres() {return genres;}
    public int getReleaseYear() {
        return releaseYear;
    }
    public String[] getMainCast(){
        return mainCast;
    }
    public double getRating() {
        return rating;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = fetchMovies();

        /* movies.add(new Movie("Fifty Shades of Grey", "Christian Grey " +
                "makes a deal with Anastasia Steel about his fetish and his sexual life.",
                Arrays.asList(Genre.ROMANCE, Genre.DRAMA)));
        movies.add(new Movie("To all the Boys I've loved before", "A movie about a girl whose love letters" +
                "from young age were sent out.", Arrays.asList(Genre.ROMANCE, Genre.DRAMA)));
        movies.add(new Movie("Star Wars 3: Revenge of the Sith", "The jedi concil have to confront a " +
                "difficult time for their existence", Arrays.asList(Genre.WAR, Genre.SCIENCE_FICTION, Genre.ACTION)));
        movies.add(new Movie("Interstellar", "Earth's last chance to find a habitable planet " +
                "before a lack of resources causes the human race to go extinct.", Arrays.asList(Genre.SCIENCE_FICTION, Genre.DRAMA)));
        movies.add(new Movie("Indiana Jones and the Raiders of the Lost Ark", "An archaeologist sets out to recover a powerful artifact from Nazis.",
                Arrays.asList(Genre.ADVENTURE, Genre.ACTION, Genre.FANTASY)));
        movies.add(new Movie("Toy Story", "A cowboy toy is threatened by the arrival of a new spaceman toy.",
                Arrays.asList(Genre.ANIMATION, Genre.FAMILY, Genre.ADVENTURE, Genre.FANTASY)));
        movies.add(new Movie("The Social Network", "The story of the creation of Facebook.",
                Arrays.asList(Genre.BIOGRAPHY, Genre.DRAMA, Genre.HISTORY)));
        movies.add(new Movie("Bridesmaids", "A woman's life falls apart as her friend gets engaged.",
                Arrays.asList(Genre.COMEDY, Genre.ROMANCE)));
        movies.add(new Movie("The Godfather", "A mafia family in New York faces challenges and betrayals.",
                Arrays.asList(Genre.CRIME, Genre.DRAMA)));
        movies.add(new Movie("The Shawshank Redemption", "A banker is sentenced to life in prison for a crime he didn't commit.",
                Arrays.asList(Genre.DRAMA, Genre.MYSTERY)));
        movies.add(new Movie("Planet Earth", "A documentary series about the natural world and its wonders.",
                Arrays.asList(Genre.DOCUMENTARY)));
        movies.add(new Movie("Finding Nemo", "A clownfish searches for his son who was taken by a diver.",
                Arrays.asList(Genre.FAMILY, Genre.FANTASY, Genre.ANIMATION)));
        movies.add(new Movie("The Lord of the Rings: The Fellowship of the Ring", "A hobbit and his friends set out to destroy a powerful ring.",
                Arrays.asList(Genre.FANTASY, Genre.ADVENTURE, Genre.ACTION)));
        movies.add(new Movie("Schindler's List", "A Nazi businessman saves the lives of over a thousand Jewish workers.",
                Arrays.asList(Genre.HISTORY, Genre.DRAMA)));
        movies.add(new Movie("The Shining", "A writer becomes the caretaker of an isolated hotel and descends into madness.",
                Arrays.asList(Genre.HORROR, Genre.MYSTERY)));
        movies.add(new Movie("Les Misérables", "A story of love and redemption during the French Revolution.",
                Arrays.asList(Genre.MUSICAL)));
        movies.add(new Movie("The Girl with the Dragon Tattoo", "A journalist investigates the disappearance of a young woman.",
                Arrays.asList(Genre.MYSTERY, Genre.THRILLER)));
        movies.add(new Movie("The Notebook", "A classic love story about a young woman and man who build a relationship " +
                "despite societal differences.", Arrays.asList(Genre.ROMANCE, Genre.DRAMA)));
        movies.add(new Movie("Blade Runner", "A detective hunts down rogue androids in a dystopian future.",
                Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER, Genre.ACTION, Genre.FANTASY)));
        movies.add(new Movie("Rocky", "A boxer gets a shot at the heavyweight championship.",
                Arrays.asList(Genre.SPORT, Genre.ROMANCE, Genre.ADVENTURE, Genre.DRAMA)));
        movies.add(new Movie("The Silence of the Lambs", "An FBI agent seeks the help of a psychopathic killer to catch another killer.",
                Arrays.asList(Genre.THRILLER, Genre.HORROR, Genre.MYSTERY))); */

        return movies;

    }
}
