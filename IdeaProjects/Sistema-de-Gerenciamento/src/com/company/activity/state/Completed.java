package com.company.activity.state;

import com.company.activity.Activity;

public class Completed implements State {

    @Override
    public void changeState(Activity activity) {
        return;
    }

    @Override
    public String toString() {
        return "Completed";
    }
}
