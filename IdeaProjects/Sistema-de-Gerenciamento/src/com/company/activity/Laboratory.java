package com.company.activity;

import com.company.activity.state.State;
import com.company.resource.Resource;
import com.company.user.User;

import java.util.ArrayList;
import java.util.Date;

public class Laboratory extends Activity {

    public Laboratory(String title, User allocator, Resource resource, String description, ArrayList<User> participants, ArrayList<Resource> supportMaterial, State state, Date dateBegin, Date dateEnd) {
        super(title, allocator, resource, description, participants, supportMaterial, state, dateBegin, dateEnd);
    }

    @Override
    public String kind() {
        return "Laboraty";
    }
}
