package guibedin.alura7days.day5;

import java.io.PrintWriter;
import java.util.List;

public class Day5 {

    public static void execute() throws Exception {

        ImdbApiCient client = new ImdbApiCient(System.getenv("IMDB_API_KEY"));
        String jsonBody = client.getBody();
        List<Movie> movies = new ImdbMovieJsonParser(jsonBody).parse();

        HTMLGenerator generator = new HTMLGenerator(new PrintWriter("index_day5.html"));
        generator.generate(movies);
    }
}
