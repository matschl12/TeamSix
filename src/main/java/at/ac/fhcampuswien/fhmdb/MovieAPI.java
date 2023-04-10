package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class MovieAPI {
    // mainURL of api
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


           /* ArrayList<String> allGenresInArrayList=new ArrayList<>();
           allGenresInArrayList= (ArrayList<String>) movies.stream().map(allgenres -> allgenres.getGenres().toArray()); */

           //System.out.println(allGenresInArrayList.toString()); -> these lines lead to error in program



       } catch (Exception e) {
           e.printStackTrace();
       }

       return movies;

    }
    public static List<Movie> fetchMoviesFilter(String query, String genre, int releaseYear,
                                                double rating) {

        String url;
        List<Movie> movies = new ArrayList<>();

        if (genre == "none" && releaseYear == 0 && rating == 0) {
            url = String.format(mainURL +"/movies?query=" +query);
        } else if (genre != "none" && releaseYear == 0 && rating == 0) {
            url = String.format(mainURL +"/movies?query=" +query+"&genre="+genre);
        } else if (genre !="none" && releaseYear != 0 && rating == 0) {
            url = String.format(mainURL +"/movies?query=" +query+"&genre=" +genre+"&releaseYear=" +releaseYear);
        } else if(genre != "none" && releaseYear == 0 && rating != 0) {
            url = String.format(mainURL +"/movies?query=" +query+"&genre=" +genre+ "&ratingFrom=" + rating);
        } else if(genre == "none" && releaseYear != 0 && rating == 0) {
            url = String.format(mainURL +"/movies?query=" +query +"&releaseYear=" + releaseYear);
        } else if(genre == "none" && releaseYear == 0 && rating !=0) {
            url = String.format(mainURL +"/movies?query=" +query+ "&ratingFrom=" + rating);
        } else if(genre == "none" && releaseYear !=0 && rating !=0) {
            url = String.format(mainURL +"/movies?query=" +query+"&releaseYear=" +releaseYear+ "&ratingFrom=" + rating);
        } else {
            url = String.format(mainURL +"/movies?query=" +query+"&genre=" +genre+"&releaseYear=" +releaseYear+ "&ratingFrom=" + rating);
        }

        /* if (releaseYear == 0 && rating != 0) {
             url = String.format(mainURL +"/movies?query=" +query+"&genre=" +genre+ "&ratingFrom=" + rating);
        } else if (rating == 0 && releaseYear !=0) {
             url = String.format(mainURL +"/movies?query=" +query+"&genre=" +genre+"&releaseYear=" +releaseYear);
        }
        else if (rating == 0 && releaseYear == 0 && genre != "none") {
             url = String.format(mainURL +"/movies?query=" +query+"&genre=" +genre);
        } else if (rating !=0 && releaseYear !=0){
            url = String.format(mainURL +"/movies?query=" +query+"&genre=" +genre+"&releaseYear=" +releaseYear+ "&ratingFrom=" + rating);
        } else {url = String.format(mainURL +"/movies?query=" +query);
        } */

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

        return movies;

    }

    public static String newQuerySortAscDesc(String myQueryURL, String AscOrDesc) {


        String url = myQueryURL+"sort=id:"+AscOrDesc;
        return url;
    }
    public static String filteredQueryString(String query, String genre, int releaseYear,
                                             double rating) {

        String url;

        if (releaseYear == 0 && rating != 0) {
            url = String.format(mainURL +"/movies?query=" +query+"&genre=" +genre+ "&ratingFrom=" + rating);
        }
        else if (rating == 0 && releaseYear !=0) {
            url = String.format(mainURL +"/movies?query=" +query+"&genre=" +genre+"&releaseYear=" +releaseYear);
        }
        else if (rating == 0 && releaseYear == 0) {
            url = String.format(mainURL +"/movies?query=" +query+"&genre=" +genre);
        } else if (rating !=0 && releaseYear !=0){
            url = String.format(mainURL +"/movies?query=" +query+"&genre=" +genre+"&releaseYear=" +releaseYear+ "&ratingFrom=" + rating);
        } else {url = String.format(mainURL +"/movies");
        }
        return url;

    }




}

