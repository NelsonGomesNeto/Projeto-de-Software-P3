package com.company.user;

import com.company.activity.Activity;

import java.util.ArrayList;
import java.util.Date;

public interface User {

	public boolean canAllocate(String activity);

	public String getCPF();

	public String getName();

	public ArrayList<Activity> getActivities();

	public boolean isAvailable(Date begin, Date end);

	public void addActivity(Activity activity);
}
