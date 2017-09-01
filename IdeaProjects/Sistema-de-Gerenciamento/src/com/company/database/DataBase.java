package com.company.database;

import com.company.activity.Activity;
import com.company.activity.state.Allocated;
import com.company.activity.state.Allocating;
import com.company.activity.state.Completed;
import com.company.activity.state.State;
import com.company.resource.Resource;
import com.company.user.User;

import java.util.ArrayList;

public class DataBase {

    ArrayList<Activity> activities;
    ArrayList<Resource> resources;
    ArrayList<User> users;
    State allocating = new Allocating();
    State allocated = new Allocated();
    State completed = new Completed();

    public DataBase() {
        activities = new ArrayList<>();
        resources = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void printAll(ArrayList arrayList) {

        for (Object object : arrayList) {

            System.out.println(object.toString());
        }
    }

    public void report() {

        printAll(activities);
        printAll(resources);
        printAll(users);
    }

    public boolean validCPF(String CPF) {

        for (User user : users) {
            if (user.getCPF().equalsIgnoreCase(CPF)) {
                return false;
            }
        }
        return true;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addResource(Resource resource) {
        resources.add(resource);
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

}
