import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
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

    public static void main(String[]args) {
        List<String[]> allLines = new ArrayList<>();
        try (InputStream inputStream = DataProcessor.class.getClassLoader()
            .getResourceAsStream("data.csv");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        CSVReader reader = new CSVReader(inputStreamReader)) {
    
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            allLines.add(nextLine);
        System.out.println(allLines);
        allLines.size();
        // System.out.println(String.join(", ", nextLine));
        }
    } catch (IOException | CsvValidationException e) {
        System.out.println("Error: " + e.getMessage());
        e.printStackTrace();
    }

    int size =  allLines.size();
    System.out.println(size);
    String movie4= allLines.get(1).toString();
    String [] chars = movie4.split("',");
    System.out.println(chars.toString());
    }
}
