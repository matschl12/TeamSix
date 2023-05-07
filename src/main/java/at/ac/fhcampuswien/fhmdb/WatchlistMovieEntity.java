package at.ac.fhcampuswien.fhmdb;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.List;

public class WatchlistMovieEntity {
    long id;
    String apiId;
    String title;
    String description;
    String genres;
    int releaseYear;
    String imgUrl;
    int lenghtInMinutes;
    double rating;

    public WatchlistMovieEntity() {

    }

    public WatchlistMovieEntity(String apiId, String title, String description, List<Genre> genres, int releaseYear, String imgUrl, int lenghtInMinutes, double rating) {
        this.apiId = apiId;
        this.title = title;
        this.description = description;
        this.genres = genresToString(genres);
        this.releaseYear = releaseYear;
        this.imgUrl = imgUrl;
        this.lenghtInMinutes = lenghtInMinutes;
        this.rating = rating;
    }

    public String genresToString(List<Genre> genres){
        StringBuilder genreString = new StringBuilder();
        for (Genre genre: genres) {
            genreString.append(genre);
            genreString.append(", ");
        }
        return genreString.toString();
    }
    }





