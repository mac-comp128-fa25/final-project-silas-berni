import java.util.Arrays;
import java.util.List;

import com.opencsv.bean.CsvBindByName;

public class Movie {
    private String[] genres;
    private String[] keywords;
    private String title;
    private String[] cast;
    private String director;

    public Movie(String title, String director, String[] cast, String[] genres, String[] keywords) {
        this.title = title;
        this.director = director;
        this.cast = cast;
        this.genres = genres;
        this.keywords = keywords;
    }

    public Movie(){
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String[] getLeadActors() {
        return cast;
    }

    public String[] getGenres() {
        return genres;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }

    public void setGenres(String[] newGenres) {
        genres = newGenres;
    }

    public void setKeywords(String[] newKeywords) {
        keywords = newKeywords;
    }

    public void setDirector(String newDirector) {
        director = newDirector;
    }

    public void setCast(String[] newCast) {
        cast = newCast;
    }
    
    public String toString() {
    return "Movie{" +
            "title='" + title + '\'' +
            ", director='" + director + '\'' +
            ", genres=" + Arrays.toString(genres) +
            ", keywords=" + Arrays.toString(keywords) +
            '}';
}
}

