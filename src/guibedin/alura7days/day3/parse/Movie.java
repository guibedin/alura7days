package guibedin.alura7days.day3.parse;

public class Movie {
    private String title;
    private String image;
    private double imDbRating;
    private int year;

    public Movie() { }
    public Movie(String title, String urlImage, double rating, int year) {
        this.title = title;
        this.image = urlImage;
        this.imDbRating = rating;
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
