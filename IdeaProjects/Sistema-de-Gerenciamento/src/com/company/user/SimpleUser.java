package com.company.user;

import com.company.activity.Activity;

import java.util.ArrayList;
import java.util.Date;

public class SimpleUser implements User {

	public String name;
	private String CPF;
	public String email;
	public ArrayList<Activity> activities;

	public SimpleUser(String name, String CPF, String email) {
		this.name = name;
		this.CPF = CPF;
		this.email = email;
		this.activities = new ArrayList<Activity>();
	}

	public boolean canAllocate(String activityKind) {

		return false;
	}

	public String getCPF() {
		return CPF;
	}

	@Override
	public String getName() { return name; }

	@Override
	public ArrayList<Activity> getActivities() {
		return this.activities;
	}

	@Override
	public boolean equals(Object object) {
		return(this.CPF.equalsIgnoreCase(((User) object).getCPF()));
	}

	@Override
	public boolean hasActivities() {
		return this.activities.size() > 0 ? true : false;
	}

	@Override
	public boolean isAvailable(Date begin, Date end) {

		for (Activity activity : activities) {
			if ((begin.before(activity.dateBegin) && end.after(activity.dateBegin))
				|| (begin.after(activity.dateBegin) && begin.before(activity.dateEnd))) {
				return(false);
			}
		}
		return(true);
	}

	@Override
	public void addActivity(Activity activity) {
		this.activities.add(activity);
	}

	@Override
	public String toString() {
		return "Name:         " + name + '\n' +
					 "\tCPF:          " + CPF + '\n' +
					 "\tE-mail:       " + email + '\n' +
					 "\tActivities:   " + activities.size() + '\n' +
					 "\tKind:         " + "Simple User";
	}
}
