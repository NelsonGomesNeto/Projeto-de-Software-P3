package main.java.com.locadora.price;

import main.java.com.locadora.Movie;

public class ChildrenPriceDecorator extends PriceDecorator {

	public ChildrenPriceDecorator(Price priceToDecorate) { super(priceToDecorate);	}

	@Override
	public double getCharge(int daysRented) {

		return this.decoratedPrice.getCharge(daysRented) * 0.8;
	}

	@Override
	public String toString() {
		return this.decoratedPrice.toString() + ", Children Price (20%)";
	}
}