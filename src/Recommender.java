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
     * Compares all user movies to all other movies in the data set Builds a max heap of the most
     * similar movies to recommend to the user
     * 
     * @param userMovies All movies the user has entered
     * @param allMovies  All movies in the greater data set
     * @return A max heap of Movie objects sorted by recommendation score
     */
    public PriorityQueue<Movie> recommend(Journal userMovies, HashMap<String, Movie> allMovies) {
        PriorityQueue<Movie> recommendations = new PriorityQueue<>(new MovieComparator());

        for (Movie userMovie : journal.getWatchedMovies()) {
            for (Map.Entry<String, Movie> movie : allMovies.entrySet()) {
                if (!journal.getWatchedMovies().contains(movie.getValue())) {
                    Movie candidate = movie.getValue();
                    if (candidate.getPopularity() > 60) {
                        double similarity = similarityScore(userMovie, candidate);

                        double rating = journal.getRating(userMovie);
                        double ratingWeight = 1 + (rating - 3) * 0.3;
                        similarity = similarity * ratingWeight;

                        double popularity = Math.log(candidate.getPopularity() + 1);
                        similarity = similarity * popularity;
                        candidate.setSimilarity(similarity);

                        if (similarity > 2 && !recommendations.contains(candidate)) {
                            recommendations.add(candidate);
                        }

                    }
                }
            }
        }
        return recommendations;
    }

    /**
     * Computes the final weighted similarity score between two movies
     * 
     * @param a The first movie being compared
     * @param b The second movie being compared
     * @return The similarity score between the two movies
     */
    private double similarityScore(Movie a, Movie b) {
        double genreScore = genreSimilarity(a, b) * GENRE_WEIGHT;
        double actorScore = actorSimilarity(a, b) * ACTORS_WEIGHT;
        double keywordScore = keywordSimilarity(a, b) * KEYWORD_WEIGHT;
        double directorScore = directorSimilarity(a, b) * DIRECTOR_WEIGHT;

        return genreScore + actorScore + keywordScore + directorScore;
    }

    /**
     * Computes the Jaccard Similarity of the genres of two movies
     * 
     * @param a The first movie being compared
     * @param b The second movie being compared
     * @return Genre similarity score
     */
    private double genreSimilarity(Movie a, Movie b) {
        return jaccard(a.getGenres(), b.getGenres());
    }

    /**
     * Computes the Jaccard Similarity of the actors of two movies
     * 
     * @param a The first movie being compared
     * @param b The second movie being compared
     * @return Actor similarity score
     */
    private double actorSimilarity(Movie a, Movie b) {
        return jaccard(a.getLeadActors(), b.getLeadActors());
    }

    /**
     * Computes the Jaccard Similarity of the keywords of two movies
     * 
     * @param a The first movie being compared
     * @param b The second movie being compared
     * @return Keywords similarity score
     */
    private double keywordSimilarity(Movie a, Movie b) {
        return jaccard(a.getKeywords(), b.getKeywords());
    }

    /**
     * Computes the Jaccard Similarity of the directors of two movies
     * 
     * @param a The first movie being compared
     * @param b The second movie being compared
     * @return Director similarity score
     */
    private double directorSimilarity(Movie a, Movie b) {
        String directorA = a.getDirector();
        String directorB = b.getDirector();

        if ((directorA.compareTo(directorB)) != 0) {
            return 0;
        } else {
            return 1;
        }
    }

    private double jaccard(String[] a, String[] b) {
        Set<String> A = new HashSet<>(Arrays.asList(a));
        Set<String> B = new HashSet<>(Arrays.asList(b));

        Set<String> intersection = new HashSet<>(A);
        intersection.retainAll(B);
        Set<String> union = new HashSet<>(A);
        union.addAll(B);

        if (union.size() == 0)
            return 0.0;
        return (double) (intersection.size() / union.size());
    }

}
