package guibedin.alura7days.days.day3.jackson;

public class MovieJackson {
    private String title;
    private String image;
    private double imDbRating;
    private int year;

    public MovieJackson() { }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setImDbRating(double imDbRating) {
        this.imDbRating = imDbRating;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", urlImage='" + image + '\'' +
                ", rating=" + imDbRating +
                ", year=" + year +
                '}';
    }
}
