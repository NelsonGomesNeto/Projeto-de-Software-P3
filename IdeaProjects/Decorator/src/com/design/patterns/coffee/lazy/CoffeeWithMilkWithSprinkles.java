package com.design.patterns.coffee.lazy;

public class CoffeeWithMilkWithSprinkles implements Coffee{

	public CoffeeWithMilkWithSprinkles() { }

	@Override
	public double getCost() { return 1.7;	}

	@Override
	public String getIngredients() { return "Coffee" + ", Milk" + ", Sprinkles";	}
}
