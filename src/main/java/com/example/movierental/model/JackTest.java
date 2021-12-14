package com.example.movierental.model;

public class JackTest {


    public static void main(String args[]){

        Movie.MovieBuilder testMovieBuilder = new Movie.MovieBuilder("Test Movie One","Genre NewPrice","Described as \"Good for Testing\"","1.1",11111,"Test1.jpg");
        testMovieBuilder.setPrice(0);
        Movie testMovie1 = testMovieBuilder.build();
        System.out.println(testMovie1.toString());

        testMovieBuilder = new Movie.MovieBuilder("Test Movie Two","Genre StandardPrice","Described as \"Pretty Good for Testing\"","1.2",22222,"Test2.jpg");
        testMovieBuilder.setPrice(1);
        Movie testMovie2 = testMovieBuilder.build();
        System.out.println(testMovie2.toString());

        testMovieBuilder = new Movie.MovieBuilder("Test Movie Three","Genre ChildrensPrice","Described as \"Grand for Testing\"","1.3",333333,"Test3.jpg");
        testMovieBuilder.setPrice(2);
        Movie testMovie3 = testMovieBuilder.build();
        System.out.println(testMovie3.toString());

        System.out.println(testMovie3.getChargeString(0));
    }
}
