package com.example.movierental.memento;

import java.io.*;

public class LoyaltyPointsMemento {
    /*File lptFile = new File("LoyaltyPointsTracker.ser");
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;

    public LoyaltyPointsMemento(LoyaltyPointsTracker loyaltyPointsTracker) throws IOException {
        //Serialize
        oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(lptFile)));
        oos.writeObject(loyaltyPointsTracker);
        oos.close();
    }

    public LoyaltyPointsTracker restoreState() throws IOException, ClassNotFoundException {
        //Deserialize
        ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(lptFile)));
        LoyaltyPointsTracker loyaltyPointsTracker = (LoyaltyPointsTracker) ois.readObject();
        ois.close();
        lptFile.deleteOnExit();
        return loyaltyPointsTracker;
    }*/


    //simpler method
    private LoyaltyPointsTracker pointsTracker;

    private int copyOfPoints;
    private int copyOfPrevPoints;

    public LoyaltyPointsMemento(LoyaltyPointsTracker loyaltyPointsTracker) {
        this.pointsTracker = loyaltyPointsTracker;
        this.copyOfPoints = loyaltyPointsTracker.getLoyaltyPoints();
        this.copyOfPrevPoints = loyaltyPointsTracker.getPreviousPoints();
    }

    public void restoreState() {
        this.pointsTracker.overridePoints(this.copyOfPoints);
        this.pointsTracker.previousPoints = copyOfPrevPoints;
    }
}
