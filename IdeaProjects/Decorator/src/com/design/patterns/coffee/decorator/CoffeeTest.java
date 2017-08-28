package com.design.patterns.coffee.decorator;

public class CoffeeTest {

	public static void printInfo(Coffee c) {
		System.out.println("Cost: " + c.getCost() + "; Ingredients: " + c.getIngredients());
	}

	public static void main(String[] args) {

		Coffee coffee = new SimpleCoffee();
		printInfo(coffee);

		coffee = new WithMilk(coffee);
		printInfo(coffee);

		coffee = new WithSprinkles(coffee);
		printInfo(coffee);

		coffee = new WithSprinkles(new WithMilk(new SimpleCoffee()));
		printInfo(coffee);
	}
}
