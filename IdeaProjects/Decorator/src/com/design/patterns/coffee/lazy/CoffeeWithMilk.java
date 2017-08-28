package com.design.patterns.coffee.lazy;

public class CoffeeWithMilk implements Coffee {

	public CoffeeWithMilk() {
	}

	@Override
	public double getCost() { return 1.5; }

	@Override
	public String getIngredients() { return "Coffee" + ", Milk"; }
}
