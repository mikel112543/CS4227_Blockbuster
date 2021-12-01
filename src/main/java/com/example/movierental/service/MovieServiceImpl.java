package com.example.movierental.service;

import com.example.movierental.model.Movie;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;

@Service
public class MovieServiceImpl implements MovieService {
    ArrayList<Movie> listOfMovies = new ArrayList<>();

    @PostConstruct
    @Override
    public void InitializeList() {
        Movie transformers = new Movie(1,"Transformers", "Action", "The movie", "1hr 54mins", 1, 3);
        Movie piratesOfTheCaribbean = new Movie(2,"Pirates of the Caribbean", "Action", "pirates", "1hr 38mins", 1, 4);
        Movie titanic = new Movie(3,"The Titanic", "Romance", "It has boats", "2hr 23mins", 2, 4);
        Movie peterPan = new Movie(4,"Peter Pan", "Animation", "It's fun", "1hr 20mins", 2, 3);
        Movie luca = new Movie(5,"Luca", "Animation", "Another kids movie", "1hr 28mins", 3, 3);

        listOfMovies.add(transformers);
        listOfMovies.add(piratesOfTheCaribbean);
        listOfMovies.add(titanic);
        listOfMovies.add(peterPan);
        listOfMovies.add(luca);
    }

    public ArrayList<Movie> listAllMovies() throws IOException {

        Path pathToFile = Paths.get("movies.csv");
        BufferedReader br = Files.newBufferedReader(pathToFile);
        String line = br.readLine();

        while(line != null){
            String[] attributes = line.split(",");

    public ArrayList<Movie> listAllMovies() {
        return ListOfMovies;
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
        String length = metadata[3];
        int price = Integer.parseInt(metadata[4]);
        int movieId = Integer.parseInt(metadata[5]);
        int movieRating = Integer.parseInt(metadata[6]);

        return new Movie (movieId, title, genre, description, length, price, movieRating);
    }

    @Override
    public Movie findByMovieID(int movieID) {
        Movie movie = new Movie();
        for (int i = 0 ; i < ListOfMovies.size() ; i++ ) {
            if (ListOfMovies.get(i).getMovieId() == movieID) {
                movie = ListOfMovies.get(i);
            }
        }
        return movie;
    }
    @Override
    public ArrayList<Movie> findByName(String searchbar){
        ArrayList<Movie> results = new ArrayList<Movie>();
        for (int i = 0 ; i < ListOfMovies.size() ; i++){
            if (searchbar.contains(ListOfMovies.get(i).getTitle())){
                results.add(ListOfMovies.get(i));
            }
        }
        return results;
    }
}
