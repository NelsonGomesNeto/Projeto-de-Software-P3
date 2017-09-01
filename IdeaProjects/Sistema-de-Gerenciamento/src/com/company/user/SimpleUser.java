package com.company.user;

import com.company.activity.Activity;

import java.util.ArrayList;

public class SimpleUser implements User {

    public String name;
    private String CPF;
    public String email;
    public ArrayList<Activity> activities;

    public SimpleUser(String name, String CPF, String email) {
        this.name = name;
        this.CPF = CPF;
        this.email = email;
    }

    public boolean allocate(Activity activity) {

        return false;
    }

    public String getCPF() {
        return CPF;
    }

    @Override
    public String toString() {
        return "Name:       " + name + '\n' +
               "CPF:        " + CPF + '\n' +
               "E-mail:     " + email + '\n' +
               "Activities: " + activities + '\n' +
               "Kind:       " + "Simple User";
    }
}
