import java.util.HashMap;

public class Journal {
    private HashMap <String, Movie> allMovies;
    private HashMap <Movie, Integer> ratings;

    public Journal(){
        allMovies = new HashMap<>();
        ratings = new HashMap<>();
    }
}
