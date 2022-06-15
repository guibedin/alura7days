package guibedin.alura7days.days.day4;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Day4 {

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

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        // Parse JSON response into multiple lists - Configure it to not fail when reading unknown properties
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Reads body into a jsonNode, which is easier to handle
        JsonNode jsonNode = objectMapper.readTree(body);

        // Get the 'items' from the jsonNode, which represents the list of movie
        String moviesJson = jsonNode.get("items").toString();

        // Creates a list of movies from the json
        List<MovieRecord> movies = objectMapper.readValue(moviesJson, new TypeReference<List<MovieRecord>>() {});
        File htmlFile = new File("index.html");

        PrintWriter writer = new PrintWriter(htmlFile);
        HTMLGenerator generator = new HTMLGenerator(writer);
        generator.generate(movies);

        writer.close();
    }
}
