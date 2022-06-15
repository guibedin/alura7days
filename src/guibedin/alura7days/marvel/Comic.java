package guibedin.alura7days.marvel;

import guibedin.alura7days.Content;

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
