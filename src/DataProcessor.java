import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DataProcessor {

    private HashMap<String, Movie> allMovies = new HashMap<>();
    private HashMap<String, List<Movie>> moviesGenre;
    private HashMap<String, List<Movie>> moviesActors;
    private HashMap<String, List<Movie>> moviesKeywords;
    private HashMap<String, List<Movie>> moviesDirector;

    
     /**
      * Reads in the CSV File and creates sorted maps of all the movies by category
      */
    public DataProcessor() {
        moviesGenre = new HashMap<>();
        moviesActors = new HashMap<>();
        moviesKeywords = new HashMap<>();
        moviesDirector = new HashMap<>();

        processMovies();
        buildCategoryMaps();
    }

    /**
     * Builds maps sorted by genre, actors, keywords, and directors.
     * All movies which share a genre, actor, keyword, or director are added to a list as the value for that key
     */
    private void buildCategoryMaps() {
        for (Map.Entry<String, Movie> movie : allMovies.entrySet()) {
            for (String genre : movie.getValue().getGenres()) {
                genre = genre.trim();
                if (!moviesGenre.containsKey(genre)) {
                    moviesGenre.put(genre, new ArrayList<>());
                }
                moviesGenre.get(genre).add(movie.getValue());

            }

            for (String actor : movie.getValue().getLeadActors()) {
                if (!moviesActors.containsKey(actor)) {
                    moviesActors.put(actor, new ArrayList<>());
                }
                moviesActors.get(actor).add(movie.getValue());

            }

            for (String keyword : movie.getValue().getKeywords()) {
                if (!moviesKeywords.containsKey(keyword)) {
                    moviesKeywords.put(keyword, new ArrayList<>());
                }
                moviesKeywords.get(keyword).add(movie.getValue());
                
            }

            String director = movie.getValue().getDirector();
            if (!moviesDirector.containsKey(director)) {
                moviesDirector.put(director, new ArrayList<>());
            }
            moviesDirector.get(director).add(movie.getValue());
            
        }
    }

    /**
     * Reads in the CSV file and creates a map of Movie objects with their titles as keys
     */
    private void processMovies() {
        if (!allMovies.isEmpty()) {
            return;
        }

        try (InputStream inputStream = DataProcessor.class.getClassLoader().getResourceAsStream("data.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            CSVReader reader = new CSVReader(inputStreamReader)) {

            String[] nextLine;
            boolean firstLine = true;
            while ((nextLine = reader.readNext()) != null) {
                if(firstLine) {
                    firstLine = false;
                    continue;
                }
                Movie newMovie = new Movie();

                newMovie.setGenres(nextLine[0].split(" "));
                newMovie.setKeywords(nextLine[1].split(" "));
                newMovie.setTitle(nextLine[2]);
                newMovie.setDirector(nextLine[4]);

                String[] unsortedNames = nextLine[3].split(" ");
                ArrayList<String> sortedNames = new ArrayList<>();

                for (int i = 0; i < unsortedNames.length; i += 2) {
                    if (i + 1 < unsortedNames.length) {
                        sortedNames.add(new String(unsortedNames[i] + " " + unsortedNames[i + 1]));
                    }
                }

                String[] cast = new String[sortedNames.size()];
                sortedNames.toArray(cast);
                newMovie.setCast(cast);

                allMovies.put(newMovie.getTitle(), newMovie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Movie> getAllMovies() {
        return allMovies;
    }

    public HashMap<String, List<Movie>> getMoviesByGenre() {
        return moviesGenre;
    }

    public HashMap<String, List<Movie>> getMoviesByDirector() {
        return moviesDirector;
    }

    public HashMap<String, List<Movie>> getMoviesByKeywords() {
        return moviesKeywords;
    }

    public HashMap<String, List<Movie>> getMoviesByActors() {
        return moviesActors;
    }
}

