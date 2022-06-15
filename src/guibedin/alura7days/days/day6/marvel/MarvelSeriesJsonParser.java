package guibedin.alura7days.days.day6.marvel;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import guibedin.alura7days.days.day6.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class MarvelSeriesJsonParser implements JsonParser {

    private final String json;

    public MarvelSeriesJsonParser(String json) {
        this.json = json;
    }
    
    @Override
    public List<Series> parse() {
        // Parse JSON response into multiple lists - Configure it to not fail when reading unknown properties
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            JsonNode results = objectMapper.readTree(this.json).get("data").get("results");
            List<Series> series = new ArrayList<>();
            results.forEach(result -> {
                String path = result.get("thumbnail").get("path").toString().replace("\"", "");
                String extension = result.get("thumbnail").get("extension").toString().replace("\"", "");

                String title = result.get("title").toString().replace("\"", "");
                String urlImage = String.format("%s/portrait_fantastic.%s", path, extension);
                String rating = result.get("rating").toString().replace("\"", "");
                String year = result.get("startYear").toString().replace("\"", "");

                series.add(new Series(title, urlImage, rating, year));
            });

            return series;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
