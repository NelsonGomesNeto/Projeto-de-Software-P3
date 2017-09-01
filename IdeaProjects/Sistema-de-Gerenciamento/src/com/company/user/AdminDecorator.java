package com.company.user;

import com.company.activity.Activity;

public class AdminDecorator extends UserDecorator {

    public AdminDecorator(User decoratedUser) {
        super(decoratedUser);
    }

    public boolean allocate(Activity activity) {

        return true;
    }

    @Override
    public String getCPF() {
        return decoratedUser.getCPF();
    }

    @Override
    public String toString() {
        return decoratedUser.toString() + ", Admin";
    }
}
