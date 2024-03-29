package at.ac.fhcampuswien.fhmdb.models;

import java.util.List;
import static at.ac.fhcampuswien.fhmdb.MovieAPI.fetchMovies;

// @JsonIgnoreProperties(ignoreUnknown = true, value = {"id"})
public class Movie {
    public String id;
    private String title;
    private String description;
    private List<Genre> genres;
    private int releaseYear;
    public String imgUrl;
    public int lengthInMinutes;
    private List<String> directors;
    public List<String> writers;
    private List<String> mainCast;
    private double rating;

    public Movie() {

    }
    public Movie(String id, String title, String description, List<Genre> genres, int releaseYear,
                 String imgUrl, int lengthInMinutes, List<String> directors, List<String> writers,
                 List<String> mainCast, double rating) {
        this.id=id;
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
    public List<String> getMainCast(){
        return mainCast;
    }
    public double getRating() {
        return rating;
    }

    public List<String> getDirectors() {return directors;}

    public static List<Movie> initializeMovies(){
        List<Movie> movies = fetchMovies("","","","");
        return movies;

    }
}
