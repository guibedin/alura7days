package guibedin.alura7days.day5;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ImdbApiCient {

    String apiKey;

    public ImdbApiCient(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getBody() {
        // Make a GET HTTP Request to IMDB API and print the response in stdout
        URI uri = URI.create("https://imdb-api.com/en/API/Top250Movies/" + apiKey);

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
