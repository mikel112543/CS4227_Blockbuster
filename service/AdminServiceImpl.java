package service;

import model.Movie;
import model.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {


    ArrayList<User> listOfUsers = new ArrayList<User>();
    ArrayList<Movie> listOfMovies = new ArrayList<Movie>();

    public void addMovie(String title, String genre, String description, LocalTime movieLength, LocalTime rentLength, double price, int movieID, int movieRating, String movieRelease) throws IOException {
        Movie movie = new Movie(title, genre, description, movieLength, rentLength, price, movieID, movieRating, movieRelease);

        Path pathToFile = Paths.get("movies.csv");
        BufferedReader br = Files.newBufferedReader(pathToFile);
        PrintWriter pw = new PrintWriter("movies.csv");
        String line = br.readLine();
        if (line == null){
            pw.println(movie.getTitle() + "," + movie.getGenre() + "," + movie.getDescription() + "," + movie.getMovieLength() + "," + movie.getRentLength() + "," + movie.getPrice() + "," + movie.getMovieID() + "," + movie.getMovieRating() + "," + movie.getMovieRelease());
            pw.close();
            br.close();
        }

    }

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
        } finally {

        }
    }

    public ArrayList<User> listAllUsers() throws IOException {
        Path pathToFile = Paths.get("users.csv");
        BufferedReader br = Files.newBufferedReader(pathToFile);
        String line = br.readLine();

        while(line != null){
            String[] attributes = line.split(",");
            User user = createUser(attributes);
            listOfUsers.add(user);
            line = br.readLine();
        }
        return listOfUsers;
    }
    //method used to create the user object when given an array of its attributes.
    public User createUser(String [] metadata){
        int ID = Integer.parseInt(metadata[0]);
        String username = metadata[1];
        String password = metadata[2];
        boolean isBanned = Boolean.parseBoolean(metadata[3]);

        return new User(ID, username, password, isBanned);
    }
    public void banUser(int userID) throws IOException {
        listOfUsers = this.listAllUsers();
        User user = new User();
        //findByUserID in userservice..
        for (int i = 0; i < listOfUsers.size(); i++){
            if (user.getUserID() == userID){
                user.setBanned(true);
            }
        }
        //delete the users/customers csv file and put new arraylist in.
    }
}