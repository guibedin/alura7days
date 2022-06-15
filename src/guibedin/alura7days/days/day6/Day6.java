package guibedin.alura7days.days.day6;


import guibedin.alura7days.days.day6.imdb.ImdbApiClient;
import guibedin.alura7days.days.day6.imdb.ImdbMovieJsonParser;
import guibedin.alura7days.days.day6.imdb.Movie;
import guibedin.alura7days.days.day6.marvel.*;

import java.io.PrintWriter;
import java.util.List;

public class Day6 {

    public static void execute() throws Exception {
        // Get IMDB Movies
        String moviesJson = new ImdbApiClient(System.getenv("IMDB_API_KEY")).getBody("Top250Movies");
        List<Movie> movies = new ImdbMovieJsonParser(moviesJson).parse();
        PrintWriter writer = new PrintWriter("movies.html");
        new HTMLGenerator(writer).generate(movies);
        writer.close();


        // Setup Marvel API Client
        MarvelApiClient marvelClient = new MarvelApiClient(
                System.getenv("MARVEL_PUBLIC_KEY"),
                System.getenv("MARVEL_PRIVATE_KEY"));

        // Get Marvel Comics
        String comicsJson = marvelClient.getBody("comics");
        List<Comic> comics = new MarvelComicsJsonParser(comicsJson).parse();
        writer = new PrintWriter("comics.html");
        new HTMLGenerator(writer).generate(comics);
        writer.close();

        // Get Marvel Series
        String seriesJson = marvelClient.getBody("series");;
        List<Series> series = new MarvelSeriesJsonParser(seriesJson).parse();
        writer = new PrintWriter("series.html");
        new HTMLGenerator(writer).generate(series);
        writer.close();
    }
}
