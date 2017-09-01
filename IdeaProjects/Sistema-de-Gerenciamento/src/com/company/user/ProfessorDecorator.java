package com.company.user;

import com.company.activity.Activity;

public class ProfessorDecorator extends UserDecorator {

    public ProfessorDecorator(User decoratedUser) {
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
        return decoratedUser.toString() + ", Professor";
    }
}
