package guibedin.alura7days;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.rmi.server.ExportException;

public class Main {

    public static void main(String[] args) {

        // Make a GET HTTP Request to IMDB API and print the JSON response in stdout
        String apiKey = System.getenv("IMDB_API_KEY");
        URI uri = URI.create("https://imdb-api.com/en/API/Top250Movies/" + apiKey);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
