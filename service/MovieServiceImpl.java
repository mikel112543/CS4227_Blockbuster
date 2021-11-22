package service;
import org.springframework.stereotype.Service;
import model.Movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;

public class MovieServiceImpl implements MovieService {
    ArrayList<Movie> listOfMovies = new ArrayList<Movie>();
    @Service
    public ArrayList<Movie> listAllMovies() throws IOException {

        Path pathToFile = Paths.get("movies.csv");
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



    public Movie findByMovieID(int movieID) throws IOException {
        Path pathToFile = Paths.get("movies.csv");
        BufferedReader br = Files.newBufferedReader(pathToFile);
        String line = br.readLine();
        Movie movie = new Movie();
        while (line != null) {
            String[] attributes = line.split(",");
            int movieIDfromCSV = Integer.parseInt(attributes[6]);
            if (movieID != movieIDfromCSV) {
                line = br.readLine();
            } else {
                movie.setTitle(attributes[0]);
                movie.setGenre(attributes[1]);
                movie.setDescription(attributes[2]);
                movie.setMovieLength(LocalTime.parse(attributes[3]));
                movie.setRentLength(LocalTime.parse(attributes[4]));
                movie.setPrice(Double.parseDouble(attributes[5]));
                movie.setMovieID(Integer.parseInt(attributes[6]));
                movie.setMovieRating(Integer.parseInt(attributes[7]));
                movie.setMovieRelease(attributes[8]);
            }
        }
        return movie;
    }

    public ArrayList<Movie> findByName(String searchbar) throws IOException{
        ArrayList<Movie> results = new ArrayList<Movie>();
        Path pathToFile = Paths.get("movies.csv");
        BufferedReader br = Files.newBufferedReader(pathToFile);
        String line = br.readLine();
        while (line != null){
            String [] attributes = line.split(",");
            String nameFromCSV = attributes[0];
            Movie movie = new Movie();
            if (nameFromCSV.contains(searchbar)){
                movie.setTitle(attributes[0]);
                movie.setGenre(attributes[1]);
                movie.setDescription(attributes[2]);
                movie.setMovieLength(LocalTime.parse(attributes[3]));
                movie.setRentLength(LocalTime.parse(attributes[4]));
                movie.setPrice(Integer.parseInt(attributes[5]));
                movie.setMovieID(Integer.parseInt(attributes[6]));
                movie.setMovieRating(Integer.parseInt(attributes[7]));
                movie.setMovieRelease(attributes[8]);
                results.add(movie);
            }
            line = br.readLine();
        }
        return results;
    }
}
