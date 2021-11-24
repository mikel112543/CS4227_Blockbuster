package com.example.movierental.service;

import com.example.movierental.service.UserServiceImpl;
import com.example.movierental.model.Movie;
import com.example.movierental.model.Rental;
import com.example.movierental.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserServiceImpl userService;

    ArrayList<User> listOfUsers = new ArrayList<User>();
    ArrayList<Movie> listOfMovies = new ArrayList<Movie>();
    @Override
    public void addMovie(String title, String genre, String description, Duration length, int price, int movieID, int movieRating) throws IOException {
        Movie movie = new Movie(title, genre, description, length, price, movieID, movieRating);

        Path pathToFile = Paths.get("movies.csv");
        BufferedReader br = Files.newBufferedReader(pathToFile);
        PrintWriter pw = new PrintWriter("movies.csv");
        String line = br.readLine();
        if (line == null){
            pw.println(movie.getTitle() + "," + movie.getGenre() + "," + movie.getDescription() + "," + movie.getLength() + "," + movie.getPrice() + "," + movie.getMovieId() + "," + movie.getMovieRating());
            pw.close();
            br.close();
        }

    }
    @Override
    public void deleteMovie(int movieID) throws IOException {
        Path pathToFile = Paths.get("movies.csv");
        try {
            BufferedReader br = Files.newBufferedReader(pathToFile);
            String line = br.readLine();
            int count = 1;
            while (line != null) {

                String[] attributes = line.split(",");
                int movieIDfromCSV = Integer.parseInt(attributes[6]);
                if (movieIDfromCSV != movieID) {
                    line = br.readLine();
                    count = count + 1;
                } else {
                    //remove count row
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public ArrayList<User> listAllUsers() throws IOException {
        Path pathToFile = Paths.get("users.csv");
        BufferedReader br = Files.newBufferedReader(pathToFile);
        String line = br.readLine();

        while(line != null){
            String[] attributes = line.split(",");
            User user = createCustomer(attributes);
            listOfUsers.add(user);
            line = br.readLine();
        }
        return listOfUsers;
    }
    //method used to create the user object when given an array of its attributes.
    @Override
    public User createCustomer(String [] metadata){
        int id = Integer.parseInt(metadata[0]);
        String username = metadata[1];
        String password = metadata[2];
        boolean isBanned = Boolean.parseBoolean(metadata[3]);
        int tier = 1;
        List<Rental> userRentals = new ArrayList<Rental>();
        int loyaltyPoints = 0;

        return new User(id, username, password, isBanned, loyaltyPoints, tier, userRentals, false);
    }
    @Override
    public void banCustomer(int userID) throws IOException {
        listOfUsers = this.listAllUsers();
        User cust = new User();
        //findByUserID in userservice... put details into user.
        for (int i = 0; i < listOfUsers.size(); i++){
            if (cust.getUserID() == userID){
                cust.setBanned(true);
            }
        }
        //delete the users/customers csv file and put new arraylist in.
    }
}