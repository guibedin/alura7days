package guibedin.alura7days.imdb;

import guibedin.alura7days.Content;

public record Movie(String title, String urlImage, String rating, String year) implements Content, Comparable<Content> {

    @Override
    public String type() {
        return "Movie";
    }

    @Override
    public int compareTo(Content other) {
        return this.rating().compareTo(other.rating());
    }

    
}
