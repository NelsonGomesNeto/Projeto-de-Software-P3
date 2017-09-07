package com.company.activity;

import com.company.activity.state.State;
import com.company.resource.Resource;
import com.company.user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;

public abstract class Activity {

	public String title;
	public User allocator;
	public Resource resource;
	public String description;
	//private ArrayList<User> participants;
	private String participants;
	//private ArrayList<Resource> supportMaterial;
	public State state;
	public Date dateBegin;
	public Date dateEnd;

	public Activity(String title, User allocator, Resource resource, String description, String participants, State state, Date dateBegin, Date dateEnd) {

		this.title = title;
		this.allocator = allocator;
		this.resource = resource;
		this.description = description;
		this.participants = participants;
		this.state = state;
		this.dateBegin = dateBegin;
		this.dateEnd = dateEnd;
	}

	public void setState(State state) {
		this.state = state;
	}

	public void nextState() { this.state.changeState(this); }

	public State getState() {
		return state;
	}

	public abstract String kind();

	@Override
	public String toString() {
		return "Title:        " + title + '\n' +
					 "\tUser:         " + allocator.getName() + '\n' +
					 "\tResource:     " + resource.getName() + '\n' +
					 "\tDescription:  " + description + '\n' +
			     "\tParticipants: " + participants + '\n' +
					 "\tState:        " + state + '\n' +
					 "\tStart time:   " + dateBegin + '\n' +
					 "\tEnd time:     " + dateEnd;
	}
}
