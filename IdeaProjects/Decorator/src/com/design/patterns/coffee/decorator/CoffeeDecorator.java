package com.design.patterns.coffee.decorator;

public abstract class CoffeeDecorator implements Coffee {

	protected Coffee decoratedCoffee;

	public CoffeeDecorator(Coffee c) {
		this.decoratedCoffee = c;
	}

	@Override
	public double getCost() {
		return decoratedCoffee.getCost();
	}

	@Override
	public String getIngredients() {
		return decoratedCoffee.getIngredients();
	}
}
