
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;


public class MovieJournalApp {

    private Journal journal;
    private DataProcessor processor;
    private Recommender recommender;
    private HashMap<String, Movie> allMovies;

    public MovieJournalApp() {
        processor = new DataProcessor();
        journal = new Journal(processor);
        allMovies = processor.getAllMovies();
        recommender = new Recommender(journal);
    }

    private void showJournal(Journal journal) {
        Collection<Movie> watchedMovies = journal.getWatchedMovies();
        if (watchedMovies.isEmpty()) {
            System.out.println(
                "You haven't logged any movies. Please log at least one to be able to see your journal");
        } else {
            System.out.println("Your movie journal:");
            for (Movie m : watchedMovies) {
                System.out.println(m.getTitle() + ", directed by " + m.getDirector() + " and starring "
                    + Arrays.toString(m.getLeadActors()) + " | Rating: " + journal.getRating(m));
                System.out.println();
            }
        }
    }

    private void logMovie(Scanner scanner, HashMap<String, Movie> movies, Journal journal) {
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

        journal.addToUserMovies(title, rating);
        System.out.println("Movie entered: Title: " + title + " Rating: " + rating);
    }

    private void showRecommendations(Scanner scanner) {
        int recNumber = -1;

        while (true) {
            System.out.println("How many recommendations would you like? Please enter an integer greater than 0");
            if (scanner.hasNextInt()) {
                recNumber = scanner.nextInt();
                scanner.nextLine();

                if (recNumber > 0) {
                    break;
                } else {
                    System.out.println("Please enter an integer greater than 0!");
                }
            } else {
                System.out.println("Please enter an integer greater than 0!");
                scanner.nextLine();
            }
        }


        PriorityQueue<Movie> recommendations = recommender.recommend(journal, allMovies);
        Collection<Movie> watchedMovies = journal.getWatchedMovies();
        if (watchedMovies.isEmpty()) {
            System.out.println(
                "You haven't logged any movies. Please log at least one to be able to see your personalized recommendations");
        } else {
            System.out
                .println("Here are " + recNumber + " recommendations for you based on the movies you have watched!");
            for (int i = 0; i < recNumber; i++) {
                Movie m = recommendations.poll();
                if (m == null) {
                    if (i >= 1) {
                        System.out.println(
                            "Oops, looks like these are all the movies we can recommend. Please input more movies that you like for better recommendations!");
                        break;
                    } else {
                        System.out.println(
                            "We aren't able to give any recommendations based on your journal. Please input more movies that you like for better recommendations!");
                        break;
                    }

                }
                System.out.println(m.getTitle());
            }
        }
    }

    public void run() {

        Scanner scanner = new Scanner(System.in);
        int decision = -1;

        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("Enter 1 to see your journal");
            System.out.println("Enter 2 to log a new movie");
            System.out.println("Enter 3 to get movie recommendations");
            System.out.println("Enter 4 to exit");

            if (scanner.hasNextInt()) {
                decision = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("It appears you haven't entered a valid option. Please enter either 1, 2, 3 or 4.");
                scanner.nextLine();
                continue;
            }

            if (decision == 1) {
                showJournal(journal);
            } else if (decision == 2) {
                logMovie(scanner, allMovies, journal);
            } else if (decision == 3) {
                showRecommendations(scanner);
            } else if (decision == 4) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("You must select an option between 1 and 4, please try again");
            }

        }
        scanner.close();
    }

    public static void main(String[] args) {
        new MovieJournalApp().run();
    }
}

