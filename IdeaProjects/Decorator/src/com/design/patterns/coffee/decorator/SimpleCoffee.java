package com.design.patterns.coffee.decorator;

public class SimpleCoffee implements Coffee {

	@Override
	public double getCost() {
		return(1);
	}

	@Override
	public String getIngredients() {
		return "Coffee";
	}
}
