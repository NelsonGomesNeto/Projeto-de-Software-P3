package com.company.user.student;

import com.company.activity.Activity;
import com.company.user.User;
import com.company.user.UserDecorator;

public class StudentDecorator extends UserDecorator {

    public StudentDecorator(User decoratedUser) {
        super(decoratedUser);
    }

    public boolean allocate(Activity activity) {

        if (activity.kind().equalsIgnoreCase("Presentation")) {
            return true;
        } else {
            return decoratedUser.allocate(activity);
        }
    }

    @Override
    public String getCPF() {
        return decoratedUser.getCPF();
    }

    @Override
    public String toString() {
        return decoratedUser.toString();
    }
}
