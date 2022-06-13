package guibedin.alura7days.day3;

import java.net.URI;
import java.util.List;

public class Day3 {

    public static void execute() {

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

        try {
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

            urlImages = parseAttribute(moviesArray, "urlImage");
            urlImages.forEach(System.out::println);

            crews = parseAttribute(moviesArray, "crew");
            crews.forEach(System.out::println);

            imDbRatings = parseAttribute(moviesArray, "imDbRating");
            imDbRatings.forEach(System.out::println);

            imDbRatingCounts = parseAttribute(moviesArray, "imDbRatingCount");
            imDbRatingCounts.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
