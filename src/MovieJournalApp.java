
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;




public class MovieJournalApp {

    private static void showJournal() {
        Collection<Movie> watchedMovies = Journal.getWatchedMovies();
        if (watchedMovies.isEmpty()) {
            System.out.println(
                "You haven't logged any movies. Please log at least one to be able to see your journal");
        } else {
            System.out.println("Your movie journal:");
            for (Movie m : watchedMovies) {
                System.out.println(m.getTitle() + ", directed by " + m.getDirector() + " and starring "
                    + Arrays.toString(m.getLeadActors()) + " | Rating: " + Journal.getRating(m));
                System.out.println();
            }
        }
    }

    private static void logMovie(Scanner scanner, HashMap<String, Movie> movies) {
        String title = "";
        while (true) {
            System.out.println("Enter the title of the movie: ");
            title = scanner.nextLine().trim();

            if (movies.containsKey(title)) {
                break;
            } else {
                System.out.println("It seems like this movie isn't in our database.");
                System.out.println("Make sure you entered the title correctly and try again");
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

    private static void showRecommendations() {
        System.out.println("We will figure this part out later");
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DataProcessor processor = new DataProcessor();
        HashMap<String, Movie> allMovies = DataProcessor.getAllMovies();

        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("Enter 1 to see your journal");
            System.out.println("Enter 2 to log a new movie");
            System.out.println("Enter 3 to get movie recommendations");
            System.out.println("Enter 4 to exit");

            int decision = scanner.nextInt();
            scanner.nextLine();

            if (decision == 1) {
                showJournal();
            } else if (decision == 2) {
                logMovie(scanner, allMovies);
            } else if (decision == 3) {
                showRecommendations();
            } else if (decision == 4) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("You must select an option between 1 and 4, please try again");
            }
            
        }
        scanner.close();
    }
}

