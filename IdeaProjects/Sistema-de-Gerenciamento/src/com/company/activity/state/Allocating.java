package com.company.activity.state;

import com.company.activity.Activity;

public class Allocating implements State {

    @Override
    public void changeState(Activity activity) {

        System.out.println("Allocated!");
        activity.setState(new Allocated());
    }

    @Override
    public String toString() {
        return "Allocating";
    }
}
