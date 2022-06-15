package guibedin.alura7days.days.day6.marvel;

import guibedin.alura7days.days.day6.APIClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.MessageDigest;

public class MarvelApiClient implements APIClient {

    private final String publicKey;
    private final String privateKey;

    public MarvelApiClient(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    @Override
    public String getBody(String endpoint) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String hash = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(String.format("%s%s%s", timestamp, this.privateKey, this.publicKey).getBytes());
            StringBuffer sb = new StringBuffer();
            for (byte b : md.digest()) {
                sb.append(String.format("%02x", b & 0xff));
            }
            hash = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        URI uri = URI.create(String.format("https://gateway.marvel.com:443/v1/public/%s?limit=10&ts=%s&apikey=%s&hash=%s", endpoint, timestamp, this.publicKey, hash));
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
