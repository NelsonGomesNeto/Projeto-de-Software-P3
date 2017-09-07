package com.company.activity.state;

import com.company.activity.Activity;

public class Allocated implements State {

    @Override
    public void changeState(Activity activity) {

        System.out.println("Allocated!");
        activity.setState(new Completed());
    }

    @Override
    public String toString() {
        return "Allocated";
    }
}
