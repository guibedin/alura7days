package guibedin.alura7days.days.day2;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Day2 {

    // Split attributes from a single movie string
    public static String[] splitAttributes(String movies) {
        movies = movies.replace("{", "");
        movies = movies.replace("}", "");

        return movies.split("\",\"");
    }

    // Parse the specified attribute, creating a list of the same attribute from all movies
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
        List<String> ids;
        List<String> ranks;
        List<String> titles;
        List<String> fullTitles;
        List<String> years;
        List<String> urlImages;
        List<String> crews;
        List<String> imDbRatings;
        List<String> imDbRatingCounts;


        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        int start = body.indexOf("[") + 1;
        int end = body.indexOf("]");

        String moviesJson = body.substring(start, end);
        String[] moviesArray = moviesJson.split("},");

        ids = parseAttribute(moviesArray, "id");
        ids.forEach(System.out::println);

        ranks = parseAttribute(moviesArray, "rank");
        ranks.forEach(System.out::println);

        titles = parseAttribute(moviesArray, "title");
        titles.forEach(System.out::println);

        fullTitles = parseAttribute(moviesArray, "fullTitle");
        fullTitles.forEach(System.out::println);

        years = parseAttribute(moviesArray, "year");
        years.forEach(System.out::println);

        urlImages = parseAttribute(moviesArray, "image");
        urlImages.forEach(System.out::println);

        crews = parseAttribute(moviesArray, "crew");
        crews.forEach(System.out::println);

        imDbRatings = parseAttribute(moviesArray, "imDbRating");
        imDbRatings.forEach(System.out::println);

        imDbRatingCounts = parseAttribute(moviesArray, "imDbRatingCount");
        imDbRatingCounts.forEach(System.out::println);
    }
}
