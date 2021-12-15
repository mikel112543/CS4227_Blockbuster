package com.example.movierental.states;

import com.example.movierental.model.User;

//Context
public class StateHandler {
    private Tier currentTier;
    private int tier;

    public StateHandler(User user) {
        tier = user.getTier();
        if (tier == 1) {
            currentTier = new Tier1();
        } else if(tier == 2) {
            currentTier = new Tier2();
        } else if (tier == 3) {
            currentTier = new Tier3();
        }
    }

    public Tier getCurrentTier() {
        return currentTier;
    }

    public void setCurrentTier(Tier currentTier) {
        this.currentTier = currentTier;
    }
}
