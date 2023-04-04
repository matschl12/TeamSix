package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieAPI {

    public MovieAPI() {

        List<String> movies = new ArrayList<>();

        String url = "https://prog2.fh-campuswien.ac.at/movies";


       try {
           URL api = new URL(url);
           HttpURLConnection connection = (HttpURLConnection) api.openConnection();
           connection.setRequestMethod("GET");
           connection.connect();

           Scanner scanner = new Scanner(api.openStream());

           while (scanner.hasNext()) {
               movies.add(scanner.nextLine());
           }
           scanner.close();
       } catch (Exception e) {
           e.printStackTrace();
       }

        System.out.println(movies);
    }
}
