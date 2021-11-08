package service;

import model.Movie;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;

public class AdminService {

    File moviesCSV = new File("Movies.csv");
    File usersCSV = new File("Users.csv");


    public void addMovie(String title, String genre, String description, LocalTime movieLength, LocalTime rentLength, double price, int movieID, int movieRating, String movieRelease) throws IOException {
        Movie movie = new Movie(title, genre, description, movieLength, rentLength, price, movieID, movieRating, movieRelease);

        PrintWriter pw = new PrintWriter(moviesCSV);

        pw.println(movie.getTitle() + "," + movie.getGenre() + "," + movie.getDescription() + "," + movie.getMovieLength() + "," + movie.getRentLength() + "," + movie.getPrice() + "," + movie.getMovieID() + "," + movie.getMovieRating() + "," + movie.getMovieRelease());
        pw.close();
    }

    public void deleteMovie(int movieID) {
        //TODO: search the CSV file for the movieID (column 7) and remove the entire row.
        boolean found = false;

    }

    public ArrayList<User> listAllUsers(){
        //TODO: put each user into an arraylist of users. then return this arraylist.


        //copy 1st row of csv file, turn into object, add object to arraylist. repeat for each row of the csv file.
        return listOfUsers;
    }

    public void banUser(int userID){

    }
}