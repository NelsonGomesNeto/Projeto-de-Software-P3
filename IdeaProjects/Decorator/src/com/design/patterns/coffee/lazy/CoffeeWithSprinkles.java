package com.design.patterns.coffee.lazy;

public class CoffeeWithSprinkles implements Coffee {

	public CoffeeWithSprinkles() {}

	@Override
	public double getCost() { return 1.2;	}

	@Override
	public String getIngredients() { return "Coffee" + ", Sprinkles";	}
}
