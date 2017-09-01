package com.company.user.student;

import com.company.user.User;

public class PhDStudent extends StudentDecorator {

    public PhDStudent(User decoratedUser) {
        super(decoratedUser);
    }

    @Override
    public String toString() {
        return decoratedUser.toString() + ", PhD Student";
    }
}
