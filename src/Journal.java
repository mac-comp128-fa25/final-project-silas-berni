import java.util.HashMap;

public class Journal {
    private HashMap <String, Movie> allMovies;
    private HashMap <Movie, Integer> ratings;

    public Journal(){
        allMovies = new HashMap<>();
        ratings = new HashMap<>();
    }
    
    public void addMovie(Movie m) {
        allMovies.put(m.getTitle(), m);
    }

    public boolean containsMovie(String title) {
        return allMovies.containsKey(title);
    }


}
