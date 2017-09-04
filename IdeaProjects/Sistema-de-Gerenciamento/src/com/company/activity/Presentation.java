package com.company.activity;

import com.company.activity.state.State;
import com.company.resource.Resource;
import com.company.user.User;

import java.util.ArrayList;
import java.util.Date;

public class Presentation extends Activity {

    public Presentation(String title, User allocator, Resource resource, String description, String participants, State state, Date dateBegin, Date dateEnd) {
        super(title, allocator, resource, description, participants, state, dateBegin, dateEnd);
    }

    @Override
    public String kind() {
        return "Presentation";
    }
}
