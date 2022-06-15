package guibedin.alura7days.days.day6.imdb;

import guibedin.alura7days.days.day6.APIClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ImdbApiClient implements APIClient {

    private final String apiKey;

    public ImdbApiClient(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String getBody(String endpoint) {
        // Make a GET HTTP Request to IMDB API and print the response in stdout
        URI uri = URI.create(String.format("https://imdb-api.com/en/API/%s/%s", endpoint, this.apiKey));

        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
