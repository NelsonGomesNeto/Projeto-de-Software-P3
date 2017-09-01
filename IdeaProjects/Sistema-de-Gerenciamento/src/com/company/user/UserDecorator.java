package com.company.user;

import com.company.activity.Activity;

public abstract class UserDecorator implements User {

    protected User decoratedUser;

    public UserDecorator(User decoratedUser) {
        this.decoratedUser = decoratedUser;
    }

    public boolean allocate(Activity activity) {
        return decoratedUser.allocate(activity);
    }
}
