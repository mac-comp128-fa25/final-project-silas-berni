import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Recommender {

    final double GENRE_WEIGHT = 0.35;
    final double KEYWORD_WEIGHT = 0.15;
    final double DIRECTOR_WEIGHT = 0.25;
    final double ACTORS_WEIGHT = 0.25;

    private Journal journal;

    public Recommender(Journal journal) {
        this.journal = journal;
    }
    
    /**
     * Compares all user movies to all other movies in the data set
     * Builds a max heap of the most similar movies to recommend to the user
     * @param userMovies All movies the user has entered
     * @param allMovies All movies in the greater data set
     * @return A max heap of Movie objects sorted by recommendation score
     */
    public PriorityQueue<Movie> recommend(Journal userMovies, HashMap<String, Movie> allMovies) {
        PriorityQueue<Movie> recommendations = new PriorityQueue<>(new MovieComparator());

        for (Movie userMovie : journal.getWatchedMovies()) {
            for (Map.Entry<String, Movie> movie : allMovies.entrySet()) {
                if (!journal.getWatchedMovies().contains(movie.getValue())) {
                    double similarity = similarityScore(userMovie, movie.getValue());
                    similarity = similarity * journal.getRating(userMovie);
                    movie.getValue().setSimilarity(similarity);

                    if (similarity > 2) {
                        recommendations.add(movie.getValue());
                    }
                }
            }
        }
        return recommendations;
    }

    /**
     * Computes the final weighted similarity score between two movies
     * @param a The first movie being compared
     * @param b The second movie being compared
     * @return The similarity score between the two movies
     */
    private double similarityScore(Movie a, Movie b) {
        double genreScore = genreSimilarity(a, b) * 0.35;
        double actorScore = actorSimilarity(a, b) * 0.25;
        double keywordScore = keywordSimilarity(a, b) * 0.15;
        double directorScore = directorSimilarity(a, b) * 0.25;

        return genreScore + actorScore + keywordScore + directorScore;
    }

    /**
     * Computes the Jaccard Similarity of the genres of two movies
     * @param a The first movie being compared
     * @param b The second movie being compared
     * @return Genre similarity score
     */
    private double genreSimilarity(Movie a, Movie b) {
        Set<String> genresA = new HashSet<>();
        Set<String> genresB = new HashSet<>();
        Set<String> union = new HashSet<>();
        Set<String> intersection = new HashSet<>();
        Collections.addAll(genresA, a.getGenres());
        Collections.addAll(genresB, b.getGenres());

        union.addAll(genresA);
        union.addAll(genresB);

        intersection.addAll(genresA);
        intersection.retainAll(genresB);

        double score = (intersection.size() / union.size());
        return score;
    }

    /**
     * Computes the Jaccard Similarity of the actors of two movies
     * @param a The first movie being compared
     * @param b The second movie being compared
     * @return Actor similarity score
     */
    private double actorSimilarity(Movie a, Movie b) {
        Set<String> actorsA = new HashSet<>();
        Set<String> actorsB = new HashSet<>();
        Set<String> union = new HashSet<>();
        Set<String> intersection = new HashSet<>();
        Collections.addAll(actorsA, a.getLeadActors());
        Collections.addAll(actorsB, b.getLeadActors());

        union.addAll(actorsA);
        union.addAll(actorsB);

        intersection.addAll(actorsA);
        intersection.retainAll(actorsB);

        double score = (intersection.size() / union.size());
        return score;
    }

    /**
     * Computes the Jaccard Similarity of the keywords of two movies
     * @param a The first movie being compared
     * @param b The second movie being compared
     * @return Keywords similarity score
     */
    private double keywordSimilarity(Movie a, Movie b) {
        Set<String> keywordsA = new HashSet<>();
        Set<String> keywordsB = new HashSet<>();
        Set<String> union = new HashSet<>();
        Set<String> intersection = new HashSet<>();
        Collections.addAll(keywordsA, a.getKeywords());
        Collections.addAll(keywordsB, b.getKeywords());

        union.addAll(keywordsA);
        union.addAll(keywordsB);

        intersection.addAll(keywordsA);
        intersection.retainAll(keywordsB);

        double score = (intersection.size() / union.size());
        return score;
    }

    /**
     * Computes the Jaccard Similarity of the directors of two movies
     * @param a The first movie being compared
     * @param b The second movie being compared
     * @return Director similarity score
     */
    private double directorSimilarity(Movie a, Movie b) {
        String directorA = a.getDirector();
        String directorB = b.getDirector();

        if ((directorA.compareTo(directorB)) != 0) {
            return 1;
        } else {
            return 0;
        }
    }

}
