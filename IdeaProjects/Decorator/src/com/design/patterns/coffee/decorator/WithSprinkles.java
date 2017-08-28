package com.design.patterns.coffee.decorator;

public class WithSprinkles extends CoffeeDecorator {

	public WithSprinkles(Coffee coffee) {
		super(coffee);
	}

	@Override
	public double getCost() {
		return super.getCost() + 0.2;
	}

	@Override
	public String getIngredients() {
		return super.getIngredients() + ", Sprinkles";
	}
}
