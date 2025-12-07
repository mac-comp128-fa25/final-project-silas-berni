import java.util.Comparator;

public class MovieComparator implements Comparator<Movie> {
    
    @Override
    public int compare(Movie movieA, Movie movieB) {
        if (movieA.getSimilarity() < movieB.getSimilarity()) {
            return -1;
        } else if(movieA.getSimilarity() > movieB.getSimilarity()) {
            return 1;
        } else {
            return 0;
        }
    }
}
