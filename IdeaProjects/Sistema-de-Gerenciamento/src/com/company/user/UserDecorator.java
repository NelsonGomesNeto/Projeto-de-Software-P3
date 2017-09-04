package com.company.user;

import com.company.activity.Activity;

import java.util.ArrayList;
import java.util.Date;

public abstract class UserDecorator implements User {

	protected User decoratedUser;

	public UserDecorator(User decoratedUser) {
		this.decoratedUser = decoratedUser;
	}

	@Override
	public String getCPF() {
		return decoratedUser.getCPF();
	}

	@Override
	public String getName() {
		return decoratedUser.getName();
	}

	@Override
	public ArrayList<Activity> getActivities() {
		return decoratedUser.getActivities();
	}

	@Override
	public void addActivity(Activity activity) {
		decoratedUser.addActivity(activity);
	}

	@Override
	public boolean equals(Object object) {
		return(this.decoratedUser.getCPF().equalsIgnoreCase(((User) object).getCPF()));
	}

	@Override
	public boolean isAvailable(Date begin, Date end) {
		return(decoratedUser.isAvailable(begin, end));
	}

	public boolean canAllocate(String activityKind) {
		return decoratedUser.canAllocate(activityKind);
	}
}
