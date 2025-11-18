import java.util.List;

public class Movie {

    private int movieId;
    private String title;
    private String director;
    private List<String> leadActors;
    private List<String> genres;

    public Movie(int movieId, String title, String director, List<String> leadActors, List<String> genres) {
        this.movieId = movieId;
        this.title = title;
        this.director = director;
        this.leadActors = leadActors;
        this.genres = genres;
    }

    private int getMovieId() {
        return movieId;
    }

    private String getTitle() {
        return title;
    }

    private String getDirector() {
        return director;
    }

    private List<String> getLeadActors() {
        return leadActors;
    }

    private List<String> getGenres() {
        return genres;
    }
}

