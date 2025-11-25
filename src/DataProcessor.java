import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import com.opencsv.CSVReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DataProcessor {

    public static HashMap<String, Movie> allMovies;


    public DataProcessor() {
        allMovies = new HashMap<>();
    }

    private static HashMap<String, Movie> processMovieCsv() {
        allMovies = new HashMap<>();

        try (InputStream inputStream = DataProcessor.class.getClassLoader().getResourceAsStream("data.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            CSVReader reader = new CSVReader(inputStreamReader)) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                Movie newMovie = new Movie();
                newMovie.setGenres(nextLine[0].split(" "));
                newMovie.setKeywords(nextLine[1].split(" "));
                newMovie.setTitle(nextLine[2]);
                newMovie.setDirector(nextLine[4]);
                allMovies.put(newMovie.getTitle(), newMovie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allMovies;
    }

    public static HashMap<String, Movie> getAllMovies() {
        HashMap<String, Movie> listOfMovies = processMovieCsv();
        return listOfMovies;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("Enter 1 to see your journal");
            System.out.println("Enter 2 to log a new movie");
            System.out.println("Enter 3 to get movie recommendations");
            System.out.println("Enter 4 to exit");

            int decision = scanner.nextInt();
            scanner.nextLine();

            if (decision == 1) {
                Collection<Movie> watchedMovies = Journal.getWatchedMovies();
                if (watchedMovies.isEmpty()) {
                    System.out.println(
                        "You haven't logged any movies. Please log at least one to be able to see your journal");
                } else {
                    System.out.println("Your movie journal:");
                    for (Movie m : watchedMovies) {
                        System.out.println(m.getTitle() + ", directed by " + m.getDirector() + " and starring "
                            + m.getLeadActors() + " | Rating: " + Journal.getRating(m));
                        System.out.println();
                    }
                }
            }

            else if (decision == 2) {
                String title = "";
                while (true) {
                    System.out.println("Enter the title of the movie: ");
                    title = scanner.nextLine().trim();

                    if (!getAllMovies().containsKey(title)) {
                        System.out.println("It seems like this movie isn't in our database.");
                        System.out.println("Make sure you entered the title correctly and try again");
                    } else {
                        break;
                    }
                }

                int rating = 0;
                while (true) {
                    System.out.print("Enter the rating for the movie (1 to 10): ");
                    if (scanner.hasNextInt()) {
                        rating = scanner.nextInt();
                        scanner.nextLine();

                        if (rating > 0 && rating <= 10) {
                            break;
                        } else {
                            System.out.println("Rating must be a number between 1 and 10, please try again.");
                        }
                    } else {
                        System.out.println("Rating must be a number between 1 and 10, please try again.");
                        scanner.nextLine();
                    }

                }

                Journal.addToUserMovies(title, rating);
                System.out.println("Movie entered: Title: " + title + " Rating: " + rating);
            }

            else if (decision == 3) {
                System.out.println("We have to figure this out");
            }

            else if (decision == 4) {
                System.out.println("Goodbye!");
                break;
            }

            else {
                System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}

