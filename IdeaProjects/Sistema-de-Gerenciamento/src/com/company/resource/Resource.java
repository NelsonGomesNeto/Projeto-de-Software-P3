package com.company.resource;

import com.company.activity.Activity;
import com.company.user.User;

import java.util.ArrayList;
import java.util.Date;

public class Resource {

	public String name;
	private ArrayList<Activity> activities;

	public Resource(String name) {

		this.name = name;
		this.activities = new ArrayList<Activity>();
	}

	public String getName() { return name; }

	public boolean isAvailable(Date begin, Date end) {

		for (Activity activity : activities) {
			if ((begin.before(activity.dateBegin) && end.after(activity.dateBegin))
				|| (begin.after(activity.dateBegin) && begin.before(activity.dateEnd))) {
				return(false);
			}
		}
		return(true);
	}

	public void addActivity(Activity activity) {
		this.activities.add(activity);
	}

	@Override
	public String toString() {
		return "Name:         " + name + '\n' +
			     "\tActivities:   " + activities.size();
	}
}
