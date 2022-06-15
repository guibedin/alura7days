package guibedin.alura7days.day6.marvel;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import guibedin.alura7days.day6.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class MarvelComicsJsonParser implements JsonParser {

    private final String json;

    public MarvelComicsJsonParser(String json) {
        this.json = json;
    }
    @Override
    public List<Comic> parse() {
        // Parse JSON response into multiple lists - Configure it to not fail when reading unknown properties
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            JsonNode results = objectMapper.readTree(this.json).get("data").get("results");
            List<Comic> comics = new ArrayList<>();
            results.forEach(result -> {
                String path = result.get("thumbnail").get("path").toString().replace("\"", "");
                String extension = result.get("thumbnail").get("extension").toString().replace("\"", "");

                String title = result.get("title").toString().replace("\"", "");
                String urlImage = String.format("%s/portrait_fantastic.%s", path, extension);
                String year = result.get("dates").elements().next().get("date").toString().replace("\"", "");

                comics.add(new Comic(title, urlImage, year));
            });

            return comics;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
