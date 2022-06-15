package guibedin.alura7days.days.day6.marvel;

import guibedin.alura7days.days.day6.Content;

public record Comic(String title, String urlImage, String year) implements Content {

    @Override
    public String rating() {
        return "";
    }

    @Override
    public String type() {
        return "Comic";
    }
}
