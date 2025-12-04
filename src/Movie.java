import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.bean.CsvBindByName;

public class Movie {
    private String[] genres;
    private String[] keywords;
    private String title;
    private String[] cast;
    private String director;
    private ArrayList<String> directorList = new ArrayList<>();;

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

    public ArrayList<String> getDirectorList() {
        return directorList;
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

    public void addGenres(String[] additionalGenres) {
        String[] newGenres = new String[genres.length + additionalGenres.length];
        System.arraycopy(genres, 0, newGenres, 0, genres.length);
        System.arraycopy(additionalGenres, 0, newGenres, genres.length, additionalGenres.length);
        genres = newGenres;
    }

    public void addKeywords(String[] additionalKeywords) {
        String[] newKeywords = new String[keywords.length + additionalKeywords.length];
        System.arraycopy(keywords, 0, newKeywords, 0, keywords.length);
        System.arraycopy(additionalKeywords, 0, newKeywords, keywords.length, additionalKeywords.length);
        keywords = newKeywords;
    }

    public void addCast(String[] additionalCast) {
        String[] newCast = new String[cast.length + additionalCast.length];
        System.arraycopy(cast, 0, newCast, 0, cast.length);
        System.arraycopy(additionalCast, 0, newCast, cast.length, additionalCast.length);
        cast = newCast;
    }

    public void addDirector(String newDirector) {
        directorList.add(newDirector);
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

