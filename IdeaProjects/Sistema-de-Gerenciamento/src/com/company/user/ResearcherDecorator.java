package com.company.user;

import com.company.activity.Activity;

public class ResearcherDecorator extends UserDecorator {

	public ResearcherDecorator(User decoratedUser) {
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
	public String toString() {
		return decoratedUser.toString() + ", Researcher";
	}
}
