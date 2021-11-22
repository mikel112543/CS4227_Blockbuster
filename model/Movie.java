package model;

import java.time.LocalTime;

public class Movie {
    private String title;
    private String genre;
    private String description;
    private LocalTime movieLength;
    private LocalTime rentLength;
    private double price;
    private int movieID;
    private int movieRating;
    private String movieRelease;


    public Movie(){
    }

    public Movie(String title, String genre, String description, LocalTime movieLength, LocalTime rentLength, double price, int movieID, int movieRating, String movieRelease) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.movieLength = movieLength;
        this.rentLength = rentLength;
        this.price = price;
        this.movieID = movieID;
        this.movieRating = movieRating;
        this.movieRelease = movieRelease;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getGenre(){
        return genre;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(LocalTime movieLength) {
        this.movieLength = movieLength;
    }

    public LocalTime getRentLength() {
        return rentLength;
    }

    public void setRentLength(LocalTime rentLength) {
        this.rentLength = rentLength;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public int getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(int movieRating) {
        this.movieRating = movieRating;
    }

    public String getMovieRelease() {
        return movieRelease;
    }

    public void setMovieRelease(String movieRelease) {
        this.movieRelease = movieRelease;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                ", movieLength=" + movieLength +
                ", rentLength=" + rentLength +
                ", price=" + price +
                ", movieID=" + movieID +
                ", movieRating=" + movieRating +
                ", movieRelease='" + movieRelease + '\'' +
                '}';
    }
}

