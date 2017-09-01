package com.company.activity.state;

import com.company.activity.Activity;

public class Allocating implements State {

    @Override
    public void changeState(Activity activity) {

        System.out.println("Allocating!");
        activity.setState(this);
    }

    @Override
    public String toString() {
        return "Allocating";
    }
}
