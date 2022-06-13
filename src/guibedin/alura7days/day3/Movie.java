package guibedin.alura7days.day3;

public class Movie {
    private String title;
    private String urlImage;
    private double rating;
    private int year;

    public Movie(String title, String urlImage, double rating, int year) {
        this.title = title;
        this.urlImage = urlImage;
        this.rating = rating;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", rating=" + rating +
                ", year=" + year +
                '}';
    }
}
