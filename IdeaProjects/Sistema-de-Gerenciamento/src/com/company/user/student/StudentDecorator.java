package com.company.user.student;

import com.company.activity.Activity;
import com.company.user.User;
import com.company.user.UserDecorator;

public class StudentDecorator extends UserDecorator {

	public StudentDecorator(User decoratedUser) {
		super(decoratedUser);
	}

	public boolean canAllocate(String activityKind) {

		if (activityKind.equalsIgnoreCase("Presentation")) {
			return true;
		} else {
			return decoratedUser.canAllocate(activityKind);
		}
	}

	@Override
	public String getCPF() {
		return decoratedUser.getCPF();
	}

	@Override
	public String getName() { return decoratedUser.getName();	}

	@Override
	public void addActivity(Activity activity) {
		this.decoratedUser.addActivity(activity);
	}

	@Override
	public String toString() {
		return decoratedUser.toString();
	}
}
