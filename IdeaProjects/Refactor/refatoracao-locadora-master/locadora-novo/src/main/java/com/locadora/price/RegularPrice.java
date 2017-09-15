package main.java.com.locadora.price;

import main.java.com.locadora.Movie;

public class RegularPrice implements Price {

	@Override
	public double getCharge(int daysRented) {
		double result = 2;

		if (daysRented > 2)
			result += (daysRented - 2) * 1.5;

		return result;
	}

	@Override
	public int getFrequentRenterPoints(int daysRented) {
		return 1;
	}

}