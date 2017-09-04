package com.company.user;

import com.company.activity.Activity;

public class ProfessorDecorator extends UserDecorator {

	public ProfessorDecorator(User decoratedUser) {
		super(decoratedUser);
	}

	public boolean canAllocate(String activityKind) {

		return true;
	}

	@Override
	public String toString() {
		return decoratedUser.toString() + ", Professor";
	}
}
