package com.example.movierental.service;

import com.example.movierental.model.Movie;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;

@Service
public class MovieServiceImpl implements MovieService {
    ArrayList<Movie> listOfMovies = new ArrayList<Movie>();

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
        Duration length = Duration.parse(metadata[3]);
        int price = Integer.parseInt(metadata[4]);
        int movieId = Integer.parseInt(metadata[5]);
        int movieRating = Integer.parseInt(metadata[6]);

        return new Movie (title, genre, description, length, price, movieId, movieRating);
    }

    @Override
    public Movie findByMovieID(int movieID) {
        Path pathToFile = Paths.get("movies.csv");
        Movie movie = new Movie();
        try {
            BufferedReader br = Files.newBufferedReader(pathToFile);
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                int movieIDfromCSV = Integer.parseInt(attributes[6]);
                if (movieID != movieIDfromCSV) {
                    line = br.readLine();
                } else {
                    movie.setTitle(attributes[0]);
                    movie.setGenre(attributes[1]);
                    movie.setDescription(attributes[2]);
                    movie.setLength(Duration.parse(attributes[3]));
                    movie.setPrice(Integer.parseInt(attributes[4]));
                    movie.setMovieId(Integer.parseInt(attributes[5]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
                movie.setLength(Duration.parse(attributes[3]));
                movie.setPrice(Integer.parseInt(attributes[4]));
                movie.setMovieId(Integer.parseInt(attributes[5]));
                results.add(movie);
            }
            line = br.readLine();
        }
        return results;
    }
}
