package at.ac.fhcampuswien.fhmdb;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.List;

public class WatchlistMovieEntity {
    long id;
    String aplid;
    String title;
    String description;
    String genres;
    int releaseYear;
    String imgUrl;
    int lenghtInMinutes;
    double rating;

    //public String genresToString(List<Genre> genres){
    //if (genres == null || genres.isEmpty()) {
           //return "";
             //} else {
            //StringBuilder sb = new StringBuilder();
            //for (Genre genre : genres) {
                //sb.append(Movie.getGenres()).append(",");
            //}
            //return sb.substring(0, sb.length() - 1);
        //}
    }





