import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Tests {
    static DataProcessor processor;
    static HashMap<String, Movie> movies;
    Journal journal;

    @BeforeAll
    static void setupAll() {
        processor = new DataProcessor();
        movies = processor.getAllMovies();  
        
    }

    @BeforeEach
    void resetJournal() {
        journal = new Journal(processor);
    }

    // Tests for data importing 
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
    
    // Tests for Category Maps
    
   @Test
    void testGenreMapContainsAllGenres() {
        Map<String, List<Movie>> genreMap = processor.getMoviesByGenre();

        assertTrue(genreMap.containsKey("Action"));
        assertTrue(genreMap.containsKey("Adventure"));
        assertTrue(genreMap.containsKey("Fantasy"));
        assertTrue(genreMap.containsKey("Science"));
        assertTrue(genreMap.containsKey("Fiction"));
        assertTrue(genreMap.containsKey("Crime"));
        assertTrue(genreMap.containsKey("Drama"));
        assertTrue(genreMap.containsKey("Thriller"));
        assertTrue(genreMap.containsKey("Animation"));
        assertTrue(genreMap.containsKey("Family"));
        assertTrue(genreMap.containsKey("Western"));
        assertTrue(genreMap.containsKey("Comedy"));

        assertEquals(12, genreMap.size());
    }
        
    @Test
    void testFirstMovieInEachGenre(){
        Map<String, List<Movie>> genreMap = processor.getMoviesByGenre();
        List<Movie> actionMovies = genreMap.get("Action");
        Movie avatar = actionMovies.get(0);
        assertEquals("Avatar", avatar.getTitle());  //It is not adding the first movie to action
    }

    @Test
    void testListInsidemap() {
        Map<String, List<Movie>> genreMap = processor.getMoviesByGenre();
        List<Movie> actionMovies = genreMap.get("Action");
        assertEquals(actionMovies.size(), 18); // it is not adding one movie
    }
    
    @Test
    void testListInsidemap2() {
        Map<String, List<Movie>> genreMap = processor.getMoviesByGenre();
        List<Movie> crimeMovies = genreMap.get("Crime");
        assertEquals(crimeMovies.size(), 3); // it is not adding one movie
    }

    @Test
    void testListInsidemapCrime() {
        Map<String, List<Movie>> genreMap = processor.getMoviesByGenre();
        List<Movie> crimeMovies = genreMap.get("Crime");
    }

    // Tests for Journal
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
}
