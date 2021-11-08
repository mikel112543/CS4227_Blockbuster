package service;

import model.Movie;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MovieService {
    File moviesCSV = new File("Movies.csv");

    @Service
    public ArrayList<Movie> listAllMovies() throws IOException {
        //TODO: put each Movie from the Movies.csv file, into an arraylist of movies, then return this arraylist.
        ArrayList<Movie> listOfMovies = new ArrayList<Movie>();
        Path pathToFile = Paths.get("Movies.csv");
        //copy 1st row of csv file, turn into object, add object to arraylist. repeat for each row of the csv file.
        BufferedReader br = Files.newBufferedReader(pathToFile);
        String line = br.readLine();

        while(line != null){
            String[] attributes = line.split(",");

            Movie movie = createMovie(attributes);
            listOfMovies.add(movie);
            line = br.readLine();
        }
        return listOfMovies;
    }
    //method used to create the movie object when given an array of it's attributes.
    public Movie createMovie(String [] metadata){
        String title = metadata[0];
        String genre = metadata[1];
        String description = metadata[2];
        LocalTime movieLength = LocalTime.parse(metadata[3]);
        LocalTime rentLength = LocalTime.parse(metadata[4]);
        double price = Double.parseDouble(metadata[5]);
        int movieID = Integer.parseInt(metadata[6]);
        int movieRating = Integer.parseInt(metadata[7]);
        String movieRelease = metadata[8];

        return new Movie (title, genre, description, movieLength, rentLength, price, movieID, movieRating, movieRelease);
    }

}
