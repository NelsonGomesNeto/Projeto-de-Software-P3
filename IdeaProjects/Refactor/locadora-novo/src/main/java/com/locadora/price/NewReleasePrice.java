package main.java.com.locadora.price;

import main.java.com.locadora.Movie;

public class NewReleasePrice extends PriceDecorator {

	public NewReleasePrice(Price priceToDecorate) { super(priceToDecorate);	}

	@Override
	public double getCharge(int daysRented) {
		return daysRented * 3;
	}
	
	@Override
	public int getFrequentRenterPoints(int daysRented) {
		return daysRented > 1 ? 2 : 1;
	}

}