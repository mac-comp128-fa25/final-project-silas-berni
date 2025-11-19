import java.util.Collection;
import java.util.HashMap;

/**
 * Keeps track of all movies in the system and the movies logged by the user along with the ratings for each
 */

public class Journal {
    private HashMap <Movie, Integer> userMovies;

    public Journal(){
        userMovies = new HashMap<>();
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
    * Returns all movies in the user's journal
    */
    public Collection<Movie> getWatchedMovies() {
        return userMovies.keySet();
    }

}
