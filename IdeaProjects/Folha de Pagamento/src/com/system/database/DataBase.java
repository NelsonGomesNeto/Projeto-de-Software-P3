package com.system.database;

import com.system.people.Commissioned;
import com.system.people.Hourly;
import com.system.people.Salaried;

import java.util.Stack;

public class DataBase {

  Stack<Hourly> hourlyStack = new Stack<>();
  Stack<Salaried> salariedStack = new Stack<>();
  Stack<Commissioned> commissionedStack = new Stack();
}
