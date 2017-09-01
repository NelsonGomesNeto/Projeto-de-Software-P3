package com.company.user.student;

import com.company.user.User;

public class GraduationStudent extends StudentDecorator {

    public GraduationStudent(User decoratedUser) {
        super(decoratedUser);
    }

    @Override
    public String toString() {
        return decoratedUser.toString() + ", Graduation Student";
    }
}
