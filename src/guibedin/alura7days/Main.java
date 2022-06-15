package guibedin.alura7days;

import guibedin.alura7days.imdb.ImdbApiClient;
import guibedin.alura7days.imdb.ImdbMovieJsonParser;
import guibedin.alura7days.imdb.Movie;
import guibedin.alura7days.marvel.*;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        try {
            // Get IMDB Movies
            String moviesJson = new ImdbApiClient(System.getenv("IMDB_API_KEY")).getBody("Top250Movies");
            List<Movie> movies = new ImdbMovieJsonParser(moviesJson).parse();

            // Get Marvel Comics and Series
            MarvelApiClient marvelClient = new MarvelApiClient(
                    System.getenv("MARVEL_PUBLIC_KEY"),
                    System.getenv("MARVEL_PRIVATE_KEY"));

            String comicsJson = marvelClient.getBody("comics");
            List<Comic> comics = new MarvelComicsJsonParser(comicsJson).parse();

            String seriesJson = marvelClient.getBody("series");;
            List<Series> series = new MarvelSeriesJsonParser(seriesJson).parse();

            // Creates a list with all of the above
            List<? extends Content> allLists = Stream.of(movies, comics).flatMap(Collection::stream).toList();
            allLists = Stream.of(allLists, series).flatMap(Collection::stream).toList();

            PrintWriter writer = new PrintWriter("all.html");
            new HTMLGenerator(writer).generate(allLists);
            writer.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
