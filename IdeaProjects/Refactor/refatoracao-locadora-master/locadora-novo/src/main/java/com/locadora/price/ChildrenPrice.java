package main.java.com.locadora.price;

import main.java.com.locadora.Movie;

public class ChildrenPrice extends PriceDecorator {

	public ChildrenPrice(Price priceToDecorate) { super(priceToDecorate);	}

	@Override
	public double getCharge(int daysRented) {
		double result = 1.5;

		if (daysRented > 3)
			result += (daysRented - 3) * 1.5;

		return result;
	}

//	@Override
//	public int getFrequentRenterPoints(int daysRented) {
//		return daysRented > 1 ? 2 : 1;
//	}
}