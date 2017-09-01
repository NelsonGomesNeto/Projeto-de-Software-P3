package com.company.activity.state;

import com.company.activity.Activity;

public class Completed implements State {

    @Override
    public void changeState(Activity activity) {
        System.out.println("Completed!");
        activity.setState(this);
    }

    @Override
    public String toString() {
        return "Completed";
    }
}
