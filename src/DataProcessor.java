import java.util.HashMap;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataProcessor {
    
    private HashMap <String, Movie> allMovies;


    public DataProcessor() {
        allMovies = new HashMap<>();
    }

    public static void main(String[]args) {
        FileReader fileReader = new FileReader("Test CSV File - Sheet1.csv");
        CSVReader reader = new CSVReader(fileReader);
    }
}
