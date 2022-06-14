package guibedin.alura7days.day3.parse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Day3 {

    // Split attributes from a single movie string
    public static String[] splitAttributes(String movies) {
        movies = movies.replace("{", "");
        movies = movies.replace("}", "");

        return movies.split("\",\"");
    }

    public static List<String> parseAttribute(String[] movies, String attribute) {
        List<String> attributes = new ArrayList<>();

        for(String movie : movies) {
            String[] attributesArray = splitAttributes(movie);
            for(String atr : attributesArray) {
                if(atr.contains(attribute)) {
                    attributes.add(atr.split(":\"")[1].replace("\"",""));
                }
            }
        }

        return attributes;
    }

    public static List<Movie> parseMovies(List<String> titles, List<String> urlImages,
                                          List<String> imDbRatings, List<String> years) {
        List<Movie> movies = new ArrayList<>();

        for(int i = 0; i < titles.size(); i++) {
            movies.add(new Movie(titles.get(i), urlImages.get(i),
                    Double.parseDouble(imDbRatings.get(i)), Integer.parseInt(years.get(i))));
        }

        return movies;
    }
    public static void execute() throws Exception {

        // Make a GET HTTP Request to IMDB API and print the response in stdout
        String apiKey = System.getenv("IMDB_API_KEY");
        URI uri = URI.create("https://imdb-api.com/en/API/Top250Movies/" + apiKey);

        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response;

        // Parse JSON response into multiple lists
        List<Movie> movies;

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        int start = body.indexOf("[") + 1;
        int end = body.indexOf("]");

        String moviesJson = body.substring(start, end);
        String[] moviesArray = moviesJson.split("},");

        List<String> titles  = parseAttribute(moviesArray, "title");
        List<String> images = parseAttribute(moviesArray, "image");
        List<String> imDbRatings = parseAttribute(moviesArray, "imDbRating");
        List<String> years = parseAttribute(moviesArray, "year");

        movies = parseMovies(titles, images, imDbRatings, years);
        movies.forEach(System.out::println);
    }
}
