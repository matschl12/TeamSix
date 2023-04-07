package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

public class MovieAPI {
    static String mainURL = "https://prog2.fh-campuswien.ac.at";

    public static List<Movie> fetchMovies() {

        List<Movie> movies = new ArrayList<>();

        String url = mainURL + "/movies";


       try {
           URL api = new URL(url);
           HttpURLConnection connection = (HttpURLConnection) api.openConnection();
           connection.setRequestMethod("GET");
           connection.connect();

           InputStream inputStream = connection.getInputStream();
           Scanner scanner = new Scanner(inputStream);
           String response = scanner.useDelimiter("\\A").next();
           scanner.close();

           ObjectMapper objectMapper = new ObjectMapper();
           movies = objectMapper.readValue(response, new TypeReference<List<Movie>>(){});


       } catch (Exception e) {
           e.printStackTrace();
       }

        System.out.println(movies);

       return movies;

    }
    public static List<Movie> fetchMoviesFilter(String query, String genre, int releaseYear, double rating) {

        String url;
        List<Movie> movies = new ArrayList<>();
        if (releaseYear == 0) {
             url = String.format(mainURL +"/movies?query=" +query+"&genre=" +genre+ "&ratingFrom=" + rating);
        }
        if (rating == 0) {
             url = String.format(mainURL +"/movies?query=" +query+"&genre=" +genre+"&releaseYear=" +releaseYear);
        }
        if (rating == 0 && releaseYear == 0) {
             url = String.format(mainURL +"/movies?query=" +query+"&genre=" +genre);
        } else {
             url = String.format(mainURL +"/movies?query=" +query+"&genre=" +genre+"&releaseYear=" +releaseYear+ "&ratingFrom=" + rating);
        }

        try {
            URL api = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) api.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            String response = scanner.useDelimiter("\\A").next();
            scanner.close();

            ObjectMapper objectMapper = new ObjectMapper();
            movies = objectMapper.readValue(response, new TypeReference<List<Movie>>(){});


        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(movies);

        return movies;

    }
}
