package com.example.movierental;

import com.example.movierental.model.Movie;
import java.time.Duration;

public class JackTest {

    public static void main(String[] args){

        Duration timeT = Duration.ofHours(1);

        Movie m1 = new Movie("Test Movie","tGenre1","testing1",timeT,0,9999,23);
        Movie m2 = new Movie("Test Movie 2","tGenre2","testing2",timeT,1,8888,21);
        Movie m3 = new Movie("Test Movie 3","tGenre3","testing3",timeT,2,7777,55);

        System.out.println(m1.toString());
        System.out.println("Charge for 2 days m1: " + m1.getCharge(2));
        System.out.println("Loyalty points earned for 2 days m1: " + m1.getLoyaltyPointsEarned(2) +"\n");

        System.out.println(m2.toString());
        System.out.println("Charge for 2 days m2: " + m2.getCharge(2));
        System.out.println("Loyalty points earned for 2 days m2: " + m2.getLoyaltyPointsEarned(2) +"\n");

        System.out.println(m3.toString());
        System.out.println("Charge for 2 days m3: " + m3.getCharge(2));
        System.out.println("Loyalty points earned for 2 days m3: " + m3.getLoyaltyPointsEarned(2) +"\n");

    }

}
