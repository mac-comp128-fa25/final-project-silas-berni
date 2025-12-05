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
    
    //make sure this is sorted by similarityScore
    private PriorityQueue<Movie> recommend(Journal userMovies, HashMap<String, Movie> allMovies) {
        PriorityQueue<Movie> recommendations = new PriorityQueue();

        for (Movie userMovie : journal.getWatchedMovies()) {
            for (Map.Entry<String, Movie> movie : allMovies.entrySet()) {
                if (!journal.getWatchedMovies().contains(movie.getValue())) {
                    double similarity = similarityScore(userMovie, movie.getValue());
                    similarity = similarity * journal.getRating(userMovie);
                    movie.getValue().setSimilarity(similarity);

                    if (similarity > 5) {
                        recommendations.add(movie.getValue());
                    }
                }
            }
        }
        return recommendations;
    }

    private double similarityScore(Movie a, Movie b) {
        double genreScore = genreSimilarity(a, b) * 0.35;
        double actorScore = actorSimilarity(a, b) * 0.25;
        double keywordScore = keywordSimilarity(a, b) * 0.15;
        double directorScore = directorSimilarity(a, b) * 0.25;

        return genreScore + actorScore + keywordScore + directorScore;
    }

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
