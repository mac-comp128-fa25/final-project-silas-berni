import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Tests {
    DataProcessor processor;
    HashMap<String, Movie> movies;
    Journal journal;
    Recommender recommender;

    @BeforeEach
    void setupAll() {
        processor = new DataProcessor();
        movies = processor.getAllMovies(); 
        journal = new Journal(processor);
        recommender = new Recommender(journal);
    }

    /**
     * Tests for Data Processor
     */
     
    @Test
    void testMoviesLoaded() {
        assertFalse(movies.isEmpty());
        assertTrue(movies.containsKey("Avatar"));
        assertTrue(movies.containsKey("Tangled"));
    }

    @Test
    void testDataImportingCorrectly() {
        Movie m = movies.get("Avatar");
        assertNotNull(m);
        assertEquals("James Cameron", m.getDirector());
        assertEquals(5, m.getLeadActors().length);
        assertEquals("Sam Worthington", m.getLeadActors()[0]);
    }

    @Test
    void testCastImporting() {
        Movie tangled = movies.get("Tangled");
        String[] cast = tangled.getLeadActors();
        assertEquals("Zachary Levi", cast[0]);
        assertEquals("Mandy Moore", cast[1]);
    }
    
   @Test
    void testGenreMapHasAllGenresFromDataset() {
        Map<String, List<Movie>> genreMap = processor.getMoviesByGenre();

         Set<String> genresInData = new HashSet<>();

        for (Movie movie : processor.getAllMovies().values()) {
            genresInData.addAll(Arrays.asList(movie.getGenres()));
        }
        assertEquals(genresInData, genreMap.keySet());
    }

    @Test
    void testMoviesAreInCorrectGenreLists() {
        Map<String, List<Movie>> genreMap = processor.getMoviesByGenre();
    
        for (Movie movie : processor.getAllMovies().values()) {
            for (String genre : movie.getGenres()) {
                assertTrue(
                    genreMap.containsKey(genre)
                );
    
                assertTrue(
                    genreMap.get(genre).contains(movie)
                );
            }
        }
    }

    /**
     * Tests for Journal
     */
    @Test
    void testAddMovieToJournal() {
        journal.addToUserMovies("Avatar", 9);
        Movie m = movies.get("Avatar");

        assertTrue(journal.hasWatched(m));
        assertEquals(9, journal.getRating(m));
    }

    @Test
    void testWatchedMoviesCount() {
        journal.addToUserMovies("Avatar", 9);
        journal.addToUserMovies("Tangled", 8);

        assertEquals(2, journal.getWatchedMovies().size());
    }

    @Test
    void testDefaultRatingIsMinusOne() {
        Movie m = movies.get("Avatar");
        assertEquals(-1, journal.getRating(m));
    }
 
   /**
     * Tests for Recommender
     */
    @Test
    void similarMovieRecommended() {
        Movie watched = movies.get("Avatar");
        journal.addToUserMovies("Avatar", 4);

        PriorityQueue<Movie> recs =
                recommender.recommend(journal, movies);

        assertFalse(recs.isEmpty());

        Movie top = recs.peek();
        assertNotNull(top);
        assertNotEquals(watched, top);
    }  
    
    @Test 
    void sharedGenresRecommendation(){
        Movie watched = movies.get("Avatar");
        journal.addToUserMovies("Avatar", 4);

        PriorityQueue<Movie> recs = recommender.recommend(journal, movies);
        
        Set<String> watchedGenres =
            new HashSet<>(Arrays.asList(watched.getGenres()));
        
        boolean sameGenre = false;
        for(Movie rec : recs){
            for (String genre : rec.getGenres()){
                if (watchedGenres.contains(genre)){
                    sameGenre = true;
                    break;
                }
            }
            if (sameGenre) break;
        }
        assertTrue (sameGenre);
    }

    @Test 
    void effectOfRatingOnRecs(){
        journal.addToUserMovies("Avatar", 5);
        int highRatingSize = recommender.recommend(journal, movies).size();

        journal.addToUserMovies("Avatar", 1);
        int lowRatingSize = recommender.recommend(journal, movies).size();

        assertTrue(highRatingSize >= lowRatingSize);
    }

    @Test 
    void effectOfPopularityOnRecs(){
        journal.addToUserMovies("Avatar", 4);

        PriorityQueue<Movie> recs =
                recommender.recommend(journal, movies);

        for (Movie m : recs) {
            assertTrue(m.getPopularity() > 60);
        }
    }
}

