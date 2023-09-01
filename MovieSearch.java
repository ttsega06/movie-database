import java.net.URL;
import java.util.Objects;

/**
 * @author TedrosTsegay
 * Date    06/02/2023
 */

public class MovieSearch implements Comparable<MovieSearch> {
    private int year;
    private double imdbScore;
    private String language;
    private String rating;
    private Movie info;

    //Parametrised constructor
    public MovieSearch(int year, double imdbScore, String language, String rating, Movie info) {
        this.year = year;
        this.imdbScore = imdbScore;
        this.language = language;
        this.rating = rating;
        this.info = info;
    }
    public String printMovieInfo() {
        String movieInfo = "-----------------------------" + "\n" +
                "ID: " + this.getId() + "\n" +
                "Color: " + this.getColor() + "\n" +
                "Title: " + this.getMovieTitle() + "\n" +
                "Duration: " + this.getDuration() + " Minutes" + "\n" +
                "Director Name: " + this.getDirectorName() + "\n" +
                "Actor 1: " + this.getActor1() + "\n" +
                "Actor 2: " + this.getActor2() + "\n" +
                "Actor 3: " + this.getActor3() + "\n" +
                "IMDB Link: " + this.getImdbLink() + "\n" +
                "Language: " + this.getLanguage() + "\n" +
                "Country: " + this.getCountry() + "\n" +
                "Content Rating: " + this.getRating() + "\n" +
                "Year: " + this.getYear() + "\n" +
                "IMDB Score: " + this.getImdbScore() + "\n" +
                "-----------------------------\n";
        return movieInfo;
    }

   //Setter and Getter methods
    public int getId() {
        return info.getID();
    }

    public String getColor() {
        return info.getColor();
    }

    public String getMovieTitle() {
        return info.getMovieTitle();
    }

    public int getDuration() {
        return info.getDuration();
    }

    public String getDirectorName() {
        return info.getDirectorName();
    }

    public String getActor1() {
        return info.getActors()[0];
    }

    public String getActor2() {
        return info.getActors()[1];
    }

    public String getActor3() {
        return info.getActors()[2];
    }

    public URL getImdbLink() {
        return info.getImdbLink();
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return info.getCountry();
    }

    public String getRating() {
        return rating;
    }

    public int getYear() {
        return year;
    }

    public double getImdbScore() {
        return imdbScore;
    }

    public void setId(int id) {
        this.info.setID(id);
    }

    public void setColor(String color) {
        this.info.setColor(color);
    }

    public void setMovieTitle(String movieTitle) {
        this.info.setMovieTitle(movieTitle);
    }

    public void setDuration(int duration) {
        this.info.setDuration(duration);
    }

    public void setActor_1(String actor_1) {
        this.info.getActors()[0] = actor_1;
    }

    public void setActor_3(String actor_3) {
        this.info.getActors()[2] = actor_3;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setCountry(String country) {
        this.info.setCountry(country);
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, imdbScore, language, rating);
    }

    @Override
    public String toString() {
        return Integer.toString(info.getID());
    }

    @Override
    public int compareTo(MovieSearch o) {
        if (this.info.getID() == o.info.getID()) {
            return 0;
        } else if (this.info.getID() < o.info.getID()) {
            return -1;
        } else {
            return 1;
        }
    }
}
