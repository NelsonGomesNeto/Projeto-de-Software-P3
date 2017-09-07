package com.company.database;

import com.company.activity.Activity;
import com.company.activity.state.Allocated;
import com.company.activity.state.Allocating;
import com.company.activity.state.Completed;
import com.company.activity.state.State;
import com.company.resource.Resource;
import com.company.user.User;

import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class DataBase {

	ArrayList<Activity> activities;
	ArrayList<Resource> resources;
	ArrayList<User> users;
	public State allocating = new Allocating();

	public DataBase() {
		activities = new ArrayList<>();
		resources = new ArrayList<>();
		users = new ArrayList<>();
	}

	public void printAll(ArrayList arrayList) {

		int counter = 0;
		for (Object object : arrayList) {
			counter ++;
			System.out.println(counter + " - " + object.toString());
		}
	}

	public void report() {

		System.out.println("Users:");
		printAll(users);
		System.out.println("Resources:");
		printAll(resources);
		System.out.println("Activities:");
		printAll(activities);
	}

	public boolean validCPF(String CPF) {

		for (User user : users) {
			if (user.getCPF().equalsIgnoreCase(CPF)) {
				return false;
			}
		}
		return true;
	}

	public ArrayList<Activity> getActivities() {
		return activities;
	}

	public Resource getResourceByName(String name) {

		for (Resource resource : resources) {
			if (resource.name.equalsIgnoreCase(name)) {
				return(resource);
			}
		}
		return(null);
	}

	public ArrayList<User> getUserByName(String name) {

		ArrayList<User> users = new ArrayList<>();
		for (User user : this.users) {
			if (user.getName().equalsIgnoreCase(name)) {
				users.add(user);
			}
		}
		return(users);
	}

	public void setUser(User user) {

		this.users.set(this.users.indexOf(user), user);
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
