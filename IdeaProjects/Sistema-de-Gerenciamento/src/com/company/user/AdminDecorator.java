package com.company.user;

import com.company.activity.Activity;

public class AdminDecorator extends UserDecorator {

	public AdminDecorator(User decoratedUser) {
		super(decoratedUser);
	}

	public boolean canAllocate(String activityKind) {

		return true;
	}


	@Override
	public String toString() {
		return decoratedUser.toString() + ", Admin";
	}
}
