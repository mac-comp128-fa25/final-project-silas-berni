import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DataProcessor {
    
    private HashMap <String, Movie> allMovies;


    public DataProcessor() {
        allMovies = new HashMap<>();
    }

    private HashMap<String,Movie> processMovieCsv() {
        try (InputStream inputStream = DataProcessor.class.getClassLoader().getResourceAsStream("data.csv");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        CSVReader reader = new CSVReader(inputStreamReader));

        String[] nextLine;
        while((nextLine = reader.readNext()) != null) {
            Movie newMovie = new Movie();
            newMovie.setGenres(nextLine[0].split(" "));
            newMovie.setKeywords(nextLine[1].split(" "));
            newMovie.setTitle(nextLine[2]);
            
            newMovie.setDirector(nextLine[4]);
            allMovies.put(newMovie.getTitle(), newMovie);

            


        }
    }

    // public static void main(String[]args) {
    //     try (InputStream inputStream = DataProcessor.class.getClassLoader()
    //         .getResourceAsStream("data.csv");
    //     InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    //     CSVReader reader = new CSVReader(inputStreamReader)) {
    
    //     String[] nextLine;
    //     while ((nextLine = reader.readNext()) != null) {
    //     System.out.println(String.join(", ", nextLine));
    //     }
    // } catch (IOException | CsvValidationException e) {
    //     System.out.println("Error: " + e.getMessage());
    //     e.printStackTrace();
    // }

    // List<String[]> allLines = new ArrayList<>();
    


    //System.err.println(allLines);


    }

