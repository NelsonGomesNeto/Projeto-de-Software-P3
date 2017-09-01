package com.company.user;

import com.company.activity.Activity;

public class ResearcherDecorator extends UserDecorator {

    public ResearcherDecorator(User decoratedUser) {
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
        return decoratedUser.toString() + ", Researcher";
    }
}
