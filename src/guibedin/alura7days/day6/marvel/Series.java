package guibedin.alura7days.day6.marvel;

import guibedin.alura7days.day6.Content;

public record Series(String title, String urlImage, String rating, String year) implements Content {

    @Override
    public String type() {
        return "Series";
    }
}
