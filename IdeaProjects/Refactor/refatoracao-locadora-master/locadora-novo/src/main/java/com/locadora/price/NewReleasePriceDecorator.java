package main.java.com.locadora.price;

import main.java.com.locadora.Movie;

public class NewReleasePriceDecorator extends PriceDecorator {

	public NewReleasePriceDecorator(Price priceToDecorate) { super(priceToDecorate);}

	@Override
	public double getCharge(int daysRented) {
		return this.decoratedPrice.getCharge(daysRented) * 0.7;
	}
	
	@Override
	public int getFrequentRenterPoints(int daysRented) {
		return this.decoratedPrice.getFrequentRenterPoints(daysRented) + 1;
	}

	@Override
	public String toString() {
		return this.decoratedPrice.toString() + ", New Release Price (30%)";
	}
}