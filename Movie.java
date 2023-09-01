import java.net.URL;

/**
 * @author TedrosTsegay
 * Date   06/02/2023
 */
public class Movie {
    private int id;
    private String color;
    private String movieTitle;
    private int duration;
    private String director;
    private String[] actors;
    private URL imdbLink;
    private String country;

    public Movie(int id, String color, String movieTitle, int duration, String director,
                 String[] actors, URL imdbLink, String country) {
        this.id = id;
        this.color = color;
        this.movieTitle = movieTitle;
        this.duration = duration;
        this.director = director;
        this.actors = actors;
        this.imdbLink = imdbLink;
        this.country = country;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDirectorName() {
        return director;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    public URL getImdbLink() {
        return imdbLink;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
