package com.company.resource;

import com.company.user.User;

public class Resource {

    private int ID;
    public String name;
    public User responsible;
    public int allocations;

    public Resource(int ID, String name, User responsible, int allocations) {

        this.ID = ID;
        this.name = name;
        this.responsible = responsible;
        this.allocations = 0;
    }
}
