import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Keeps track of all movies in the system and the movies logged by the user along with the ratings
 * for each
 */

public class Journal {

    private HashMap<Movie, Integer> userMovies = new HashMap<>();
    private DataProcessor dataProcessor;
    private Movie userTasteProfile;


    public Journal(DataProcessor processor) {
        this.dataProcessor = processor;
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

    /**
     * Allows user to log a movie and its rating to the user's Journal
     */
    public void addToUserMovies(String title, int rating) {
        Movie movie = dataProcessor.getAllMovies().get(title);
        if (movie != null) {
            userMovies.put(movie, rating);
            updateUserTasteProfile(movie);
        }
    }

    public void updateUserTasteProfile(Movie movie) {
        userTasteProfile.addGenres(movie.getGenres());
        userTasteProfile.addCast(movie.getLeadActors());
        userTasteProfile.addKeywords(movie.getKeywords());
        userTasteProfile.addDirector(movie.getDirector());
    }

}
