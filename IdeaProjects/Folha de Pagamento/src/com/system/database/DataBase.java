package com.system.database;

import com.system.people.Commissioned;
import com.system.people.Employee;
import com.system.people.Hourly;
import com.system.people.Salaried;

import java.util.ArrayList;
import java.util.Stack;

public class DataBase {

  private Stack<ArrayList<Hourly>> hourlyStack = new Stack<>();
  private Stack<ArrayList<Salaried>> salariedStack = new Stack<>();
  private Stack<ArrayList<Commissioned>> commissionedStack = new Stack();

  public DataBase() {
  }

  public void pushAll(ArrayList<Hourly> listHourly, ArrayList<Salaried> listSalaried, ArrayList<Commissioned> listCommissioned) {

    ArrayList<Hourly> newHourly = new ArrayList<>();
    for (Hourly hourly : listHourly) {

      newHourly.add(new Hourly(hourly));
    }
    this.hourlyStack.push(newHourly);

    ArrayList<Salaried> newSalaried = new ArrayList<>();
    for (Salaried salaried : listSalaried) {

      newSalaried.add(new Salaried(salaried));
    }
    this.salariedStack.push(newSalaried);

    ArrayList<Commissioned> newCommissioned = new ArrayList<>();
    for (Commissioned commissioned : listCommissioned) {

      newCommissioned.add(new Commissioned(commissioned));
    }
    this.commissionedStack.push(newCommissioned);
  }

  public int getSize() {

    return this.hourlyStack.size();
  }

  public ArrayList<Hourly> popHourly() {

    return this.hourlyStack.pop();
  }

  public ArrayList<Salaried> popSalaried() {

    return this.salariedStack.pop();
  }

  public ArrayList<Commissioned> popCommissioned() {

    return this.commissionedStack.pop();
  }
}
