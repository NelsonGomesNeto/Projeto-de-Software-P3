package com.company.user;

import com.company.activity.Activity;

public interface User {

    public boolean allocate(Activity activity);

    public String getCPF();
}
