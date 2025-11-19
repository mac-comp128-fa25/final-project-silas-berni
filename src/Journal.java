import java.util.Collection;
import java.util.HashMap;

/**
 * Keeps track of all movies in the system and the movies logged by the user along with the ratings for each
 */

public class Journal {
    private HashMap <String, Movie> allMovies;
    private HashMap <Movie, Integer> userMovies;

    public Journal(){
        allMovies = new HashMap<>();
        userMovies = new HashMap<>();
    }
    
    /**
    * Adds movie to the Movie database
    */
    public void addMovie(Movie m) {
        allMovies.put(m.getTitle(), m);
    }
    
    /**
    * Checks if the Movie database contains a movie
    */
    public boolean containsMovie(String title) {
        return allMovies.containsKey(title);
    }

    /**
    * Gets movie from the database
    */
    public Movie getMovie(String title){
        return allMovies.get(title);
    }

    /**
    * Allows user to log a movie and its rating to the user's Journal
    */
    public void logMovie(String title, int rating){
        Movie m = allMovies.get(title);
        if (m != null){
            userMovies.put(m, rating);
        }
    }

    /**
    * Checks if a movie is in the user's journal
    */
    public boolean hasWatched(Movie m) {
        return userMovies.containsKey(m);
    }

    /**
    * Gets the rating from a movie in the user's journal
    */
    public int getRating(Movie m) {
        return userMovies.getOrDefault(m, -1); 
    }

    /**
    * Returns all movies in the database
    */
    public Collection<Movie> getAllMovies() {
        return allMovies.values();
    }

    /**
    * Returns all movies in the user's journal
    */
    public Collection<Movie> getWatchedMovies() {
        return userMovies.keySet();
    }

}
