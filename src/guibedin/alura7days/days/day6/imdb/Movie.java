package guibedin.alura7days.days.day6.imdb;

import guibedin.alura7days.days.day6.Content;

public record Movie(String title, String urlImage, String rating, String year) implements Content {

    @Override
    public String type() {
        return "Movie";
    }
}
