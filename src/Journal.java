import java.util.Collection;
import java.util.HashMap;

/**
 * Keeps track of all movies in the system and the movies logged by the user along with the ratings for each
 */

public class Journal {
    public static HashMap <Movie, Integer> userMovies;
    
        public Journal(){
            userMovies = new HashMap<>();
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
        public static Collection<Movie> getWatchedMovies() {
            if (!userMovies.isEmpty()){
                return userMovies.keySet();
            } else {
                return userMovies.keySet();
            }
        }
        
         /**
        * Allows user to log a movie and its rating to the user's Journal
        */
        public static void addToUserMovies(String title, int rating){
            HashMap <String, Movie>  movies= DataProcessor.getAllMovies();
            Movie movieEntered = movies.get(title);
            userMovies.putIfAbsent(movieEntered, rating);
            userMovies.replace(movieEntered, rating);
    }

}
