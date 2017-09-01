package com.company.user.student;

import com.company.user.User;

public class MasteringStudent extends StudentDecorator {

    public MasteringStudent(User decoratedUser) {
        super(decoratedUser);
    }

    @Override
    public String toString() {
        return decoratedUser.toString() + ", Mastering Student";
    }
}
