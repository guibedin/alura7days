package guibedin.alura7days.day5;

import java.util.ArrayList;
import java.util.List;

public class ImdbMovieJsonParser {
    
    private String json;
    
    public ImdbMovieJsonParser(String json) {
        this.json = json;
    }

    // Split attributes from a single movie string
    private String[] splitAttributes(String movies) {
        movies = movies.replace("{", "");
        movies = movies.replace("}", "");

        return movies.split("\",\"");
    }

    private List<String> parseAttribute(String[] movies, String attribute) {
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

    public List<Movie> parse() {

        int start = this.json.indexOf("[") + 1;
        int end = this.json.indexOf("]");

        String moviesJson = this.json.substring(start, end);
        String[] moviesArray = moviesJson.split("},");

        List<String> titles  = parseAttribute(moviesArray, "title");
        List<String> images = parseAttribute(moviesArray, "image");
        List<String> imDbRatings = parseAttribute(moviesArray, "imDbRating");
        List<String> years = parseAttribute(moviesArray, "year");

        List<Movie> movies = new ArrayList<>();

        for(int i = 0; i < titles.size(); i++) {
            movies.add(new Movie(titles.get(i), images.get(i),
                    Double.parseDouble(imDbRatings.get(i)), Integer.parseInt(years.get(i))));
        }

        return movies;
    }
}
