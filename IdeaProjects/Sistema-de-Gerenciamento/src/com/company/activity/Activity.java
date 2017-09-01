package com.company.activity;

import com.company.activity.state.State;
import com.company.resource.Resource;
import com.company.user.User;

import java.util.ArrayList;
import java.util.Date;

public abstract class Activity {

    public String title;
    public User allocator;
    public Resource resource;
    public String description;
    private ArrayList<User> participants;
    private ArrayList<Resource> supportMaterial;
    public State state;
    public Date dateBegin;
    public Date dateEnd;

    public Activity(String title, User allocator, Resource resource, String description, ArrayList<User> participants, ArrayList<Resource> supportMaterial, State state, Date dateBegin, Date dateEnd) {

        this.title = title;
        this.allocator = allocator;
        this.resource = resource;
        this.description = description;
        this.participants = participants;
        this.supportMaterial = supportMaterial;
        this.state = state;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public abstract String kind();
}
