import java.util.Collection;
import java.util.HashMap;

/**
 * Keeps track of all movies in the system and the movies logged by the user along with the ratings for each
 */

public class Journal {

    private static HashMap<Movie, Integer> userMovies = new HashMap<>();

    
        public Journal(){
        }

        /**
        * Checks if a movie is in the user's journal
        */
        public static boolean hasWatched(Movie m) {
            return userMovies.containsKey(m);
        }
    
        /**
        * Gets the rating from a movie in the user's journal
        */
        public static int getRating(Movie m) {
            return userMovies.getOrDefault(m, -1); 
        }
    
        /**
        * Returns all movies in the user's journal
        */
        public static Collection<Movie> getWatchedMovies() {
                return userMovies.keySet();
        }
        
         /**
        * Allows user to log a movie and its rating to the user's Journal
        */
        public static void addToUserMovies(String title, int rating){
            Movie movie = DataProcessor.getAllMovies().get(title);
            if (movie != null) {
                userMovies.put(movie, rating);
            }
    }

}
