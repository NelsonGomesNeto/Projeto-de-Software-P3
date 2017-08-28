package com.design.patterns.coffee.lazy;

public class CoffeeTest {

	public static void printInfo(Coffee c) {
		System.out.println("Cost: " + c.getCost() + "; Ingredients: " + c.getIngredients());
	}

	public static void main(String[] args) {

		SimpleCoffee simpleCoffee = new SimpleCoffee();
		printInfo(simpleCoffee);

		CoffeeWithMilk coffeeWithMilk = new CoffeeWithMilk();
		printInfo(coffeeWithMilk);

		CoffeeWithMilkWithSprinkles coffeeWithMilkWithSprinkles = new CoffeeWithMilkWithSprinkles();
		printInfo(coffeeWithMilkWithSprinkles);

		CoffeeWithMilkWithSprinkles coffeeWithMilkWithSprinkles2 = new CoffeeWithMilkWithSprinkles();
		printInfo(coffeeWithMilkWithSprinkles2);
	}
}
