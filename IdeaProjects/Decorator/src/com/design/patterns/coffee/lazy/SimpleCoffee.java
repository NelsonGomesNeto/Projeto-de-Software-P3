package com.design.patterns.coffee.lazy;

public class SimpleCoffee implements Coffee {

	public SimpleCoffee() {
	}

	@Override
	public double getCost() {
		return(1);
	}

	@Override
	public String getIngredients() {
		return "Coffee";
	}
}
