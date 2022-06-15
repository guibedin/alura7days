package guibedin.alura7days.marvel;

import guibedin.alura7days.Content;

public record Series(String title, String urlImage, String rating, String year) implements Content {

    @Override
    public String type() {
        return "Series";
    }
}
